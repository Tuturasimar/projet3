package fr.isika.cda.viewmodels;

import java.time.LocalDate;

import fr.isika.cda.entities.ar.PartDayEnum;

public class ArDateViewModel {

	private LocalDate date;
	private PartDayEnum partOfDay;
	private boolean remote;
	
	private Long arId;
	
	private Long activityId;
	
	public Long getArId() {
		return arId;
	}
	public void setArId(Long arId) {
		this.arId = arId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public PartDayEnum getPartOfDay() {
		return partOfDay;
	}
	public void setPartOfDay(PartDayEnum partOfDay) {
		this.partOfDay = partOfDay;
	}
	public boolean isRemote() {
		return remote;
	}
	public void setRemote(boolean remote) {
		this.remote = remote;
	}
	
	
}
