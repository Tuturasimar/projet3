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
 * @author Trévor
 *
 */
@Stateless
public class MissionUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<MissionUser> getAllMissionUserByManagerLogin(String managerLogin){
		Query query =  em.createQuery("SELECT mu FROM MissionUser mu JOIN mu.user u JOIN mu.mission mi WHERE u.manager.login = :login");
		query.setParameter("login", managerLogin);
		
		return query.getResultList();
	}


	public void register(MissionUserViewModel missionUserVm) {
		
		MissionUser missionUser = new MissionUser();
		missionUser.setAdr(missionUserVm.getAdr());
		missionUser.setMission(em.getReference(Mission.class, missionUserVm.getMissionId()));
		missionUser.setMissionState(StatusEnum.ACTIVE);
		missionUser.setUser(em.getReference(User.class, missionUserVm.getUserId()));
		missionUser.setUserFeedback(em.getReference(UserFeedback.class, missionUserVm.getUserFeedbackId()));
		
		em.persist(missionUser);
		
	}

}
