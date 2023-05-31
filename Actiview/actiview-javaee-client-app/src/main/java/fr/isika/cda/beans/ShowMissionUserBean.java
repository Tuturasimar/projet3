package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.repository.MissionUserRepository;

@ManagedBean
public class ShowMissionUserBean {

	@Inject
	private MissionUserRepository missionUserRepo;

	private List<MissionUser> missionsUser;

	@PostConstruct
	public void init() {
		// TODO : Changer l'id par celui connecté (manager)
		missionsUser = missionUserRepo.getAllMissionUserByManagerId(1L);
	}

	public List<MissionUser> getMissionsUser() {
		return missionsUser;
	}

	public void setMissionsUser(List<MissionUser> missionsUser) {
		this.missionsUser = missionsUser;
	}
}
