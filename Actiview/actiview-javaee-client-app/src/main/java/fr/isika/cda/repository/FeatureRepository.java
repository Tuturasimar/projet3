package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.viewmodels.EditFeatureViewModel;
import fr.isika.cda.viewmodels.FeaturesViewModel;


@Stateless
public class FeatureRepository {

	private static final String SELECT_FEATURE_BY_ID_FEATURE_PARAM = "SELECT f FROM Feature f WHERE f.id = :idFeatureParam";
	@PersistenceContext
	private EntityManager em;

	
	public List<Feature> findAll() {

		return em.createQuery("SELECT f FROM Feature f", Feature.class).getResultList();
	}

	/**
	 * Méthode pour enregistrer un nouveau forfait
	 * @param featureVm FeaturesViewModel
	 * @return Long (id de la Feature)
	 */
	public Long register(FeaturesViewModel featureVm) {
		// TODO Auto-generated method stub
		
		Feature feature = new Feature();
		
		feature.setArConfig(ArConfigEnum.MONTH);
		feature.setLabel(featureVm.getLabel());
		feature.setNbOfCollaborators(featureVm.getNumberOfCollaborators());
		feature.setNbOfOptions(featureVm.getNumberOfOptions());
		feature.setPrice(featureVm.getPrice());
		
		em.persist(feature);
		
		return feature.getId();
	}
	
	/**
	 * Méthode pour mettre à jour la Feature
	 * @param featureVm EditFeatureViewModel
	 */
	public void updateFeature(EditFeatureViewModel featureVm) {
		Feature feature = findByFeatureId(featureVm.getFeatureId());
		feature.setLabel(featureVm.getLabel());
		feature.setArConfig(featureVm.getArConfig());
		feature.setNbOfCollaborators(featureVm.getNumberOfCollaborators());
		feature.setNbOfOptions(featureVm.getNumberOfOptions());
		feature.setPrice(featureVm.getPrice());
		
	}

	/**
	 * Méthode pour récupérer une Feature en fonction de son id
	 * @param id id de la Feature
	 * @return Feature
	 */
	public Feature findByFeatureId(Long id) {
		// TODO Auto-generated method stub
		Feature feature = new Feature();
		feature = em
				.createQuery(SELECT_FEATURE_BY_ID_FEATURE_PARAM, Feature.class)
				.setParameter("idFeatureParam", id)
				.getSingleResult();
		return feature;
		
	}

}
