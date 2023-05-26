package fr.isika.cda.entities.users;

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

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.common.StatusEnum;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;
	private String password;
	private LocalDate createdAt;

	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@ManyToOne
	@JoinColumn(name="manager_id", insertable=false, updatable=false)
	private User manager ;
	
	@Column(name="manager_id")
	private Long managerId;
	
	
	@OneToMany
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	//crée une colonne user_id dans la table ar
	public List<Ar> ars = new LinkedList<>();
	
	public void setLogin(String login) {
		this.login = login;
	}

	public Long getId() {
		return id;

	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

}
