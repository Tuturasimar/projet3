package fr.isika.cda.viewmodels;

import java.time.LocalDate;


public class CompanyViewModel {

	// Données de la table Company
	private int phone;
	private int siren;
	private String adress;
	private String name;
	private String adminJob;

	// Données de la table Userdata
	private String lastname;
	private String firstname;
	private LocalDate birthday;
	private String email;
	

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getSiren() {
		return siren;
	}

	public void setSiren(int siren) {
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

	public void setBrandName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminJob() {
		return adminJob;
	}

	public void setAdminJob(String adminJob) {
		this.adminJob = adminJob;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	

	

}
