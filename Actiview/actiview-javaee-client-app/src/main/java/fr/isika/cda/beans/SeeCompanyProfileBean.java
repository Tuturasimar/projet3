package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.viewmodels.CompanyViewModel;

@ManagedBean
public class SeeCompanyProfileBean {
	
	private CompanyViewModel companyVm;
	
	@Inject
	private CompanyRepository companyRepo;
	
	private Company company;
	
	public String showCompanyProfile(Long id) {
		Company companyProfile = companyRepo.findById(id);
		companyVm = new CompanyViewModel(companyProfile.getId());
		companyVm.setAdress(companyProfile.getAdress());
		companyVm.setName(companyProfile.getName());
		companyVm.setPhone(companyProfile.getPhone());
		companyVm.setSiren(companyProfile.getSiren());
		
		return "SeeProfileCompany.xhtml";
	}

	public CompanyViewModel getCompanyVm() {
		return companyVm;
	}

	public void setCompanyVm(CompanyViewModel companyVm) {
		this.companyVm = companyVm;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

}
