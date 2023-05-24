package fr.isika.cda.entities.config;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.entities.common.StatusEnum;

@Entity
public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long contractId;

	private char label;
	private float price;
	private String description;
	private StatusEnum status;

	private LocalDate createdAt;
	private LocalDate updatedAt;
	private LocalDate canBeChangedAt;


	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public char getLabel() {
		return label;
	}

	public void setLabel(char label) {
		this.label = label;
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

}
