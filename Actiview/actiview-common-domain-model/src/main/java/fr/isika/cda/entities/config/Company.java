package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.entities.common.StatusEnum;

@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long companyId;

	private String name;
	private String adress;
	private StatusEnum status;


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

}
