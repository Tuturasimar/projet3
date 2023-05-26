package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.viewmodels.MissionViewModel;

@Stateless
public class MissionRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Long register(MissionViewModel missionVm) {
		Mission mission = new Mission();
		mission.setMissionStart(missionVm.getMissionStart());
		mission.setMissionEnd(missionVm.getMissionEnd());
		em.persist(mission);
		return 1L;
	}
}
