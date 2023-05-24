package fr.isika.cda.entities.activities;

import java.time.LocalDate;

import fr.isika.cda.entities.common.MissionTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class Mission extends Activity {

	private LocalDate missionStart;
	private LocalDate missionEnd;
	private MissionTypeEnum missionType;
	private StatusEnum missionState;
	
}
