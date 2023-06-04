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

	private float adr;

	private List<MissionUser> missionsUser;

	// Pour gérer facilement des conditions d'apparition côté front
	private final StatusEnum ACTIVE = StatusEnum.ACTIVE;
	private final StatusEnum INACTIVE = StatusEnum.INACTIVE;

	public StatusEnum getACTIVE() {
		return ACTIVE;
	}

	public StatusEnum getINACTIVE() {
		return INACTIVE;
	}

	public float getAdr() {
		return adr;
	}

	public void setAdr(float adr) {
		this.adr = adr;
	}

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

	public String changeAffectation(Long id) {
		MissionUser missionUser = missionUserRepo.findMissionUserById(id);

		if (missionUser != null) {
			if (missionUser.getMissionState() == StatusEnum.ACTIVE) {
				missionUser.setMissionState(StatusEnum.INACTIVE);
			} else {
				missionUser.setMissionState(StatusEnum.ACTIVE);
			}
			missionUserRepo.updateMissionUser(missionUser);
		}
		init();

		return "missionUserList";
	}

	public String editAdr(Long id) {

		// On cherche la missionUser en fonction de son Id
		MissionUser missionUser = missionUserRepo.findMissionUserById(id);
		// On change son TJM
		missionUser.setAdr(adr);
		// On valide les modifs
		missionUserRepo.updateMissionUser(missionUser);
		
		init();

		return "missionUserList";
	}

}
