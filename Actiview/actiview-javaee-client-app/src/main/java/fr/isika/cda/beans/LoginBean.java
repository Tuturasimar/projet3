package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.LoginViewModel;

@ManagedBean
@SessionScoped
public class LoginBean {

	@Inject
	private UserRepository userRepository;

	private LoginViewModel loginVm = new LoginViewModel();

	public String login() {
		// 1 - vérifier que les données saisies sont valides
		if (loginVm.isValid()) {

			// 2 - Vérifier que le user existe dans le système
			User user = userRepository.checkIfUserExists(loginVm);
			if (user != null) {
				// 3 - Si oui => mémoriser le user en session
				SessionUtils.setUserLoginIntoSession(user.getLogin());
				SessionUtils.setUserIdIntoSession(user.getId());

				// TODO Check ici si celui qui se connecte est superAdmin
				if (isUserASuperAdmin()) {
					return "indexAdmin.xhtml";
				} else {
					return "homePageConnected";
				}
				// Sinon rediriger vers homePageConnected

			} else {
				System.err.println("user not found with data : " + loginVm);
				return "LoginView";
			}
		} else {
			// 4 - Sinon => Erreurs sur le frmulaire + rester sur la même page
			System.err.println("user login data are not valid : " + loginVm);
			return "LoginView";
		}
	}

	public String logout() {
		SessionUtils.resetSession();
		return "index";
	}

	public boolean isUserLoggedIn() {
		return SessionUtils.isUserLoggedIn();
	}

	public boolean isUserASalarie() {
		return userRepository.isUserASalarie(SessionUtils.getUserIdFromSession());
	}

	public boolean isUserAManager() {
		return userRepository.isUserLoggedAManager();
	}

	public boolean isUserAEsnAdmin() {
		return userRepository.isUserLoggedAnEsnAdmin();
	}

	public boolean isUserASuperAdmin() {
		return userRepository.isUserLoggedASuperAdmin();
	}

	public Long getUserConnectedId() {
		return SessionUtils.getUserIdFromSession();
	}

	public LoginViewModel getLoginVm() {
		return loginVm;
	}

	public void setLoginVm(LoginViewModel loginVm) {
		this.loginVm = loginVm;
	}
}
