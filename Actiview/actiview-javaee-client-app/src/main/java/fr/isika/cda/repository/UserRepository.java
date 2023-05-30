package fr.isika.cda.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;
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

		UserRole role = new UserRole();
		role.setRoleTypeEnum(userVM.getRoleTypeEnum());
		role.setUser(user);

		em.persist(role);

		return user.getId();
	}

	public Long updateUser(Long id, UserViewModel userVM) {
		UserData data = findDataByUserId(id);
		data.setFirstname(userVM.getFirstname());
		data.setLastname(userVM.getLastname());
		data.setBirthday(userVM.getBirthday());
		data.setEmail(userVM.getEmail());
		data.setJobEnum(userVM.getJobEnum());
		
		em.persist(data);
		
		User user = findUserById(id);
		user.setPassword(userVM.getPassword());
		user.setStatus(userVM.getStatus());

		Long managerId = extractManagerId(userVM.getManagerId());
		if (!MANAGER_ID_DEFAULT_NOT_FOUND.equals(managerId)) {
			User manager = em.find(User.class, managerId);
			user.setManager(manager);
		}
		user.setUserData(data);
		
		em.persist(user);

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
		Query query = em.createQuery("SELECT u FROM User u WHERE u.id= :id");
		query.setParameter("id", id);
		return (User) query.getSingleResult();
	}

	private UserData findDataByUserId(Long id) {
		Query query = em.createQuery("SELECT ud FROM UserData ud JOIN ud.user u WHERE u.id= :id");
		query.setParameter("id", id);
		return (UserData) query.getSingleResult();
	}

	public List<User> findAllUsers() {
		return em.createQuery("SELECT u FROM User u JOIN u.userData", User.class).getResultList();
	}

	public List<UserRole> getAllUserRolesByUserId(Long id) {
		try {
			Query query = em.createQuery("SELECT ur FROM UserRole ur JOIN ur.user u WHERE u.id = :id");
			query.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<UserRole> roles = query.getResultList();

			return roles;

		} catch (NoResultException nre) {

		}
		return null;
	}
}
