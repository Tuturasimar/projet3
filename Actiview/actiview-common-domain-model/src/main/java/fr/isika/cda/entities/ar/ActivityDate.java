package fr.isika.cda.entities.ar;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="activity_date")
public class ActivityDate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private PartDayEnum partOfDay;
	
	private boolean remote;
	
	@ManyToOne 
	@JoinColumn(name = "ar_activity_id")
	public ArActivity arActivity;

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

	public ArActivity getArActivity() {
		return arActivity;
	}

	public void setArActivity(ArActivity arActivity) {
		this.arActivity = arActivity;
	}

	public Long getId() {
		return id;
	}
	
	
	

}
