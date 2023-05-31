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
import javax.persistence.OneToOne;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
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
    private List<Mission> missions;
    private List<Formation> formations;
    private Mission mission;
    private Formation formation;

    User user = new User();
    


	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@OneToOne
	@JoinColumn(name = "fk_userdata_id", unique = true)
	private UserData userData;
	
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
	 * Il faut utiliser cette méthode pour pouvoir ajouter un cra dans la liste et non faire un user.GetArs.add
	 * @param ar le cra à ajouter
	 * @return
	 */
	public boolean addAr(Ar ar) {
		return this.ars.add(ar);
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public void assignMission(Mission mission) {
        missions.add(mission);
    }

    // Méthode pour attribuer une formation à l'utilisateur
    public void assignFormation(Formation formation) {
        formations.add(formation);
    }

    
    public List<Mission> getMissions() {
        return missions;
    }

    public List<Formation> getFormations() {
        return formations;
    }
    
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}



