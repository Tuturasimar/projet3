package fr.isika.cda.beans;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import fr.isika.cda.entities.ar.PartDayEnum;

@Named
@ApplicationScoped
public class EnumData {
	
	public PartDayEnum[] getPartDay() {
		return PartDayEnum.values();
	}

}
