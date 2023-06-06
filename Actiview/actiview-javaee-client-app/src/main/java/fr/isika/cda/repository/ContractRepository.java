package fr.isika.cda.repository;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.Contract;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.entities.config.Options;
import fr.isika.cda.viewmodels.ContractCreationViewModel;

@Stateless
public class ContractRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Long register(ContractCreationViewModel contractVM) {
		Contract contract = new Contract();
		Feature feature = contractVM.getFeature();
		contract.setFeature(feature);
		contract.setPrice(feature.getPrice());
		contract.setDescription(feature.getLabel());
		contract.setCreatedAt(LocalDate.now());
		contract.setUpdatedAt(LocalDate.now());
		contract.setCanBeChangedAt(LocalDate.now().plusMonths(6));
		contract.setStatus(StatusEnum.ACTIVE);
		
		Company company = contractVM.getCompany();
		company.setContract(contract);
		
		entityManager.merge(company);
		//entityManager.persist(contract);
		
		return contract.getId();
	}

	public List<Options> findOptions(long id) {
		try {
		return entityManager
				.createQuery("SELECT o FROM ContractOptions opt JOIN opt.option o WHERE opt.contract.id = :contractIdParam", Options.class)
				.setParameter("contractIdParam", id)
				.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public Feature findFeature(Long idFeature) {
		
		try {
			return entityManager
					.createQuery("SELECT f FROM Contract c JOIN c.feature f WHERE f.id = :id", Feature.class)
					.setParameter("id", idFeature)
					.getSingleResult();
			} catch(NoResultException e) {
				return null;
			}
		
	}
	
}
