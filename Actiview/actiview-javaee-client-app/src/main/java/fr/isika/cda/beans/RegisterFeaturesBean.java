package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.repository.FeatureRepository;
import fr.isika.cda.viewmodels.FeaturesViewModel;

@ManagedBean
@SessionScoped
public class RegisterFeaturesBean {

	@Inject
	private FeatureRepository featureRepository;
	
	private FeaturesViewModel featureVm = new FeaturesViewModel();
	
	public String register() {
		featureRepository.register(featureVm);
		clear();
		return "FeatureList";
	}
	
	public void clear() {
		featureVm = new FeaturesViewModel();
	}
	
	public FeaturesViewModel getFeatureVm() {
		return featureVm;
	}
	public void setFeatureVm(FeaturesViewModel featureVm) {
		this.featureVm = featureVm;
	}
}
