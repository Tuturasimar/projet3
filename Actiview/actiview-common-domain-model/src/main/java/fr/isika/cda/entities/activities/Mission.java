package fr.isika.cda.entities.activities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.common.MissionTypeEnum;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Mission extends Activity {

	private LocalDate missionStart;
	private LocalDate missionEnd;
	
	Mission Mission = new Mission("Maintenance informatique");
	
	@Enumerated(EnumType.STRING)
	private MissionTypeEnum missionType;
	

	@Enumerated(EnumType.STRING)
	private StatusEnum missionState;
	
	public Mission(String string) {

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
	
	
	

}
