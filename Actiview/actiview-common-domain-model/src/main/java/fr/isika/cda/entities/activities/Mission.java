package fr.isika.cda.entities.activities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import fr.isika.cda.entities.common.MissionTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

@Entity
@PrimaryKeyJoinColumn(name = "id_activity")
public class Mission extends Activity {

	private LocalDate missionStart;
	private LocalDate missionEnd;
	
	@Enumerated(EnumType.STRING)
	private MissionTypeEnum missionType;
	@Enumerated(EnumType.STRING)
	private StatusEnum missionState;

}
