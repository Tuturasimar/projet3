package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.ImageConfig;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.repository.ImageConfigRepository;
import fr.isika.cda.viewmodels.ImageConfigViewModel;
import fr.isika.cda.viewmodels.ShowCompanyViewModel;


@ManagedBean
public class SeeCompanyProfileBean {
	
	private ShowCompanyViewModel showCompanyVm;
	
	private ImageConfigViewModel imgConfigVm;
	
	@Inject
	private CompanyRepository companyRepo;
	
	@Inject
	private ImageConfigRepository imgConfigRepo;
	
	private Company company;
	
	private ImageConfig imgConfig;
	
	public String showCompanyProfile(Long id) {
		company = companyRepo.findById(id);
		imgConfig = imgConfigRepo.getImageConfigByCompanyId(id);
		
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

	public ImageConfig getImgConfig() {
		return imgConfig;
	}

	public void setImgConfig(ImageConfig imgConfig) {
		this.imgConfig = imgConfig;
	}

	public ImageConfigViewModel getImgConfigVm() {
		return imgConfigVm;
	}

	public void setImgConfigVm(ImageConfigViewModel imgConfigVm) {
		this.imgConfigVm = imgConfigVm;
	}
	
	
	

}
