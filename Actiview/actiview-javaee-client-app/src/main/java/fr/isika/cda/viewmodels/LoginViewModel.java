package fr.isika.cda.viewmodels;

public class LoginViewModel {

	private String password;
	private String login;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean isValid() {
		return this.login != null 
				&& this.password != null 
				&& !this.login.isBlank()
				&& !this.password.isBlank();
	}
}
