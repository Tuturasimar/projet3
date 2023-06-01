package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.repository.FeatureRepository;

@ManagedBean
public class ShowFeaturesBean {

	@Inject
	private FeatureRepository featureRepo;

	private List<Feature> feature;

	@PostConstruct
	public void initFeaturesList() {
		feature = featureRepo.findAll();
	}

	public List<Feature> getFeature() {
		return feature;
	}

	public void setFeature(List<Feature> feature) {
		this.feature = feature;
	}

}
