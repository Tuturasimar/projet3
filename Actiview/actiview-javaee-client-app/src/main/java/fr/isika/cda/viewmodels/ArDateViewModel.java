package fr.isika.cda.viewmodels;

import java.time.LocalDate;

import fr.isika.cda.entities.ar.PartDayEnum;

public class ArDateViewModel {

	private LocalDate date;
	private PartDayEnum partOfDay;
	private boolean remote;
	
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
