package fr.isika.cda.viewmodels;

public class UpdatePasswordViewModel {

	private Long userId ;
	private String password;
	
	public UpdatePasswordViewModel(Long id) {
		this.userId = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
