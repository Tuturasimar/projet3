package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;

public class FormationViewModel {

	
	private int nbOfDays;
	private String labelFormation;
	private FormationStatusEnum formationState;
	private LocationFormationEnum locationFormation;
	
	public int getNbOfDays() {
		return nbOfDays;
	}
	public void setNbOfDays(int nbOfDays) {
		this.nbOfDays = nbOfDays;
	}
	public String getLabelFormation() {
		return labelFormation;
	}
	public void setLabelFormation(String labelFormation) {
		this.labelFormation = labelFormation;
	}
	public FormationStatusEnum getFormationState() {
		return formationState;
	}
	public void setFormationState(FormationStatusEnum formationState) {
		this.formationState = formationState;
	}
	public LocationFormationEnum getLocationFormation() {
		return locationFormation;
	}
	public void setLocationFormation(LocationFormationEnum locationFormation) {
		this.locationFormation = locationFormation;
	}
	
	
}
