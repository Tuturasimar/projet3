package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.ArConfigEnum;

public class FeaturesViewModel {

	private String label;
	private int numberOfColaborators;
	private ArConfigEnum arConfig;
	private float price;
	private int numberOfOptions;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getNumberOfColaborators() {
		return numberOfColaborators;
	}
	public void setNumberOfColaborators(int numberOfColaborators) {
		this.numberOfColaborators = numberOfColaborators;
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
		
}
