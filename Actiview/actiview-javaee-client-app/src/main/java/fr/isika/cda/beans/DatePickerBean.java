package fr.isika.cda.beans;

import java.time.LocalDate;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DatePickerBean {

	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
