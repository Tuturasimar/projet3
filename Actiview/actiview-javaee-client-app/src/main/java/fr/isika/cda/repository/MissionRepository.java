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
	
	public Mission findById(Long id) {
		Mission mission = new Mission();
		mission = em
				.createQuery(SELECT_MISSION_BY_ID_MISSION_PARAM, Mission.class)
				.setParameter("idMissionParam", id)
				.getSingleResult();
		return mission;
	}
	
	@SuppressWarnings("unchecked")
	public List<Mission> getAllActiveMissionsFromCompany(Long companyId){
		Query query = em.createQuery("SELECT m FROM Mission m JOIN m.creator u WHERE m.status = :active AND u.company.id = :companyId", Mission.class);
		query.setParameter("active", StatusEnum.ACTIVE);
		query.setParameter("companyId", companyId );
		
		return query.getResultList();
	}
	
}
