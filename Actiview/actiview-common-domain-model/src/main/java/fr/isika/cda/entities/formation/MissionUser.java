package fr.isika.cda.entities.formation;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    public User user;
	
	@OneToOne
	public UserFeedback userFeedback;
	
	@Enumerated
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
}

