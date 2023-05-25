package fr.isika.cda.entities.formation;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;

@Entity
public class FormationUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
    private User user;
    
    @ManyToOne
    @JoinColumn(name="activity_id")
    public Formation formation;
    
    @Enumerated(EnumType.STRING)
    private StatusEnum formationState;

   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusEnum getFormationState() {
        return formationState;
    }

    public void setFormationState(StatusEnum formationState) {
        this.formationState = formationState;
    }
}

