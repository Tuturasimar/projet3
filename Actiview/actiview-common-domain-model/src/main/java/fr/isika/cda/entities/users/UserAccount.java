package fr.isika.cda.entities.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8632944335273962858L;

	@Id
	@GeneratedValue
	private Long userId;

	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole primaryRole;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private UserProfile userProfile;

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public UserRole getPrimaryRole() {
		return primaryRole;
	}
	public void setPrimaryRole(UserRole primaryRole) {
		this.primaryRole = primaryRole;
	}
	
	public UserAccount withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserAccount withPassword(String password) {
		this.password = password;
		return this;
	}

	public UserAccount withProfile(UserProfile profile) {
		userProfile = profile;
		return this;
	}

	public UserAccount withPrimaryRole(UserRole role) {
		primaryRole = role;
		return this;
	}

	public UserAccount withDefaultPropertiesAndProfile() {
		this.username = DefaultValues.DEFAULT_USERNAME;
		this.password = DefaultValues.DEFAULT_PASSWORD;
		this.userProfile = new UserProfile();
		return this;
	}
	
	class DefaultValues {
		public static final String DEFAULT_USERNAME = "user";
		public static final String DEFAULT_PASSWORD = "password";
		private DefaultValues() {
		}
	}
	
}
