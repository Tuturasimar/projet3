package fr.isika.cda.entities.activities;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;

public class Formation extends Activity {

	private int nbOfDays;
	private FormationStatusEnum formationStatus;
	private LocationFormationEnum locationFormation;
	private String location;
	private String description;
}
