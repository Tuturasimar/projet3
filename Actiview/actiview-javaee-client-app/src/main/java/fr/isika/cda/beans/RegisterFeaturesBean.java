package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.FeatureRepository;
import fr.isika.cda.viewmodels.FeaturesViewModel;

@ManagedBean
public class RegisterFeaturesBean {

	@Inject
	private FeatureRepository featureRepository;
	
	private FeaturesViewModel featureVm = new FeaturesViewModel();
	
	public void register() {
		featureRepository.register(featureVm);
		clear();
	}
	
	public void clear() {
		featureVm = new FeaturesViewModel();
	}
	
	public void update(Long id) {
		featureRepository.updateFeature(featureVm);
	}
	public FeaturesViewModel getFeatureVm() {
		return featureVm;
	}
	public void setFeatureVm(FeaturesViewModel featureVm) {
		this.featureVm = featureVm;
	}
}
