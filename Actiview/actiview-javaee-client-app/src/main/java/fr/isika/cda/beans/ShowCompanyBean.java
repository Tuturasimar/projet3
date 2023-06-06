package fr.isika.cda.beans;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.repository.CompanyRepository;


@ManagedBean
public class ShowCompanyBean {
	
	@Inject
	private CompanyRepository companyRepo;
	
	private List<Company> companies;
	
	@PostConstruct
	public void initCompanyList() {
		companies = companyRepo.findAll();
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public CompanyRepository getCompanyRepo() {
		return companyRepo;
	}

	public void setCompanyRepo(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}
	

}
