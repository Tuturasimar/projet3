package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.UserRepository;

@ManagedBean
public class ShowUserBean {
	
	@Inject
	private UserRepository userRepo;
	
	private List<User> users;
	
	
	
	//récupère tous les Users de la bdd (pas de jointures avec User Data et UserRole)
	@PostConstruct
	public void initUserList() {
		users = userRepo.findAllUsers();
		
	}

	public String GetListUserRoleByUserId(Long id) {
		List<UserRole> roles = userRepo.GetAllUserRolesByUserId(id);
		String rolesString = "";
		for(UserRole role : roles) {
			rolesString += role.toStringLabel();
		}
		return rolesString;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
