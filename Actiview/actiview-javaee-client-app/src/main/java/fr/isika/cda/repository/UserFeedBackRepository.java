package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.assignement.UserFeedback;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.utils.SessionUtils;

@Stateless
public class UserFeedBackRepository {

	@PersistenceContext
	EntityManager em;
	
	public Long createBlankOne() {
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setGradeClientRelation(0);
		userFeedback.setGradeManager(0);
		userFeedback.setGradeMission(0);
		userFeedback.setGradeUserComfort(0);
		
		
		em.persist(userFeedback);
		
		return userFeedback.getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<MissionUser> getAllActiveUserFeedbackByUserConnected(){
		
		Query query = em.createQuery("SELECT mu FROM MissionUser mu JOIN mu.mission m JOIN mu.userFeedback uf WHERE mu.missionState = :active AND mu.user.id = :userId ");
		query.setParameter("active", StatusEnum.ACTIVE);
		query.setParameter("userId", SessionUtils.getUserIdFromSession());
		
		return query.getResultList();
	}
	
	public void update(UserFeedback userFeedback) {
		UserFeedback oldUserFeedback = findById(userFeedback.getId());
		oldUserFeedback.setComment(userFeedback.getComment());
		oldUserFeedback.setGradeClientRelation(userFeedback.getGradeClientRelation());
		oldUserFeedback.setGradeManager(userFeedback.getGradeManager());
		oldUserFeedback.setGradeMission(userFeedback.getGradeMission());
		oldUserFeedback.setGradeUserComfort(userFeedback.getGradeUserComfort());
	}
	
	public UserFeedback findById(Long id) {
		
		Query query = em.createQuery("SELECT uf FROM UserFeedback uf WHERE uf.id = :id", UserFeedback.class);
		query.setParameter("id", id);
		
		return (UserFeedback) query.getSingleResult();
	}
}
