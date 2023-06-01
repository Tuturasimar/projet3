package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.assignement.FormationUser;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.viewmodels.FormationUserViewModel;

@Stateless
public class FormationUserRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<FormationUser> getAllFormationUserByManagerLogin(String userLoginFromSession) {
		Query query = em.createQuery(
				"SELECT fu FROM FormationUser fu JOIN fu.user u JOIN fu.formation f WHERE u.manager.login = :login ");
		query.setParameter("login", userLoginFromSession);

		return query.getResultList();
	}

	public void register(FormationUserViewModel formationUserVm) {

		FormationUser formationUser = new FormationUser();
		formationUser.setFormation(em.getReference(Formation.class, formationUserVm.getFormationId()));
		formationUser.setUser(em.getReference(User.class, formationUserVm.getUserId()));
		formationUser.setFormationState(StatusEnum.ACTIVE);
		
		em.persist(formationUser);
	}

	public FormationUser findFormationUserById(Long id) {
		return (FormationUser) em.createQuery("SELECT fu FROM FormationUser fu WHERE id = " + id).getSingleResult();
	}

	public void updateFormationUser(FormationUser formationUser) {
		em.merge(formationUser);

	}

	public boolean checkExistingFormationUser(FormationUserViewModel formationUserVm) {

		try {
			Query query = em.createQuery(
					"SELECT fu FROM FormationUser fu WHERE fu.user.id = :userId AND fu.formation.id = :formationId");
			query.setParameter("userId", formationUserVm.getUserId());
			query.setParameter("formationId", formationUserVm.getFormationId());

			return query.getSingleResult() != null ? true : false;
		} catch (Exception e) {
			return false;
		}

	}

}
