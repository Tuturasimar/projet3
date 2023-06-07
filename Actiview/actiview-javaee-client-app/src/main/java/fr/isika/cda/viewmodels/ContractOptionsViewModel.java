package fr.isika.cda.viewmodels;

import java.util.List;

import fr.isika.cda.entities.config.Contract;

public class ContractOptionsViewModel {

	private Long contractId;
	private List<Long> optionsId;
	
	
	//Donnée dans Contract et Feature
	private Contract contract;
	private int nbOfOptions;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}


	public List<Long> getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(List<Long> optionsId) {
		this.optionsId = optionsId;
	}

	public int getNbOfOptions() {
		return nbOfOptions;
	}

	public void setNbOfOptions(int nbOfOptions) {
		this.nbOfOptions = nbOfOptions;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
