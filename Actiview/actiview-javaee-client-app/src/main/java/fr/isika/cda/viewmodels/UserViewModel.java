package fr.isika.cda.viewmodels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class UserViewModel {

	// Données de la table User
	private String login;
	private String password;
	private LocalDateTime createdAt = LocalDateTime.now();
	
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

	// Getters & Setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<RoleTypeEnum> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<RoleTypeEnum> roleTypes) {
		this.roleTypes = roleTypes;
	}
	

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserViewModel [login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", status=");
		builder.append(status);
		builder.append(", managerId=");
		builder.append(managerId);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", email=");
		builder.append(email);
		builder.append(", jobEnum=");
		builder.append(jobEnum);
		builder.append(", roles=");
		builder.append(roleTypes);
		builder.append("]");
		return builder.toString();
	}
	
}
