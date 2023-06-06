package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.EditMissionViewModel;
import fr.isika.cda.viewmodels.MissionViewModel;

@Stateless
public class MissionRepository {

	private static final String SELECT_MISSION_BY_ID_MISSION_PARAM = "SELECT m FROM Mission m WHERE m.id = :idMissionParam";
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Méthode pour enregistrer une nouvelle mission
	 * @param missionVm MissionViewModel
	 * @return Long (id de la Mission)
	 */
	public Long register(MissionViewModel missionVm) {
		Mission mission = new Mission();
		mission.setLabelActivity(missionVm.getLabelActivity());
		mission.setMissionStart(missionVm.getMissionStart());
		mission.setMissionEnd(missionVm.getMissionEnd());
		mission.setMissionType(missionVm.getMissionType());
		mission.setStatus(StatusEnum.ACTIVE);
		mission.setCreator(em.getReference(User.class, SessionUtils.getUserIdFromSession()));
		em.persist(mission);
		
		return mission.getId();
	}
	
	/**
	 * Méthode pour mettre à jour une Mission en BDD
	 * @param missionVm EditMissionViewModel
	 * @return Long (id de la Mission mise à jour
	 */
	public Long editMission(EditMissionViewModel missionVm) {
		Mission missionUpdate = findById(missionVm.getMissionId());
		missionUpdate.setLabelActivity(missionVm.getLabelActivity());
		missionUpdate.setMissionStart(missionVm.getMissionStart());
		missionUpdate.setMissionEnd(missionVm.getMissionEnd());
		missionUpdate.setMissionType(missionVm.getMissionType());
		missionUpdate.setStatus(missionVm.getMissionState());
		return missionUpdate.getId(); 
	}

	
	public List<Mission> findAll() {
		return em.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
	}
	
	/**
	 * Méthode pour récupérer une mission en fonction de son id
	 * @param id id de la Mission
	 * @return Mission
	 */
	public Mission findById(Long id) {
		Mission mission = new Mission();
		mission = em
				.createQuery(SELECT_MISSION_BY_ID_MISSION_PARAM, Mission.class)
				.setParameter("idMissionParam", id)
				.getSingleResult();
		return mission;
	}
	
	/**
	 * Méthode pour récupérer les missions actives de la Company
	 * @param companyId id de la Company
	 * @return List Mission
	 */
	@SuppressWarnings("unchecked")
	public List<Mission> getAllActiveMissionsFromCompany(Long companyId){
		Query query = em.createQuery("SELECT m FROM Mission m JOIN m.creator u WHERE m.status = :active AND u.company.id = :companyId", Mission.class);
		query.setParameter("active", StatusEnum.ACTIVE);
		query.setParameter("companyId", companyId );
		
		return query.getResultList();
	}

	/**
	 * Méthode pour récupérer toutes les missions en fonction de l'id de la Company
	 * @param companyId id de la Company
	 * @return List Mission
	 */
	@SuppressWarnings("unchecked")
	public List<Mission> findAllMissionsByCompanyId(Long companyId) {
		Query query = em.createQuery("SELECT m FROM Mission m JOIN m.creator u WHERE u.company.id = :companyId", Mission.class);
		query.setParameter("companyId", companyId);
		
		return query.getResultList();
	}
	
}
