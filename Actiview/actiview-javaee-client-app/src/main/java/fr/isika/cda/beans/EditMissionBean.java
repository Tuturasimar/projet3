package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.viewmodels.EditMissionViewModel;

@ManagedBean
@SessionScoped
public class EditMissionBean {

	private EditMissionViewModel editMissionVm;

	@Inject
	private MissionRepository missionRepository;

	/**
	 * Méthode permettant le pré-remplissage du formulaire d'édition
	 * @param id Id de la mission
	 * @return String (La vue que l'on souhaite afficher)
	 */
	public String showEditMission(Long id) {
		Mission missionToUpdate = missionRepository.findById(id);
		if(missionToUpdate != null) {
			// mapper l'entité à modifier dans le editMissionVm
			editMissionVm = new EditMissionViewModel(missionToUpdate.getId());
			
			editMissionVm.setLabelActivity(missionToUpdate.getLabelActivity());
			editMissionVm.setMissionStart(missionToUpdate.getMissionStart());
			editMissionVm.setMissionEnd(missionToUpdate.getMissionEnd());
			editMissionVm.setMissionState(missionToUpdate.getStatus());
			editMissionVm.setMissionType(missionToUpdate.getMissionType());
		}
		return "editMission.xhtml";
	}

	/**
	 * Méthode pour enregistrer les modifications
	 * @return String (La vue que l'on souhaite afficher)
	 */
	public String editMission() {
		missionRepository.editMission(editMissionVm);
		return "missionList.xhtml";
	}

	public EditMissionViewModel getEditMissionVm() {
		return editMissionVm;
	}

	public void setEditMissionVm(EditMissionViewModel editMissionVm) {
		this.editMissionVm = editMissionVm;
	}

}
