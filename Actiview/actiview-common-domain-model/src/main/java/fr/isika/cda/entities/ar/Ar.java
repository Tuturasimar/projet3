package fr.isika.cda.entities.ar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import fr.isika.cda.entities.common.ArConfigEnum;

@Entity
public class Ar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
	@Enumerated(EnumType.STRING)
	private StateAr StateArEnum;
	
	@Enumerated(EnumType.STRING)
	private ArConfigEnum arConfig;
	
	@OneToMany 
	//Avoids the formation of an intermediate table in BDD
	//Create a foreign key ar_id in the table arActivity
	@JoinColumn(name="ar_id")
	public List<ArActivity> arActivities = new LinkedList<>();
	
	@Column(name="user_id")
	private Long userId ;

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

	public StateAr getStateArEnum() {
		return StateArEnum;
	}

	public void setStateArEnum(StateAr stateArEnum) {
		StateArEnum = stateArEnum;
	}

	public ArConfigEnum getArConfig() {
		return arConfig;
	}

	public void setArConfig(ArConfigEnum arConfig) {
		this.arConfig = arConfig;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	} 
	
	

}
