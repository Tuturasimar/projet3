package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.repository.MissionUserRepository;
import fr.isika.cda.repository.UserFeedBackRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.MissionUserViewModel;

@ManagedBean
public class RegisterMissionUserBean {

	@Inject
	MissionUserRepository missionUserRepo;

	@Inject
	UserFeedBackRepository userFeedbackRepo;

	@Inject
	UserRepository userRepo;

	@Inject
	MissionRepository missionRepo;

	private List<User> users;

	private List<Mission> missions;

	private MissionUserViewModel missionUserVm = new MissionUserViewModel();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public MissionUserViewModel getMissionUserVm() {
		return missionUserVm;
	}

	public void setMissionUserVm(MissionUserViewModel missionUserVm) {
		this.missionUserVm = missionUserVm;
	}

	@PostConstruct
	public void init() {
		
		String login = SessionUtils.getUserLoginFromSession();

		users = userRepo.findUserByManagerLogin(login);
		
		// Obtenir l'Id de la Company avec l'utilisateur actuellement connecté
		Long idCompany = userRepo.findCompanyByUserConnected().getId();

		// Chercher la liste des missions actives
		missions = missionRepo.getAllActiveMissionsFromCompany(idCompany);

	}

	public String register() {

		// On vérifie que cette affectation n'existe pas déjà
		if (!missionUserRepo.checkExistingMissionUser(missionUserVm)) {
			Long id = userFeedbackRepo.createBlankOne();
			missionUserVm.setUserFeedbackId(id);

			missionUserRepo.register(missionUserVm);

			// TODO ajout d'une notification
		} else {
			// TODO ajout d'une notification "La mission a déjà été attribuée pour cet
			// utilisateur"
		}
		return "missionUserList";

	}
	

}
