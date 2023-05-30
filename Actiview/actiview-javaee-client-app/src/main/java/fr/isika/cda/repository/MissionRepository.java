package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.viewmodels.EditMissionViewModel;
import fr.isika.cda.viewmodels.MissionViewModel;

@Stateless
public class MissionRepository {

	private static final String SELECT_MISSION_BY_ID_MISSION_PARAM = "SELECT m FROM Mission m WHERE m.id = :idMissionParam";
	
	@PersistenceContext
	private EntityManager em;
	
	public Long register(MissionViewModel missionVm) {
		Mission mission = new Mission();
		mission.setLabelActivity(missionVm.getLabelActivity());
		mission.setMissionStart(missionVm.getMissionStart());
		mission.setMissionEnd(missionVm.getMissionEnd());
		mission.setMissionType(missionVm.getMissionType());
		mission.setMissionState(StatusEnum.ACTIVE);
		mission.setStatus(StatusEnum.ACTIVE);
		em.persist(mission);
		
		return mission.getId();
	}
	
	public Long editMission(EditMissionViewModel missionVm, Long id) {
		Mission missionUpdate = findById(id);
		missionUpdate.setLabelActivity(missionVm.getLabelActivity());
		missionUpdate.setMissionStart(missionVm.getMissionStart());
		missionUpdate.setMissionEnd(missionVm.getMissionEnd());
		missionUpdate.setMissionType(missionVm.getMissionType());
		return missionUpdate.getId(); 
	}

	public List<Mission> findAll() {
		return em.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
	}
	
	public Mission findById(Long id) {
		Mission mission = new Mission();
		mission = em
				.createQuery(SELECT_MISSION_BY_ID_MISSION_PARAM, Mission.class)
				.setParameter("idMissionParam", id)
				.getSingleResult();
		return mission;
	}
	
}
