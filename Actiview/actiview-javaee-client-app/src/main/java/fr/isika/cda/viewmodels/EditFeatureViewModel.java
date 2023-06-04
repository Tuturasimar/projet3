package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.ArConfigEnum;

public class EditFeatureViewModel {

	private final Long featureId;

	private String label;
	private int numberOfCollaborators;
	private ArConfigEnum arConfig;
	private float price;
	private int numberOfOptions;

	public EditFeatureViewModel(Long featureId) {
		this.featureId = featureId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getNumberOfCollaborators() {
		return numberOfCollaborators;
	}

	public void setNumberOfCollaborators(int numberOfCollaborators) {
		this.numberOfCollaborators = numberOfCollaborators;
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

	public int getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(int numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

	public Long getFeatureId() {
		return featureId;
	}

}
