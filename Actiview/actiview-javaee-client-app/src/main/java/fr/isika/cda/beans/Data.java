package fr.isika.cda.beans;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;
import fr.isika.cda.entities.common.MissionTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

@Named
@ApplicationScoped
public class Data {
	
	public MissionTypeEnum[] getMissionType() {
		return MissionTypeEnum.values();
	}
	
	public StatusEnum[] getStatusEnum() {
		return StatusEnum.values();
	}
	
	public FormationStatusEnum[] getFormationStatus() {
		return FormationStatusEnum.values();
	}
	
	public LocationFormationEnum[] getLocationFormation() {
		return LocationFormationEnum.values();
	}

}
