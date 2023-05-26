package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.ar.ArActivity;

@Stateless
/**
 * Repository lié à la classe intermédiaire entre le CRA, l'activité associée et
 * l'ensemble des ActivityDate
 * 
 * @author Trévor
 *
 */
@NamedQuery(name = "findArActivityByForeignKeyId", query = "SELECT ara FROM ar_activity ara WHERE activity_id = :activityId AND ar_id = :arId")
public class ArActivityRepository {

	@PersistenceContext
	private EntityManager em;

	public Long register(Long arId, Long activityId) {

		ArActivity arActivity = new ArActivity();
		arActivity.setArId(arId);
		arActivity.setActivityId(activityId);;

		em.persist(arActivity);

		return arActivity.getId();

	}
	

	public ArActivity alreadyExist(Long arId, Long activityId) {

		ArActivity arActivity = em.createNamedQuery("findArActivityByForeignKeyId", ArActivity.class)
				.setParameter(arId.toString(), activityId.toString()).getSingleResult();

		if(arActivity != null) {
			return arActivity;
		}
		
		return null;
		
	}
}
