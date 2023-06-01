package fr.isika.cda.viewmodels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class UpdateUserViewModel {

	// Données de la table User
	private Long id;
	private String password;
	private StatusEnum status;
	private String managerId;

	// Données de la table Userdata
	private String lastname;
	private String firstname;
	private LocalDate birthday;
	private String email;
	private JobEnum jobEnum;

	// Donnée de la table UserRole
		private List<RoleTypeEnum> roleTypes = new ArrayList<RoleTypeEnum>();

	public UpdateUserViewModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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

	public JobEnum getJobEnum() {
		return jobEnum;
	}

	public void setJobEnum(JobEnum jobEnum) {
		this.jobEnum = jobEnum;
	}

	public List<RoleTypeEnum> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<RoleTypeEnum> roleTypes) {
		this.roleTypes = roleTypes;
	}
}
