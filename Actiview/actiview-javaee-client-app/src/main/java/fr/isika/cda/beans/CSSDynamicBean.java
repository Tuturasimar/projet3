package fr.isika.cda.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.repository.CSSRepository;
import fr.isika.cda.repository.UserRepository;

@ManagedBean
public class CSSDynamicBean {
	
	@Inject
	private CSSRepository CSSRepo;
	
	@Inject
	private UserRepository userRepo;
	
	private ColorConfig colorConfig;
	
	@PostConstruct
	public void init() {
		Long companyId = userRepo.findCompanyByUserConnected().getId();
		setColorConfig(CSSRepo.getColorConfigByCompanyId(companyId));
	}
	
	public String getBackgroundColor() {
		String backgroundColor = "#" + colorConfig.getBackgroundColor();
		return backgroundColor;
	}
	
	public String getColorTitle() {
		String colorTitle = "#" + colorConfig.getTitleColor();
		return colorTitle;
	}
	
	public String getColorText() {
		String colorText = "#" + colorConfig.getTextColor();
		return colorText;
	}
	
	public String getColorButton() {
		String colorButton = "#" + colorConfig.getButtonColor();
		return colorButton;
	}
	

	public CSSRepository getCSSRepo() {
		return CSSRepo;
	}

	public void setCSSRepo(CSSRepository cSSRepo) {
		CSSRepo = cSSRepo;
	}

	public ColorConfig getColorConfig() {
		return colorConfig;
	}

	public void setColorConfig(ColorConfig colorConfig) {
		this.colorConfig = colorConfig;
	}
	
	
	

}
