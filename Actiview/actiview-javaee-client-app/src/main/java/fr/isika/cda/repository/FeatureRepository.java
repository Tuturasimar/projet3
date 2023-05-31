package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.Feature;


@Stateless
public class FeatureRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Feature> findAll() {

		return em.createQuery("SELECT f FROM Feature f", Feature.class).getResultList();
	}

}
