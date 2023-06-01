package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.assignement.UserFeedback;

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
}
