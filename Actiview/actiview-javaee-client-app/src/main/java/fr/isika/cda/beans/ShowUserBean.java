package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
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

//	public UserData GetUserData(Long id) {
//		return userRepo.GetUserDataByUserId(id);
//	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
