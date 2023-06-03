package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class EditFormationViewModel {

	private final Long formationId;
	
	private int nbOfDays;
	private String labelFormation;
	private String description;
	private String location;
	private FormationStatusEnum formationState;
	private LocationFormationEnum locationFormation;
	private StatusEnum status;
	
	public EditFormationViewModel(Long formationId ) {
		this.formationId = formationId;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Long getFormationId() {
		return formationId;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	
	
}
