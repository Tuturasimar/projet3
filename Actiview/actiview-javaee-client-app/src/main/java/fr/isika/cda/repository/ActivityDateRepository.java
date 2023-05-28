package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.ArActivity;
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
					+ "WHERE ar.id = :arId");
			query.setParameter("arId", arId);

			@SuppressWarnings("unchecked")
			List<ActivityDate> activityDates = query.getResultList();

			return activityDates;

		} catch (NoResultException nre) {
			// TODO: handle exception
		}

		return null;
	}

}
