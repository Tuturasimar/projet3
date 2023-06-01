package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import fr.isika.cda.entities.common.JobEnum;
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

	private List<User> managers;
	
	@PostConstruct
	public void init() {
		managers = userRepo.getAllManagers();
	}
	
	/**
	 * Méthode qui permet de renvoyer sur un formulaire de modification d'un user avec ses infos préremplies
	 * @param id du userVM de la vue UserList qui sert à retrouver le user et le userData en bdd
	 * @return la vue du formulaire de modification
	 */
	public String showUpdateUser(Long id) {
		User userToUpdate = userRepo.findUserWithManagerById(id);
		UserData userDataToUpdate = userRepo.findDataByUserId(id);
		
		updateUserViewModel = new UpdateUserViewModel(userToUpdate.getId());
		updateUserViewModel.setPassword(userToUpdate.getPassword());
		updateUserViewModel.setStatus(userToUpdate.getStatus());
		
		String managerId = (userToUpdate.getManager() != null) ? String.valueOf(userToUpdate.getManager().getId()) : null;
		
		updateUserViewModel.setManagerId(managerId); // récupère l'id du manager en String
		updateUserViewModel.setEmail(userDataToUpdate.getEmail());
		updateUserViewModel.setFirstname(userDataToUpdate.getFirstname());
		updateUserViewModel.setLastname(userDataToUpdate.getLastname());
		updateUserViewModel.setBirthday(userDataToUpdate.getBirthday());
		updateUserViewModel.setJobEnum(userDataToUpdate.getJobEnum());
		return "UpdateUserForm.xhtml";
	}

	/**
	 * Méthode qui permet d'enregistrer la modification d'un user en bdd
	 * @return la vue UserList
	 */
	public String updateUser() {
		
		Long id = updateUserViewModel.getId();
		userRepo.updateUser(updateUserViewModel);
		
		updateUserViewModel = new UpdateUserViewModel(id);
		
		return "UserList.xhtml";
	}
	
	/**
	 * récupère la liste des job dans JobEnum
	 * @return
	 */
	public JobEnum[] jobEnumValues() {
		return JobEnum.values();
	}
	
	
	public UpdateUserViewModel getUpdateUserViewModel() {
		return updateUserViewModel;
	}

	public void setUpdateUserViewModel(UpdateUserViewModel updateUserViewModel) {
		this.updateUserViewModel = updateUserViewModel;
	}

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}
}
