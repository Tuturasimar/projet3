package fr.isika.cda.entities.activities;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import fr.isika.cda.entities.common.StatusEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(unique = true)
	protected String labelActivity;

	@Enumerated(EnumType.STRING)
	protected StatusEnum status;
	
	// id du manager ayant créé la mission
	protected Long creatorId;

	public String getLabelActivity() {
		return labelActivity;
	}

	public void setLabelActivity(String labelActivity) {
		this.labelActivity = labelActivity;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getId() {
		return id;
	}

	// Ce toString permet l'affichage dans une liste déroulante, ne pas supprimer
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(labelActivity);
		return builder.toString();
	}
	
	
	
	
	
	
	
}
