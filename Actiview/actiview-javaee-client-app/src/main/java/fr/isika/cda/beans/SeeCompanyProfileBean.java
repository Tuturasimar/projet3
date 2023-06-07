package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.viewmodels.ShowCompanyViewModel;


@ManagedBean
public class SeeCompanyProfileBean {
	
	private ShowCompanyViewModel showCompanyVm;
	
	@Inject
	private CompanyRepository companyRepo;
	
	private Company company;
	
	public String showCompanyProfile(Long id) {
		Company companyProfile = companyRepo.findById(id);
		showCompanyVm = new ShowCompanyViewModel(companyProfile.getId());
		showCompanyVm.setAdress(companyProfile.getAdress());
		showCompanyVm.setName(companyProfile.getName());
		showCompanyVm.setPhone(companyProfile.getPhone());
		showCompanyVm.setSiren(companyProfile.getSiren());
		
		return "SeeProfileCompany.xhtml";
	}

	public ShowCompanyViewModel getCompanyVm() {
		return showCompanyVm;
	}

	public void setCompanyVm(ShowCompanyViewModel companyVm) {
		this.showCompanyVm = companyVm;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

}
