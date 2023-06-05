package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.repository.UserRepository;

@ManagedBean
public class ShowMissionsBean {
	
	@Inject
	private MissionRepository missionRepo;
	
	@Inject
	private UserRepository userRepo;
	
	private List<Mission> missions;
	
	@PostConstruct
	public void initMissionList() {
		Long companyId = userRepo.findCompanyByUserConnected().getId();
		
		missions = missionRepo.findAllMissionsByCompanyId(companyId);
	}

	public MissionRepository getMissionRepo() {
		return missionRepo;
	}

	public void setMissionRepo(MissionRepository missionRepo) {
		this.missionRepo = missionRepo;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	
	

}
