package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.repository.UserFeedBackRepository;

@ManagedBean
public class ShowSalarieUserFeedbackBean {

	@Inject
	private UserFeedBackRepository userFeedbackRepo;
	
	private List<MissionUser> missionUsers;
	
	
}
