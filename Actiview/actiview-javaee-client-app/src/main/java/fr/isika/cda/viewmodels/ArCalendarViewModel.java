package fr.isika.cda.viewmodels;

import java.time.LocalDate;
import java.util.List;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.StateAr;

public class ArCalendarViewModel {

	private Long arId;
	private LocalDate createdAt;
	private LocalDate updatedAt;

	private StateAr stateAr;

	private List<ActivityDate> activityDates;

	// Getters & setters
	public Long getArId() {
		return arId;
	}

	public void setArId(Long arId) {
		this.arId = arId;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
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
