package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Activity;

@Stateless
public class ActivityRepository {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Activity> getAllActivities() {
		
		return em.createQuery("SELECT a FROM Activity a",Activity.class).getResultList();
	}
}
