package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ContractOptions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long contractOptionsId;
	
	@ManyToOne
	@JoinColumn(name="contractId")
	private Contract contract;
	
	@ManyToOne
	@JoinColumn(name="optionId")
	private Option option;
	

}
