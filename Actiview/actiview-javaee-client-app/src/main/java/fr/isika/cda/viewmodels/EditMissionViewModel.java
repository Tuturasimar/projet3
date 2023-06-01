package fr.isika.cda.viewmodels;

import java.time.LocalDate;


import fr.isika.cda.entities.common.MissionTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class EditMissionViewModel {
	
	private final Long missionId;
	
	private LocalDate missionStart;
	private LocalDate missionEnd;
	private String labelActivity;
	private MissionTypeEnum missionType;
	private StatusEnum missionState;
	
	public EditMissionViewModel(Long missionId) {
		this.missionId = missionId;
	}
	public Long getMissionId() {
		return missionId;
	}
	public LocalDate getMissionStart() {
		return missionStart;
	}
	public void setMissionStart(LocalDate missionStart) {
		this.missionStart = missionStart;
	}
	public LocalDate getMissionEnd() {
		return missionEnd;
	}
	public void setMissionEnd(LocalDate missionEnd) {
		this.missionEnd = missionEnd;
	}
	public MissionTypeEnum getMissionType() {
		return missionType;
	}
	public void setMissionType(MissionTypeEnum missionType) {
		this.missionType = missionType;
	}
	public StatusEnum getMissionState() {
		return missionState;
	}
	public void setMissionState(StatusEnum missionState) {
		this.missionState = missionState;
	}
	public String getLabelActivity() {
		return labelActivity;
	}
	public void setLabelActivity(String labelActivity) {
		this.labelActivity = labelActivity;
	}
	
	

}
