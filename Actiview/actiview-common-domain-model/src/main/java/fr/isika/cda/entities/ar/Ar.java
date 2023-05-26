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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.users.User;

@Entity
public class Ar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
	@Enumerated(EnumType.STRING)
	private StateAr StateArEnum;
	
	@Enumerated(EnumType.STRING)
	private ArConfigEnum arConfig;
	
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User user;
	
	@Column(name="user_id")
	private Long userId;
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
