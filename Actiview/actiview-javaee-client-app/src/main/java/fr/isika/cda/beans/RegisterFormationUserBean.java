package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.repository.FormationUserRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.FormationUserViewModel;

@ManagedBean
public class RegisterFormationUserBean {

	@Inject
	FormationUserRepository formationUserRepo;

	@Inject
	UserRepository userRepo;

	@Inject
	FormationRepository formationRepo;
	
	private List<User> users;

	private List<Formation> formations;

	private FormationUserViewModel formationUserVm = new FormationUserViewModel();

	@PostConstruct
	public void init() {
		String login = SessionUtils.getUserLoginFromSession();

		users = userRepo.findUserByManagerLogin(login);

		Long idCompany = userRepo.findCompanyByUserConnected().getId();

		formations = formationRepo.getAllActiveFormationsFromCompany(idCompany);
	}

	public String register() {
		if (!formationUserRepo.checkExistingFormationUser(formationUserVm)) {
			formationUserRepo.register(formationUserVm);

			// TODO ajout d'une notification
		} else {
			// TODO ajout d'une notification "La formation a déjà été attribuée pour cet
			// utilisateur
		}

		return "formationUserList";
	}

	
	//Getters & setters
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public FormationUserViewModel getFormationUserVm() {
		return formationUserVm;
	}

	public void setFormationUserVm(FormationUserViewModel formationUserVm) {
		this.formationUserVm = formationUserVm;
	}
}
