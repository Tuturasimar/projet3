package fr.isika.cda.entities.users;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.isika.cda.entities.common.JobEnum;

@Entity

public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String lastname;
	private String firstname;
	private LocalDate birthday;
	private String email;

	@Enumerated(EnumType.STRING)
	private JobEnum jobEnum;

	
	
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

	public Long getId() {
		return id;

	}

}
