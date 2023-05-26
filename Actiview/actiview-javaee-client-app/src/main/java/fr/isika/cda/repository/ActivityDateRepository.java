package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.viewmodels.ArDateViewModel;

@Stateless
public class ActivityDateRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void createActivityDate(ArDateViewModel arDateVm, Long arActivityId) {
		
		ActivityDate activityDate = new ActivityDate();
		activityDate.setDate(arDateVm.getDate());
		activityDate.setPartOfDay(arDateVm.getPartOfDay());
		activityDate.setRemote(arDateVm.isRemote());
		
		ArActivity arActivity = em.getReference(ArActivity.class, arActivityId);
		
		activityDate.setArActivity(arActivity);
		
		em.persist(activityDate);
	}
	
}