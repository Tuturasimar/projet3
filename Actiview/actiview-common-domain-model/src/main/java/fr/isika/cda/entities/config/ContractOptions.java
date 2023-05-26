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
	private long id;
	
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;
	
	@ManyToOne
	@JoinColumn(name="option_id")
	private Options option;

	public long getId() {
		return id;
	}

	public Contract getContract() {
		return contract;
	}

	public Options getOption() {
		return option;
	}
	

}