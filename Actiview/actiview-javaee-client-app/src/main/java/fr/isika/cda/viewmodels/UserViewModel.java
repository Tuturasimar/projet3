package fr.isika.cda.viewmodels;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;

public class UserViewModel {

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// Données de la table User
	private String login;
	private String password;
	private LocalDate createdAt;
	private StatusEnum status;
	private List<Ar> ars = new LinkedList<>();
	private Long managerId;

	// Données de la table Userdata
	private String lastname;
	private String firstname;
	private LocalDate birthday;
	private String email;
	private JobEnum jobEnum;
	// utilisée à la fois pour la table UserData et UserRole
	private Long userId;

	// Donnée de la table UserRole
	private RoleTypeEnum roleTypeEnum;

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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Ar> getArs() {
		return ars;
	}

	public void setArs(List<Ar> ars) {
		this.ars = ars;
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

	public RoleTypeEnum getRoleTypeEnum() {
		return roleTypeEnum;
	}

	public void setRoleTypeEnum(RoleTypeEnum roleTypeEnum) {
		this.roleTypeEnum = roleTypeEnum;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "UserViewModel [login=" + login + ", password=" + password + ", createdAt=" + createdAt + ", status="
				+ status + ", ars=" + ars + ", managerId=" + managerId + ", lastname=" + lastname + ", firstname="
				+ firstname + ", birthday=" + birthday + ", email=" + email + ", jobEnum=" + jobEnum + ", roleTypeEnum="
				+ roleTypeEnum + "]";
	}

}
