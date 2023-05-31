package fr.isika.cda.entities.assignement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;

@Entity
public class MissionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	@JoinColumn(name="fk_user_id")
    private User user;
	
	@JoinColumn(name="fk_userFeedback_id")
	@OneToOne
	private UserFeedback userFeedback;
	
	@ManyToOne
	@JoinColumn(name="fk_mission_id")
	private Mission mission;
	
	@Enumerated(EnumType.STRING)
    private StatusEnum missionState;
    private float adr;

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusEnum getMissionState() {
        return missionState;
    }

    public void setMissionState(StatusEnum missionState) {
        this.missionState = missionState;
    }

    public float getAdr() {
        return adr;
    }

    public void setAdr(float adr) {
        this.adr = adr;
    }

	public UserFeedback getUserFeedback() {
		return userFeedback;
	}

	public void setUserFeedback(UserFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}
    
    
}

