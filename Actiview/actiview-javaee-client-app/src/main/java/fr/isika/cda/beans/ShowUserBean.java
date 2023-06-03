package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.UserRepository;

@ManagedBean
public class ShowUserBean {

	@Inject
	private UserRepository userRepo;

	private List<User> users;

	private final StatusEnum ACTIVE = StatusEnum.ACTIVE;
	private final StatusEnum INACTIVE = StatusEnum.INACTIVE;

	// récupère tous les Users de la bdd (pas de jointures avec User Data et
	// UserRole)
	@PostConstruct
	public void initUserList() {
		users = userRepo.findAllUsers();

	}

	public String getListUserRoleByUserId(Long id) {
		List<UserRole> roles = userRepo.getAllUserRolesByUserId(id);
		String rolesString = "";
		for (UserRole role : roles) {
			rolesString += role.toStringLabel();
			rolesString += " ";
		}
		return rolesString;
	}

	public String changeStatus(Long id) {
		User userToUpdate = userRepo.findUserById(id);

		if (userToUpdate.getStatus() == ACTIVE) {
			userToUpdate.setStatus(INACTIVE);
		} else {
			userToUpdate.setStatus(ACTIVE);
		}
		userRepo.updateStatus(userToUpdate);

		// pour rafraichir la page avec les nouvelles infos
		initUserList();

		return "UserList.xhtml";
	}

	public StatusEnum getACTIVE() {
		return ACTIVE;
	}

	public StatusEnum getINACTIVE() {
		return INACTIVE;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
