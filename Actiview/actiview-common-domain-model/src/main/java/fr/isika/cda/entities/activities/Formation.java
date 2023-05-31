package fr.isika.cda.entities.activities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import fr.isika.cda.entities.assignement.FormationUser;
import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Formation extends Activity {

	private int nbOfDays;
	private String location;
	private String description;
	
	Formation formation = new Formation("Développeur Java");

	@Enumerated(EnumType.STRING)
	private FormationStatusEnum formationStatus;
	@Enumerated(EnumType.STRING)
	private LocationFormationEnum locationFormation;
	public Formation(String string) {
	}
	public int getNbOfDays() {
		return nbOfDays;
	}
	public void setNbOfDays(int nbOfDays) {
		this.nbOfDays = nbOfDays;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FormationStatusEnum getFormationStatus() {
		return formationStatus;
	}
	public void setFormationStatus(FormationStatusEnum formationStatus) {
		this.formationStatus = formationStatus;
	}
	public LocationFormationEnum getLocationFormation() {
		return locationFormation;
	}
	public void setLocationFormation(LocationFormationEnum locationFormation) {
		this.locationFormation = locationFormation;
	}

}
