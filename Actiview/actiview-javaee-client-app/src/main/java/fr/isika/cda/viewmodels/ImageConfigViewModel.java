package fr.isika.cda.viewmodels;

public class ImageConfigViewModel {
	
	private String logo;
	private String banner;
	
	private String templateChoice;
	
	private Long companyId;
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getTemplateChoice() {
		return templateChoice;
	}
	public void setTemplateChoice(String templateChoice) {
		this.templateChoice = templateChoice;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	

}
