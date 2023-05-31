package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.viewmodels.FormationViewModel;



@ManagedBean
public class RegisterFormation {

	private FormationViewModel formationVm = new FormationViewModel();
	@Inject
	private FormationRepository formationRepository;
	
	public void register() {
		Long id = formationRepository.register(formationVm);
		System.out.println("La formation a bien été créée avec l'id: " + id);
		clear();
		
	}
	public void clear() {
		formationVm = new FormationViewModel();
	}
	public void updateFormation(Long id) {
		System.out.println("La formation à mettre à jour est : " + id);
	}

	public FormationViewModel getFormationVm() {
		return formationVm;
	}

	public void setFormationVm(FormationViewModel formationVm) {
		this.formationVm = formationVm;
	}
	
}
