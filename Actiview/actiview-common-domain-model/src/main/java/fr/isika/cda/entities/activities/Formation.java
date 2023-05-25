package fr.isika.cda.entities.activities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;

@Entity
@PrimaryKeyJoinColumn(name = "id_activity")
public class Formation extends Activity {

	private int nbOfDays;
	
	@Enumerated(EnumType.STRING)
	private FormationStatusEnum formationStatus;
	@Enumerated(EnumType.STRING)
	private LocationFormationEnum locationFormation;
	
	private String location;
	private String description;
}
