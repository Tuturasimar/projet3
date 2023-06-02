package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.common.StatusEnum;

@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String adress;
	private StatusEnum status;
	private int phone;
	private int siren;
	private String adminJob;
	
	
	@OneToOne
	@JoinColumn(name="contract_id")
	private Contract contract;



	public long getId() {
		return id;
	}

	public Contract getContract() {
		return contract;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public StatusEnum getStatus() {
		return status;
	}

	@Enumerated(EnumType.STRING)
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

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

	public String getAdminJob() {
		return adminJob;
	}

	public void setAdminJob(String adminJob) {
		this.adminJob = adminJob;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
