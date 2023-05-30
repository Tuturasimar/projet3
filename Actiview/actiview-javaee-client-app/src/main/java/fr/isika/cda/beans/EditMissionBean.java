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

	private EditMissionViewModel editMissionVm = new EditMissionViewModel();

	@Inject
	private MissionRepository missionRepository;

	private Mission missionToUpdate;

	public void clear() {
		editMissionVm = new EditMissionViewModel();
	}

	public String showEditMission(Long id) {
		missionToUpdate = missionRepository.findById(id);
		return "editMission.xhtml";
	}

	public String editMission() {
		missionRepository.editMission(editMissionVm, missionToUpdate.getId());
		clear();
		return "missionList.xhtml";
	}

	public EditMissionViewModel getEditMissionVm() {
		return editMissionVm;
	}

	public void setEditMissionVm(EditMissionViewModel editMissionVm) {
		this.editMissionVm = editMissionVm;
	}

}
