package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.UserRepository;

@ManagedBean
public class SeeProfileBean {

	@Inject
	private UserRepository userRepo;
	
	private User user;

	
	public String showSeeProfile(Long id) {
		user = userRepo.findUserById(id);
		return "SeeProfile.xhtml";
	}
	
	public String getListUserRoleByUserId(Long id) {
		List<UserRole> roles = userRepo.getAllUserRolesByUserId(id);
		String rolesString = "";
		for(UserRole role : roles) {
			rolesString += role.toStringLabel();
			rolesString+= " ";
		}
		return rolesString;
	}
	
	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
