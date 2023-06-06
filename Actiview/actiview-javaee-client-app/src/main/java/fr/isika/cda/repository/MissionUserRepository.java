package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.assignement.UserFeedback;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.viewmodels.MissionUserViewModel;

/**
 * Repository pour interagir avec les affectations de missions dans la BDD
 * 
 * @author Trévor
 *
 */
@Stateless
public class MissionUserRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Méthode pour récupérer toutes les attributions de mission des employés selon le login de leur manager
	 * @param managerLogin login du manager
	 * @return List MissionUser
	 */
	@SuppressWarnings("unchecked")
	public List<MissionUser> getAllMissionUserByManagerLogin(String managerLogin) {
		Query query = em.createQuery(
				"SELECT mu FROM MissionUser mu JOIN mu.user u JOIN mu.mission mi WHERE u.manager.login = :login");
		query.setParameter("login", managerLogin);

		return query.getResultList();
	}

	/**
	 * Méthode pour enregistrer une nouvelle attribution de Mission
	 * @param missionUserVm MissionUserViewModel
	 */
	public void register(MissionUserViewModel missionUserVm) {

		MissionUser missionUser = new MissionUser();
		missionUser.setAdr(missionUserVm.getAdr());
		missionUser.setMission(em.getReference(Mission.class, missionUserVm.getMissionId()));
		missionUser.setMissionState(StatusEnum.ACTIVE);
		missionUser.setUser(em.getReference(User.class, missionUserVm.getUserId()));
		missionUser.setUserFeedback(em.getReference(UserFeedback.class, missionUserVm.getUserFeedbackId()));

		em.persist(missionUser);

	}

	/**
	 * Méthode pour vérifier si l'attribution de mission existe déjà
	 * @param missionUserVm MissionUserViewModel
	 * @return true si elle existe | false si elle n'existe pas
	 */
	public boolean checkExistingMissionUser(MissionUserViewModel missionUserVm) {

		try {
			Query query = em.createQuery(
					"SELECT mu FROM MissionUser mu WHERE mu.user.id = :userId AND mu.mission.id = :missionId");
			query.setParameter("userId", missionUserVm.getUserId());
			query.setParameter("missionId", missionUserVm.getMissionId());

			return query.getSingleResult() != null ? true : false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Méthode pour récupérer une MissionUser en fonction de son id
	 * @param id id de la MissionUser
	 * @return MissionUser
	 */
	public MissionUser findMissionUserById(Long id) {
		return (MissionUser) em.createQuery("SELECT mu FROM MissionUser mu WHERE id = " + id).getSingleResult();
	}

	/**
	 * Méthode pour mettre à jour une MissionUser
	 * @param missionUser MissionUser
	 */
	public void updateMissionUser(MissionUser missionUser) {
		em.merge(missionUser);
	}
	
	/**
	 * Méthode pour récupérer les missions en fonction de l'id du user et si l'attribution de mission est active
	 * @param id id du User
	 * @return List Mission
	 */
	@SuppressWarnings("unchecked")
	public List<Mission> findAllAffectedMissionsByUserId(Long id){
		
		try {
			Query query = em.createQuery("SELECT m FROM MissionUser mu JOIN mu.mission m WHERE mu.user.id = :userId AND mu.missionState = :status", Mission.class);
			query.setParameter("userId", id);
			query.setParameter("status", StatusEnum.ACTIVE);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
		
	}

}
