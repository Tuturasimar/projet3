package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.viewmodels.FormationViewModel;



@ManagedBean
public class RegisterFormation {

	@Inject
	private FormationRepository formationRepository;
	
	private FormationViewModel formationVm = new FormationViewModel();

	public void register() {
		formationRepository.register(formationVm);
		clear();
	}

	public void clear() {
		formationVm = new FormationViewModel();
	}
	
	public void update(Long id) {
		formationRepository.updateFormation(formationVm);
	}

	public FormationViewModel getFormationVm() {
		return formationVm;
	}

	public void setFormationVm(FormationViewModel formationVm) {
		this.formationVm = formationVm;
	}
	
}
