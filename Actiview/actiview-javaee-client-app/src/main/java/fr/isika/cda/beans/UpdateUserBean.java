package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.EditUserViewModel;

@ManagedBean
@SessionScoped
public class UpdateUserBean {

	private EditUserViewModel editUserViewModel;

	@Inject
	private UserRepository userRepo;

	public String showUpdateUser(Long id) {
		User userToUpdate = userRepo.findUserById(id);
		UserData userDataToUpdate = userRepo.findDataByUserId(id);
		editUserViewModel = new EditUserViewModel(userToUpdate.getId());
		editUserViewModel.setPassword(userToUpdate.getPassword());
		editUserViewModel.setStatus(userToUpdate.getStatus());
		editUserViewModel.setManagerId(userToUpdate.getManager().getId().toString()); // récupère l'id du manager en String
		editUserViewModel.setEmail(userDataToUpdate.getEmail());
		editUserViewModel.setFirstname(userDataToUpdate.getFirstname());
		editUserViewModel.setLastname(userDataToUpdate.getLastname());
		editUserViewModel.setBirthday(userDataToUpdate.getBirthday());
		editUserViewModel.setJobEnum(userDataToUpdate.getJobEnum());
		return "UpdateUserForm.xhtml";
	}

	public String updateUser(Long id) {
		userRepo.updateUser(editUserViewModel);

		return "UserList.xhtml";
	}

}
