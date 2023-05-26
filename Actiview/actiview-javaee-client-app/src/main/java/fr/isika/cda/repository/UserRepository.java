package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

		UserData data = new UserData();

		UserRole role = new UserRole();

		// nécessite d'ajouter une cascade pour la persistence
		em.persist(user);

		return null;
	}
}
