package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UserViewModel;


@ManagedBean
public class UpdateUserBean {
	
	private UserViewModel userViewModel = new UserViewModel();
	
	@Inject
	private UserRepository userRepo;
	
	public void UpdateUser() {
	}
	
	public void UpdateUser(Long id) {
	}
	
	public void clear( ) {
		userViewModel = new UserViewModel();
	}

	public UserViewModel getUserViewModel() {
		return userViewModel;
	}

	public void setUserViewModel(UserViewModel userViewModel) {
		this.userViewModel = userViewModel;
	}
	
	
}
