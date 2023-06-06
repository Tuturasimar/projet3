package fr.isika.cda.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.viewmodels.ArDateViewModel;

@Stateless
/**
 * Repository des ActivityDate
 * 
 * @author Trévor
 *
 */
public class ActivityDateRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Méthode de création d'une activityDate
	 * 
	 * @param arDateVm     viewModel contenant les informations essentielles pour la
	 *                     création
	 * @param arActivityId id de l'ArActivity, faisant le lien entre l'activityDate,
	 *                     l'Ar et l'Activity
	 */
	public void createActivityDate(ArDateViewModel arDateVm, Long arActivityId) {

		ActivityDate activityDate = new ActivityDate();
		activityDate.setDate(arDateVm.getDate());
		activityDate.setPartOfDay(arDateVm.getPartOfDay());
		activityDate.setRemote(arDateVm.isRemote());

		ArActivity arActivity = em.getReference(ArActivity.class, arActivityId);

		activityDate.setArActivity(arActivity);

		em.persist(activityDate);
	}

	/**
	 * Méthode pour récupérer l'ensemble des ActivityDate par l'Id du CRA
	 * @param arActivityId l'ID du CRA
	 * @return Une liste d'ActivityDate
	 */
	public List<ActivityDate> getAllActivityDateByArActivityId(Long arActivityId) {

		try {
			Query query = em.createQuery("SELECT ad FROM ActivityDate ad WHERE ad.arActivity = :id ",
					ActivityDate.class);
			query.setParameter("id", arActivityId);

			@SuppressWarnings("unchecked")
			List<ActivityDate> activityDates = query.getResultList();

			return activityDates;

		} catch (NoResultException nre) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * Méthode allant chercher toutes les ActivityDate en fonction de l'id d'un Ar
	 * @param arId L'id de l'Ar que l'on souhaite
	 * @return Une liste d'ActivityDate
	 */
	public List<ActivityDate> getAllActivityDateByArId(Long arId) {

		try {

			// Exemple d'une requête avec triple jointure
			Query query = em.createQuery(
					"SELECT ad FROM ActivityDate ad "   // On utilise un alias pour ne pas devoir réécrire ActivityDate
					+ "JOIN ad.arActivity ara "			// Jointure + alias. arActivity est un attribut d'ActivityDate
					+ "JOIN ara.ar ar "					// ar est un attribut d'ArActivity
					+ "WHERE ar.id = :arId "
					+ "ORDER BY ad.date ASC");
			query.setParameter("arId", arId);

			@SuppressWarnings("unchecked")
			List<ActivityDate> activityDates = query.getResultList();

			return activityDates;

		} catch (NoResultException nre) {
			// TODO: handle exception
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityDate> getMissionsDateByActivityId(Long activityId) {
		Query query= em.createQuery("SELECT ad FROM ActivityDate ad "
				+ "JOIN ad.arActivity ara " //arActivtity = attribut de ad
				+ "JOIN ara.activity a " // a =attribut de l'activité dans arAvitivty
				+ "WHERE a.id = :aId");
		query.setParameter("aId", activityId);
		
		List<ActivityDate> missions = query.getResultList();
		return missions;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityDate> getFormationsDateByActivityId(Long activityId) {
		Query query= em.createQuery("SELECT ad FROM ActivityDate ad "
				+ "JOIN ad.arActivity ara " //arActivtity = attribut de ad
				+ "JOIN ara.activity a " // a =attribut de l'activité dans arAvitivty
				+ "WHERE a.id = :aId");
		query.setParameter("aId", activityId);
		
		List<ActivityDate> formations = query.getResultList();
		return formations;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityDate> getAbsencesDateByActivityId(Long activityId) {
		Query query= em.createQuery("SELECT ad FROM ActivityDate ad "
				+ "JOIN ad.arActivity ara " //arActivtity = attribut de ad
				+ "JOIN ara.activity a " // a =attribut de l'activité dans arAvitivty
				+ "WHERE a.id = :aId");
		query.setParameter("aId", activityId);
		
		List<ActivityDate> absences = query.getResultList();
		return absences;
	}
	
	/**
	 * Méthode qui permet de récupérer le label de l'activité liée à une activityDate dont on passe l'id 
	 * @param id : id de l'activityDate
	 * @return : Label de l'activité correspondante
	 */
	public String getActivityLabelFromActivityDate(Long id) {
		Query query = em.createQuery(
				"SELECT ad from ActivityDate ad " 
				+"JOIN ad.arActivity ara " 
				+"JOIN ara.activity a "
				+"WHERE ad.id = :id");
		query.setParameter("id", id);
		ActivityDate activityDate = (ActivityDate) query.getSingleResult();		
		return activityDate.arActivity.getActivity().getLabelActivity();
	}
	
	/**
	 * Méthode de suppresion d'une ActivityDate
	 * @param activityDate ActivityDate
	 */
	public void delete(ActivityDate activityDate) {
		
		em.remove(em.contains(activityDate) ? activityDate : em.merge(activityDate));
		
	}

	public List<ActivityDate> getActivityDateDependingOnPartOfDay(List<ActivityDate> activityDates, PartDayEnum partOfDay) {
		List<ActivityDate> resultActivityDates = new ArrayList<ActivityDate>();
		for(ActivityDate activityDate : activityDates) {
			if (activityDate.getPartOfDay() == partOfDay) {
				resultActivityDates.add(activityDate);
			}
		}
		return resultActivityDates;
	}

	

	



}
