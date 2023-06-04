package fr.isika.cda.viewmodels;

import java.util.List;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.StateAr;

public class SeeArOfEmployeeViewModel {

	private Long arId;

	private StateAr stateAr;

	private List<ActivityDate> activityDates;

	
	//Getters & setters
	public Long getArId() {
		return arId;
	}

	public void setArId(Long arId) {
		this.arId = arId;
	}

	public StateAr getStateAr() {
		return stateAr;
	}

	public void setStateAr(StateAr stateAr) {
		this.stateAr = stateAr;
	}

	public List<ActivityDate> getActivityDates() {
		return activityDates;
	}

	public void setActivityDates(List<ActivityDate> activityDates) {
		this.activityDates = activityDates;
	}
	
	
}
