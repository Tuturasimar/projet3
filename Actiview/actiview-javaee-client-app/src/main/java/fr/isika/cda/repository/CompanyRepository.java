package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.CompanyAdminData;
import fr.isika.cda.viewmodels.CompanyViewModel;

@Stateless
public class CompanyRepository {

	private static final String SELECT_COMPANY_BY_ID_COMPANY_PARAM = "SELECT m FROM Mission m WHERE m.id = :idMissionParam";
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
		company.setAdminJob(companyVM.getAdminJob());

		em.persist(company);

		CompanyAdminData data = new CompanyAdminData();
		data.setFirstname(companyVM.getFirstname());
		data.setLastname(companyVM.getLastname());
		data.setBirthday(companyVM.getBirthday());
		data.setEmail(companyVM.getEmail());

		em.persist(data);

		return company.getId();

	}

	public List<Company> findAll() {
		return em.createQuery("SELECT c FROM Company c", Company.class).getResultList();
	}

	public Company findById(Long id) {
		Company company = new Company();
		company = em.createQuery(fr.isika.cda.repository.CompanyRepository.SELECT_COMPANY_BY_ID_COMPANY_PARAM,
				Company.class).setParameter("idCompanyParam", id).getSingleResult();
		return company;
	}

	public boolean checkExistingCompany(CompanyViewModel companyVm) {

		try {
			Query query = em.createQuery("SELECT c FROM Company WHERE c.company.siren = : siren ");
			query.setParameter("siren", companyVm.getSiren());

			return query.getSingleResult() != null ? true : false;
		} catch (Exception e) {
			return false;
		}

	}

}
