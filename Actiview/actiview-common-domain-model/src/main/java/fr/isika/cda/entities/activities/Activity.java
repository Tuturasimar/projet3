package fr.isika.cda.entities.activities;

import fr.isika.cda.entities.common.StatusEnum;

public abstract class Activity {

	private String labelActivity;
	private StatusEnum status;
	
	// relation avec un userId (table User) pour savoir quel manager a créé cette activité
}
