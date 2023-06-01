package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.viewmodels.FeaturesViewModel;


@Stateless
public class FeatureRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Feature> findAll() {

		return em.createQuery("SELECT f FROM Feature f", Feature.class).getResultList();
	}

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
	
	public void updateFeature(FeaturesViewModel featureVm) {
		Feature feature = new Feature();
		feature.setLabel(featureVm.getLabel());

		em.merge(feature);
		
		
	}

}
