package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.StatusEnum;

public class MissionUserViewModel {

	private float adr;
	
	private StatusEnum missionState;
	
	private Long missionId;
	
	private Long userId;
	
	private Long userFeedbackId;

	public float getAdr() {
		return adr;
	}

	public void setAdr(float adr) {
		this.adr = adr;
	}

	public StatusEnum getMissionState() {
		return missionState;
	}

	public void setMissionState(StatusEnum missionState) {
		this.missionState = missionState;
	}

	public Long getMissionId() {
		return missionId;
	}

	public void setMissionId(Long missionId) {
		this.missionId = missionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserFeedbackId() {
		return userFeedbackId;
	}

	public void setUserFeedbackId(Long userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}

	
	
	
}
