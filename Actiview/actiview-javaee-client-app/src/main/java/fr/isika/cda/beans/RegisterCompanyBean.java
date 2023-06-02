package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.viewmodels.CompanyViewModel;


@ManagedBean
@SessionScoped
public class RegisterCompanyBean {

	@Inject
	CompanyRepository companyRepo;
	
	private CompanyViewModel companyViewModel = new CompanyViewModel();
	
	

	public CompanyRepository getCompanyRepo() {
		return companyRepo;
	}

	public void setCompanyRepo(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}
	public void clear() {
		companyViewModel = new CompanyViewModel();
	}
	public CompanyViewModel getCompanyViewModel() {
		return companyViewModel;
	}

	public void setCompanyViewModel(CompanyViewModel companyViewModel) {
		this.companyViewModel = companyViewModel;
	}
	public String register() {
		if (!companyRepo.checkExistingCompany(companyViewModel)){
			

			companyRepo.register(companyViewModel);

			//TODO ajout d'un message de validation "votre inscription a bien été effectuée"
			// TODO ajout d'une notification au superadmin
		} else {
			// TODO ajout d'un message d'erreur
		}
		return "CompanyList";

	}
	
}
