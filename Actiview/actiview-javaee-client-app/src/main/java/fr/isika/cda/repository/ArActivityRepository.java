package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public ArActivity register(Ar ar, Activity Activity) {

		ArActivity arActivity = new ArActivity();
		arActivity.setAr(ar);
		arActivity.setActivity(Activity);

		em.persist(arActivity);

		return arActivity;

	}
}
