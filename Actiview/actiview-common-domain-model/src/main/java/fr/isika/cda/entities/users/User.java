package fr.isika.cda.entities.users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@ManyToOne
	@JoinColumn(name = "fk_manager_id")
	private User manager;

	@OneToMany
	@JoinColumn(name = "fk_user_id")
	// crée une colonne user_id dans la table ar
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public List<Ar> getArs() {
		return Collections.unmodifiableList(ars);
	}
	
	/**
	 * @param ar
	 * @return
	 */
	public boolean addAr(Ar ar) {
		return this.ars.add(ar);
	}
}
