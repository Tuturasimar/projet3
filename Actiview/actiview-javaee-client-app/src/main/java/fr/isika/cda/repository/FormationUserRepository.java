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

	/**
	 * Méthode pour récupérer toutes les attributions de formation en fonction du login du manager connecté
	 * @param userLoginFromSession Login du manager connecté
	 * @return List de FormationUser
	 */
	@SuppressWarnings("unchecked")
	public List<FormationUser> getAllFormationUserByManagerLogin(String userLoginFromSession) {
		Query query = em.createQuery(
				"SELECT fu FROM FormationUser fu JOIN fu.user u JOIN fu.formation f WHERE u.manager.login = :login ");
		query.setParameter("login", userLoginFromSession);

		return query.getResultList();
	}

	/**
	 * Méthode pour enregistrer une nouvelle attribution de formation en BDD
	 * @param formationUserVm FormationUserViewModel
	 */
	public void register(FormationUserViewModel formationUserVm) {

		FormationUser formationUser = new FormationUser();
		formationUser.setFormation(em.getReference(Formation.class, formationUserVm.getFormationId()));
		formationUser.setUser(em.getReference(User.class, formationUserVm.getUserId()));
		formationUser.setFormationState(StatusEnum.ACTIVE);
		
		em.persist(formationUser);
	}

	/**
	 * Méthode pour récupérer l'attribution de formation par son Id
	 * @param id id du FormationUser
	 * @return FormationUser
	 */
	public FormationUser findFormationUserById(Long id) {
		return (FormationUser) em.createQuery("SELECT fu FROM FormationUser fu WHERE id = " + id).getSingleResult();
	}

	/**
	 * Méthode pour mettre à jour l'attribution de formation
	 * @param formationUser FormationUser
	 */
	public void updateFormationUser(FormationUser formationUser) {
		em.merge(formationUser);

	}

	/**
	 * Méthode pour vérifier si le FormationUser existe déjà en BDD ou non
	 * @param formationUserVm FormationUserViewModel
	 * @return true si le FormationUser est déjà lié au même User et à la même Formation | false sinon
	 * 
	 */
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

	/**
	 * Méthode pour récupérer toutes les attributions de formations de l'utilisateur connecté
	 * @param userConnectedId id du User
	 * @return List Formation
	 */
	@SuppressWarnings("unchecked")
	public List<Formation> findAllAffectedFormationsByUserId(Long userConnectedId) {
		try {
			Query query = em.createQuery("SELECT f FROM FormationUser fu JOIN fu.formation f WHERE fu.user.id = :userId AND fu.formationState = :status", Formation.class);
			query.setParameter("userId", userConnectedId);
			query.setParameter("status", StatusEnum.ACTIVE);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
