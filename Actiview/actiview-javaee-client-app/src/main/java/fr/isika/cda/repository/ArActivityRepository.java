package fr.isika.cda.repository;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Activity;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.ArActivity;

@Stateless
/**
 * Repository lié à la classe intermédiaire entre le CRA, l'activité associée et
 * l'ensemble des ActivityDate
 * 
 * @author Trévor
 *
 */

public class ArActivityRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Méthode de création d'un ArActivity
	 * @param arId id du Ar associé
	 * @param activityId id de l'Activity associée
	 * @return Long (l'id de l'Ar nouvellement créé)
	 */
	public Long register(Long arId, Long activityId) {

		ArActivity arActivity = new ArActivity();
		
		Ar ar = em.getReference(Ar.class, arId);
		Activity activity = em.getReference(Activity.class, activityId);
		
		arActivity.setAr(ar);
		arActivity.setActivity(activity);

		em.persist(arActivity);

		return arActivity.getId();

	}

	/**
	 * Méthode vérifiant l'existence d'un ArActivity
	 * @param arId L'id de l'Ar actuel
	 * @param activityId L'id de l'Activity associée
	 * @return arActivity (s'il le trouve) , sinon null
	 */
	public ArActivity alreadyExist(Long arId, Long activityId) {
		
		ArActivity arActivity = null;
		
		try {
			Query query = em.createQuery(
					"SELECT ara FROM ArActivity ara WHERE activity_id = :activityId AND ar_id = :arId");
			query.setParameter("activityId", activityId);
			query.setParameter("arId", arId);

			arActivity = (ArActivity) query.getSingleResult();
		} catch(NoResultException nre) {
			
		}

		if (arActivity != null) {
			return arActivity;
		}

		return null;
	}
	
	
}
