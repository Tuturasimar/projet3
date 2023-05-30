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

	public String showEditMission(Long id) {
		Mission missionToUpdate = missionRepository.findById(id);
		if(missionToUpdate != null) {
			// mapper l'entité à modifier dans le editMissionVm
			editMissionVm = new EditMissionViewModel(missionToUpdate.getId());
			editMissionVm.setLabelActivity(missionToUpdate.getLabelActivity());
		}
		return "editMission.xhtml";
	}

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
