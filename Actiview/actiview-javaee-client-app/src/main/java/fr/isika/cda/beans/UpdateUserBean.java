package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UserViewModel;


@ManagedBean
@SessionScoped
public class UpdateUserBean {
	
	private UserViewModel userViewModel = new UserViewModel();
	
	private User userToUpdate = new User();
	@Inject
	private UserRepository userRepo;
	
	public String showUpdateUser(Long id) {
		userToUpdate = userRepo.findUserById(id);
		return "UpdateUserForm.xhtml";
	}
	
	public String updateUser(Long id) {
		userRepo.updateUser(userToUpdate.getId(), userViewModel);
		clear();
		return "UserList.xhtml";
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
