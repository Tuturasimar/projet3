package fr.isika.cda.beans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UserViewModel;

@ManagedBean
public class RegisterUserBean {
	
	private UserViewModel userViewModel = new UserViewModel();
	
	@Inject
	private UserRepository userRepo;

	private List<User> managers;
	
	@PostConstruct
	public void init() {
		managers = userRepo.getAllManagers();
	}
	
	/**
	 * Appelle la méthode registerUser de UserRepository pour enregistrer le user (et les tables liées (UserData et UserRole) en bdd
	 * Connectée en front au bouton Valider du formulaire
	 */
	public void registerUser() {
		Long id = userRepo.registerUser(userViewModel);
		
		System.out.println("Id du user créé : "+ id);
	}
	/**
	 * Vide les champs du formulaire en front
	 */
	public void clear() {
		userViewModel = new UserViewModel();
	}

	public RoleTypeEnum[] roleTypeEnumValues() {
		return RoleTypeEnum.values();
	}
	
	public JobEnum[] jobEnumValues() {
		return JobEnum.values();
	}
	
	public UserViewModel getUserViewModel() {
		return userViewModel;
	}

	public void setUserViewModel(UserViewModel userViewModel) {
		this.userViewModel = userViewModel;
	}
	public List<User> getManagers() {
		return managers;
	}
	public void setManagers(List<User> managers) {
		this.managers = managers;
	}
	
}
