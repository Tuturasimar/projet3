package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;

import fr.isika.cda.viewmodels.LoginViewModel;

@ManagedBean
public class LoginBean {

	private LoginViewModel loginVm = new LoginViewModel();
	
	public LoginViewModel getLoginVm() {
		return loginVm;
	}
	public void setLoginVm(LoginViewModel loginVm) {
		this.loginVm = loginVm;
	}
	
	public void login () {
		System.out.println(loginVm.getLogin()+" "+loginVm.getPassword());
	}
	
	
}
