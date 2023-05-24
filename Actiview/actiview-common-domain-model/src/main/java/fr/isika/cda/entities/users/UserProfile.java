package fr.isika.cda.entities.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 244166025066426404L;

	@Id
	@GeneratedValue
	private Long id;

	private String firstName = "";
	private String lastName = "";
	private String avatar = "";

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private UserContact contact;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserContact getContact() {
		return contact;
	}

	public void setContact(UserContact contact) {
		this.contact = contact;
	}

	public UserProfile withFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserProfile withLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserProfile withAvatar(final String avatar) {
		this.avatar = avatar;
		return this;
	}

	public UserProfile withContact(final UserContact contact) {
		this.contact = contact;
		return this;
	}
}
