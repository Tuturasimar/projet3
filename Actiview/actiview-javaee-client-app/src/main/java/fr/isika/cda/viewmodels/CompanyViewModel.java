package fr.isika.cda.viewmodels;

public class CompanyViewModel {

	// Données de la table Company
	private String phone;
	private String siren;
	private String adress;
	private String name;
	
	private final Long companyId;
	
	public CompanyViewModel(Long companyId) {
		this.companyId = companyId;
	}
	public Long getCompanyId() {
		return companyId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSiren() {
		return siren;
	}

	public void setSiren(String siren) {
		this.siren = siren;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
