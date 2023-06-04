package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.repository.FeatureRepository;
import fr.isika.cda.viewmodels.EditFeatureViewModel;
import fr.isika.cda.viewmodels.FeaturesViewModel;

@ManagedBean
@SessionScoped
public class UpdateFeatureBean {

	private EditFeatureViewModel editFeatureViewModel;

	@Inject
	private FeatureRepository featureRepo;

	public String showUpdateFeature(Long id) {

		Feature featureToUpdate = featureRepo.findByFeatureId(id);
		
		if(featureToUpdate != null) {
		
			editFeatureViewModel = new EditFeatureViewModel(featureToUpdate.getId());
			
		editFeatureViewModel.setLabel(featureToUpdate.getLabel());
		editFeatureViewModel.setNumberOfCollaborators(featureToUpdate.getNbOfCollaborators());
		editFeatureViewModel.setPrice(featureToUpdate.getPrice());
		editFeatureViewModel.setNumberOfOptions(featureToUpdate.getNbOfOptions());
		editFeatureViewModel.setArConfig(featureToUpdate.getArConfig());
		
		}
		return "UpdateFeature.xhtml";
	}

	public String updateFeature() {
		
		return "FeatureList.xhtml";	
	}

	public EditFeatureViewModel getEditFeatureViewModel() {
		return editFeatureViewModel;
	}

	public void setEditFeatureViewModel(EditFeatureViewModel editFeatureViewModel) {
		this.editFeatureViewModel = editFeatureViewModel;
	}

	public FeatureRepository getFeatureRepo() {
		return featureRepo;
	}

	public void setFeatureRepo(FeatureRepository featureRepo) {
		this.featureRepo = featureRepo;
	}

}
