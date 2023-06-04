package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.viewmodels.CompanyViewModel;

@Stateless
public class CompanyRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Enregistre en bdd une entreprise cliente
	 * 
	 * @param companyVM
	 * @return
	 */
	public Long register(CompanyViewModel companyVM) {
		Company company = new Company();
		company.setAdress(companyVM.getAdress());
		company.setName(companyVM.getName());
		company.setPhone(companyVM.getPhone());
		company.setSiren(companyVM.getSiren());
		// En attendant que le SUPER ADMIN valide, l'entreprise est considérée comme inactive
		company.setStatus(StatusEnum.INACTIVE);
		em.persist(company);

		return company.getId();

	}

	public List<Company> findAll() {
		return em.createQuery("SELECT c FROM Company c", Company.class).getResultList();
	}

	public Company findById(Long id) {
		Company company = new Company();
		company = em.createQuery("SELECT c FROM Company c WHERE c.id = :id",
				Company.class).setParameter("id", id).getSingleResult();
		return company;
	}

	public boolean checkExistingCompany(CompanyViewModel companyVm) {

		try {
			Query query = em.createQuery("SELECT c FROM Company c WHERE c.siren = :siren");
			query.setParameter("siren", companyVm.getSiren());

			return query.getSingleResult() != null ? true : false;
		} catch (Exception e) {
			return false;
		}

	}
	
}
