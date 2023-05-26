package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.repository.MissionRepository;

@ManagedBean
public class ShowMissionsBean {
	
	@Inject
	private MissionRepository missionRepo;
	
	private List<Mission> missions;
	
	@PostConstruct
	public void initMissionList() {
		missions = missionRepo.findAllMissions();
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
