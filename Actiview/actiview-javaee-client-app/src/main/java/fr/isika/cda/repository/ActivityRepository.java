package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Absence;
import fr.isika.cda.entities.activities.Activity;
import fr.isika.cda.entities.activities.CustomActivity;

@Stateless
public class ActivityRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Activity> getAllActivities() {

		return em.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
	}

	public List<Absence> getAllAbsences() {

		return em.createQuery("SELECT ab FROM Absence ab", Absence.class).getResultList();
	}
	
	public List<CustomActivity> getAllCustomActivities(){
		
		return em.createQuery("SELECT ca FROM CustomActivity ca",CustomActivity.class).getResultList();
	}
}
