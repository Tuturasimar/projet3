package fr.isika.cda.entities.activities;



import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.common.StatusEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

	@Id
	@GeneratedValue
	protected Long activityId;

	@Column(unique = true)
	protected String labelActivity;

	@Enumerated(EnumType.STRING)
	protected StatusEnum status;
	
	// id du manager ayant créé la mission
	protected int creatorId;
	
	
	
	
	
}
