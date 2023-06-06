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
	 * @return Long (L'id de la company)
	 */
	public Long register(CompanyViewModel companyVM) {
		Company company = new Company();
		company.setAdress(companyVM.getAdress());
		company.setName(companyVM.getName());
		company.setPhone(companyVM.getPhone());
		company.setSiren(companyVM.getSiren());
		// L'entreprise est activée directement (pour l'instant)
		company.setStatus(StatusEnum.ACTIVE);
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

	/**
	 * Méthode pour vérifier si la Company existe déjà en BDD
	 * @param companyVm CompanyViewModel
	 * @return true si la Company existe | false si la Company n'existe pas
	 */
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
