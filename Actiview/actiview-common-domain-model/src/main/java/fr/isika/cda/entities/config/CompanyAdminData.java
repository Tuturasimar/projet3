package fr.isika.cda.entities.config;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyAdminData {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String lastname;
		private String firstname;
		private LocalDate birthday;
		private String email;
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
		public Long getId() {
			return id;
		}
		


	}


