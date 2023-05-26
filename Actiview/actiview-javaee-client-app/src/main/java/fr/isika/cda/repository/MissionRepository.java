package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.viewmodels.MissionViewModel;

@Stateless
public class MissionRepository {

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
	
	public List<Mission> findAllMissions() {
		return em.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
	}
}
