package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.viewmodels.UserViewModel;

@Stateless
public class UserRepository {

	@PersistenceContext
	private EntityManager em;

	public Long registerUser(UserViewModel userVM) {
		
		User user = new User();
		user.setLogin(userVM.getLogin());
		user.setPassword(userVM.getPassword());
		user.setCreatedAt(userVM.getCreatedAt());
		user.setStatus(StatusEnum.ACTIVE);

		UserData data = new UserData();
		data.setFirstname(userVM.getFirstname());
		data.setLastname(userVM.getLastname());
		data.setBirthday(userVM.getBirthday());
		data.setEmail(userVM.getEmail());
		data.setJobEnum(userVM.getJobEnum());

		UserRole role = new UserRole();
		role.setRoleTypeEnum(userVM.getRoleTypeEnum());

		// nécessite d'ajouter une cascade pour la persistence
		em.persist(user);

		return user.getId();
	}
}
