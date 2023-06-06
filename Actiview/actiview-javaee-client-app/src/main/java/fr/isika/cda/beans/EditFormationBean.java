package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.viewmodels.EditFormationViewModel;

@ManagedBean
@SessionScoped
public class EditFormationBean {

	private EditFormationViewModel editFormaVm;
	
	@Inject 
	FormationRepository formationRepo;
	
	public EditFormationViewModel getEditFormaVm() {
		return editFormaVm;
	}
	
	public void setEditFormaVm(EditFormationViewModel editFormaVm) {
		this.editFormaVm = editFormaVm;
	}
	
	/**
	 * Méthode permettant le pré-remplissage du formulaire d'édition
	 * @param id Id de la formation
	 * @return String (La vue que l'on souhaite afficher)
	 */
	public String showEditFormation(Long id) {
		Formation formationToUpdate = formationRepo.findById(id);
		
		if(formationToUpdate != null) {
			editFormaVm = new EditFormationViewModel(formationToUpdate.getId());
			editFormaVm.setDescription(formationToUpdate.getDescription());
			editFormaVm.setFormationState(formationToUpdate.getFormationStatus());
			editFormaVm.setLabelFormation(formationToUpdate.getLabelActivity());
			editFormaVm.setLocation(formationToUpdate.getLocation());
			editFormaVm.setLocationFormation(formationToUpdate.getLocationFormation());
			editFormaVm.setNbOfDays(formationToUpdate.getNbOfDays());
			editFormaVm.setStatus(formationToUpdate.getStatus());
			
		}
		return "UpdateFormation.xhtml";
	}
	
	/**
	 * Méthode pour enregistrer les modifications
	 * @return String (La vue que l'on souhaite afficher)
	 */
	public String editFormation() {
		formationRepo.updateFormation(editFormaVm);
		return "FormationList";
	}
}
