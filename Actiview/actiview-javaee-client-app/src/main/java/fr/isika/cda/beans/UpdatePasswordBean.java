package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UpdatePasswordViewModel;

@ManagedBean
@SessionScoped
public class UpdatePasswordBean {

	private UpdatePasswordViewModel updatePasswordVM;

	@Inject
	private UserRepository userRepo;

	public String showUpdatePassword(String login) {
		User userToUpdate = userRepo.findUserByLogin(login);

		updatePasswordVM = new UpdatePasswordViewModel(userToUpdate.getId());
		updatePasswordVM.setPassword(userToUpdate.getPassword());

		return "UpdatePassword.xhtml";
	}

	public String updatePassword() {

		Long id = updatePasswordVM.getUserId();
		userRepo.updatePassword(updatePasswordVM);

		updatePasswordVM = new UpdatePasswordViewModel(id);

		return "index.xhtml";
	}

	public UpdatePasswordViewModel getUpdatePasswordVM() {
		return updatePasswordVM;
	}

	public void setUpdatePasswordVM(UpdatePasswordViewModel updatePasswordVM) {
		this.updatePasswordVM = updatePasswordVM;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
}
