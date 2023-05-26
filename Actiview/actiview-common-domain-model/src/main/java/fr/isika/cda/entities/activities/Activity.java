package fr.isika.cda.entities.activities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import fr.isika.cda.entities.common.StatusEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

	@Id
	@GeneratedValue
	protected Long id;

	@Column(unique = true)
	protected String labelActivity;

	@Enumerated(EnumType.STRING)
	protected StatusEnum status;

	// id du manager ayant créé la mission
	protected int creatorId;

}
