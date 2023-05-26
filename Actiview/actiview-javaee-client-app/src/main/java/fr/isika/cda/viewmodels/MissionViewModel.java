package fr.isika.cda.viewmodels;

import java.time.LocalDate;

public class MissionViewModel {
	
	private LocalDate missionStart;
	private LocalDate missionEnd;
	
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
	
	

}
