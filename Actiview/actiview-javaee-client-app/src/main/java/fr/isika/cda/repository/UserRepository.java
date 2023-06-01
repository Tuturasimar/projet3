package fr.isika.cda.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.viewmodels.UpdateUserViewModel;
import fr.isika.cda.viewmodels.LoginViewModel;
import fr.isika.cda.viewmodels.UpdateUserRoleViewModel;
import fr.isika.cda.viewmodels.UserViewModel;

@Stateless
public class UserRepository {

	private static final Long MANAGER_ID_DEFAULT_NOT_FOUND = -1L;
	@PersistenceContext
	private EntityManager em;

	/**
	 * Enregistre en bdd un User avec ses tables associées UserData et UserRole
	 * 
	 * @param userVM
	 * @return
	 */
	public Long registerUser(UserViewModel userVM) {
		UserData data = new UserData();
		data.setFirstname(userVM.getFirstname());
		data.setLastname(userVM.getLastname());
		data.setBirthday(userVM.getBirthday());
		data.setEmail(userVM.getEmail());
		data.setJobEnum(userVM.getJobEnum());

		em.persist(data);

		User user = new User();
		user.setLogin(userVM.getLogin());
		user.setPassword(userVM.getPassword());
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus(StatusEnum.ACTIVE);
		user.setUserData(data);

		Long managerId = extractManagerId(userVM.getManagerId());
		if (!MANAGER_ID_DEFAULT_NOT_FOUND.equals(managerId)) {
			User manager = em.find(User.class, managerId);
			user.setManager(manager);
		}

		em.persist(user);

		List<RoleTypeEnum> roleTypes = new ArrayList<RoleTypeEnum>(userVM.getRoleTypes());

		for (RoleTypeEnum roleType : roleTypes) {
			UserRole role = new UserRole();
			role.setUser(user);
			role.setRoleTypeEnum(roleType);
			em.persist(role);
		}

		return user.getId();
	}

	public Long updateUser(UpdateUserViewModel updateUserVM) {
		UserData data = findDataByUserId(updateUserVM.getId());
		data.setFirstname(updateUserVM.getFirstname());
		data.setLastname(updateUserVM.getLastname());
		data.setBirthday(updateUserVM.getBirthday());
		data.setEmail(updateUserVM.getEmail());
		data.setJobEnum(updateUserVM.getJobEnum());

		em.persist(data);

		User user = findUserById(updateUserVM.getId());
		user.setPassword(updateUserVM.getPassword());
		user.setStatus(updateUserVM.getStatus());

		Long managerId = extractManagerId(updateUserVM.getManagerId());
		if (!MANAGER_ID_DEFAULT_NOT_FOUND.equals(managerId)) {
			User manager = em.find(User.class, managerId);
			user.setManager(manager);
		}
		user.setUserData(data);

		em.persist(user);

		return user.getId();
	}

	public Long updateUserRole(UpdateUserRoleViewModel updateUserRoleVM) {
		User user = findUserById(updateUserRoleVM.getUserId());
		// On efface tous les userRole du user
		List<UserRole> existingUserRoles = getAllUserRolesByUserId(user.getId());
		for (UserRole existingUserRole : existingUserRoles) {
			em.remove(existingUserRole);
		}

		// On recrée les nouveaux userRoles associés au user
		List<RoleTypeEnum> roleTypes = new ArrayList<RoleTypeEnum>(updateUserRoleVM.getRoleTypes());
		for (RoleTypeEnum roleType : roleTypes) {
			UserRole role = new UserRole();
			role.setUser(user);
			role.setRoleTypeEnum(roleType);
			em.persist(role);
		}
		return user.getId();
	}

	private Long extractManagerId(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return MANAGER_ID_DEFAULT_NOT_FOUND;
		}
	}

	public User findUserById(Long id) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
		query.setParameter("id", id);
		return (User) query.getSingleResult();
	}
	
	public User findUserByLogin(String login) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
		query.setParameter("login", login);
		return (User) query.getSingleResult();
	}
	
	public User findUserWithManagerById(Long id) {
		Query query = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.manager WHERE u.id = :id");
		query.setParameter("id", id);
		return (User) query.getSingleResult();
	}

	public UserData findDataByUserId(Long id) {
		Query query = em.createQuery("SELECT ud FROM User u JOIN u.userData ud WHERE u.id = :id");
		query.setParameter("id", id);
		return (UserData) query.getSingleResult();
	}

	public List<User> findAllUsers() {
		return em.createQuery("SELECT u FROM User u JOIN u.userData", User.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getAllUserRolesByUserId(Long id) {
		Query query = em.createQuery("SELECT ur FROM UserRole ur JOIN ur.user u WHERE u.id = :id", UserRole.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	//En cours par Diane
	@SuppressWarnings("unchecked")
	public List<User> getAllManagers() {
		Query query = em.createQuery("SELECT u FROM UserRole ur JOIN ur.user u WHERE ur.roleTypeEnum = :manager")
				.setParameter("manager", RoleTypeEnum.MANAGER);
		
		List<User> managers = query.getResultList();
		return managers;
	}

	/**
	 * Returns a user with the given login data. <br>
	 * If not found, returns <b>null</b>.
	 * 
	 * @param loginVm
	 * @return
	 */
	public User checkIfUserExists(LoginViewModel loginVm) {
		return em
				.createQuery("SELECT u FROM User u WHERE u.login = :loginParam AND u.password = :passwordParam",
						User.class)
				.setParameter("loginParam", loginVm.getLogin()).setParameter("passwordParam", loginVm.getPassword())
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findUserByManagerLogin(String login) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.manager.login = :login");
		query.setParameter("login", login);
		return  query.getResultList();
	}

	
}
