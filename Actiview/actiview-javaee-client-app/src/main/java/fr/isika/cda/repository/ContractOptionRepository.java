package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.Contract;
import fr.isika.cda.entities.config.ContractOptions;
import fr.isika.cda.entities.config.Options;
import fr.isika.cda.viewmodels.ContractOptionsViewModel;

@Stateless
public class ContractOptionRepository {

	@PersistenceContext
	private EntityManager em;

	public void register(ContractOptionsViewModel contractOptionsVM) {

		for (Long optionId : contractOptionsVM.getOptionsId()) {
			ContractOptions contractOptions = new ContractOptions();
			Contract contract = em.getReference(Contract.class, contractOptionsVM.getContractId());
			Options option = em.getReference(Options.class, optionId);
		
			contractOptions.setContract(contract);
			contractOptions.setOption(option);
			em.persist(contractOptions);
		}
	}
	

}
