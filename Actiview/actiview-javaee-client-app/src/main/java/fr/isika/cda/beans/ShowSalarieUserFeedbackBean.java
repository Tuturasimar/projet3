package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.repository.UserFeedBackRepository;

@ManagedBean
public class ShowSalarieUserFeedbackBean {

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
		missionUsers = userFeedbackRepo.getAllUserFeedbackByUserConnected();
	}
}
