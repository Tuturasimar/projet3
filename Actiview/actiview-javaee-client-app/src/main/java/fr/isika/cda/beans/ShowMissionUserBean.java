package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.repository.MissionUserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class ShowMissionUserBean {

	@Inject
	private MissionUserRepository missionUserRepo;

	private List<MissionUser> missionsUser;

	@PostConstruct
	public void init() {
		
		missionsUser = missionUserRepo.getAllMissionUserByManagerLogin(SessionUtils.getUserLoginFromSession());
	}

	public List<MissionUser> getMissionsUser() {
		return missionsUser;
	}

	public void setMissionsUser(List<MissionUser> missionsUser) {
		this.missionsUser = missionsUser;
	}
	
	public String desactivateAffectation(Long id) {
		MissionUser missionUser = missionUserRepo.findMissionUserById(id);
		
		if(missionUser != null) {
			missionUser.setMissionState(StatusEnum.INACTIVE);
			missionUserRepo.updateMissionUser(missionUser);
		}
		init();
		
		return "missionUserList";
	}
	
	
}
