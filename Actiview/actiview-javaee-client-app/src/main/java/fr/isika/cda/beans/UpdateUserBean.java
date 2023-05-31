package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UpdateUserViewModel;

@ManagedBean
@SessionScoped
public class UpdateUserBean {

	private UpdateUserViewModel updateUserViewModel;

	@Inject
	private UserRepository userRepo;

	public String showUpdateUser(Long id) {
//		User userToUpdate = userRepo.findUserById(id);
//		UserData userDataToUpdate = userRepo.findDataByUserId(id);
		
//		updateUserViewModel = new UpdateUserViewModel(userToUpdate.getId());
//		updateUserViewModel.setPassword(userToUpdate.getPassword());
//		updateUserViewModel.setStatus(userToUpdate.getStatus());
//		updateUserViewModel.setManagerId(userToUpdate.getManager().getId().toString()); // récupère l'id du manager en String
//		updateUserViewModel.setEmail(userDataToUpdate.getEmail());
//		updateUserViewModel.setFirstname(userDataToUpdate.getFirstname());
//		updateUserViewModel.setLastname(userDataToUpdate.getLastname());
//		updateUserViewModel.setBirthday(userDataToUpdate.getBirthday());
//		updateUserViewModel.setJobEnum(userDataToUpdate.getJobEnum());
		return "UpdateUserForm.xhtml";
	}

	public String updateUser(Long id) {
		userRepo.updateUser(updateUserViewModel);

		return "UserList.xhtml";
	}

}
