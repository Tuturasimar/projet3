package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.repository.UserFeedBackRepository;

@ManagedBean
@ViewScoped
public class UserFeedbackBean {

	@Inject
	private UserFeedBackRepository userFeedbackRepo;

	private List<MissionUser> missionUsers;

	public List<MissionUser> getMissionUsers() {
		return missionUsers;
	}

	public void setMissionUsers(List<MissionUser> missionUsers) {
		this.missionUsers = missionUsers;
	}

	@PostConstruct
	public void init() {
		missionUsers = userFeedbackRepo.getAllActiveUserFeedbackByUserConnected();
	}

	public void updateGrade(int index, MissionUser missionUser) {

		missionUsers.set(index, missionUser);
	}

	public void saveChanges() {
		for (MissionUser missionUser : missionUsers) {
			userFeedbackRepo.update(missionUser.getUserFeedback());
		}
		init();
	}
}
