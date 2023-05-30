package fr.isika.cda.beans;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import fr.isika.cda.entities.common.MissionTypeEnum;

@Named
@ApplicationScoped
public class Data {
	
	public MissionTypeEnum[] getMissionType() {
		return MissionTypeEnum.values();
	}

}
