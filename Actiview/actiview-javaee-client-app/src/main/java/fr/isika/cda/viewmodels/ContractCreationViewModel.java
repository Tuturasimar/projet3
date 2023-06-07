package fr.isika.cda.viewmodels;

import java.time.LocalDate;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.Feature;

public class ContractCreationViewModel {

	//Données relatives à la company
	private Company company;
	
	//Données relatives au contract
	private Long contractId;
	private float price;
	private String description;
	
	private StatusEnum status;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private LocalDate canBeChangedAt;
	
	//Données relatives à la feature
	private Long featureId;
	private Feature feature;
	

	
	//Getters & setters

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDate getCanBeChangedAt() {
		return canBeChangedAt;
	}

	public void setCanBeChangedAt(LocalDate canBeChangedAt) {
		this.canBeChangedAt = canBeChangedAt;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}
}
