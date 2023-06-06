package fr.isika.cda.entities.config;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;

@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String adress;
	
	private String phone;
	private String siren;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@OneToOne
	@JoinColumn(name="contract_id")
	private Contract contract;
	
	@OneToMany
	@JoinColumn(name="fk_company_id")
	List<User> users;


	public long getId() {
		return id;
	}

	public Contract getContract() {
		return contract;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
