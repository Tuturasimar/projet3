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
	
	public int getIndex(int index) {
		return index;
	}

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

	public void update(int index, MissionUser missionUser) {

		missionUsers.set(index, missionUser);
	}
	

	public void saveChanges(int index) {
		
		
		
		MissionUser missionUser = missionUsers.get(index);
		
	    userFeedbackRepo.update(missionUser.getUserFeedback());
		
		init();
	}
}
