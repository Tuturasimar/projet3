package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.entities.common.ArConfigEnum;

@Entity
public class Feature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long featureId;

	private int nbOfCollaborators;
	
	@Enumerated(EnumType.STRING)
	private ArConfigEnum arConfig;
	
	private float price;
	private int nbOfOptions;


	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}

	public int getNbOfCollaborators() {
		return nbOfCollaborators;
	}

	public void setNbOfCollaborators(int nbOfCollaborators) {
		this.nbOfCollaborators = nbOfCollaborators;
	}

	public ArConfigEnum getArConfig() {
		return arConfig;
	}

	public void setArConfig(ArConfigEnum arConfig) {
		this.arConfig = arConfig;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNbOfOptions() {
		return nbOfOptions;
	}

	public void setNbOfOptions(int nbOfOptions) {
		this.nbOfOptions = nbOfOptions;
	}

}
