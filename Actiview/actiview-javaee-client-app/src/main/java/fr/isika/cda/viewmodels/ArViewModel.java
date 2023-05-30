package fr.isika.cda.viewmodels;

import java.time.LocalDate;

import fr.isika.cda.entities.ar.Ar;

public class ArViewModel {

	private LocalDate date = LocalDate.now();
	
	private Long userId;
	
	private Ar ar;

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Ar getAr() {
		return ar;
	}
	
	public void setAr(Ar ar) {
		this.ar = ar;
	}
	
	
	
}
