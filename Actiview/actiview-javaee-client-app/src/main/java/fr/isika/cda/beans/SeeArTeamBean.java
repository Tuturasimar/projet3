package fr.isika.cda.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class SeeArTeamBean {

	@Inject
	private ArRepository arRepo;

	@Inject
	private UserRepository userRepo;

	private List<User> employees = new ArrayList<User>();
	private List<Ar> ars = new ArrayList<Ar>();

	// Pour gérer facilement des conditions d'apparition côté front
	private final StateAr DRAFT = StateAr.DRAFT;
	private final StateAr INHOLD = StateAr.INHOLD;
	private final StateAr VALIDATED = StateAr.VALIDATED;

	/**
	 * Méthode permettant d'afficher un tableau des cras de tous les salariés d'un
	 * manager pour le mois et l'année en cours
	 * 
	 */
	@PostConstruct
	public void init() {
		Long managerId = SessionUtils.getUserIdFromSession();
		setEmployees(userRepo.getAllEmployeesByManagerId(managerId));

		for (User employee : employees) {
			Ar arToAdd = arRepo.findArWithUserDataByUserAndCreatedAt(employee.getId(), LocalDate.now().getMonthValue(),
					LocalDate.now().getYear());
			ars.add(arToAdd);
		}

	}

	/**
	 * Méthode permettant d'afficher un tableau des cras de tous les salariés d'un
	 * manager en fonction du mois et de l'année choisis
	 * 
	 * @param managerId : id du manager qui consulte la page (donc il faudra que
	 *                  cette page ne soit accessible qu'à un manager)
	 * @param month     : mois sélectionné (quand on arrive sur cette page, on est
	 *                  en LocalDate.now() et on peut changer grâce à des boutons
	 *                  mois précédent/suivant)
	 * @param year      : année sélectionnée (quand on arrive sur cette page, on est
	 *                  en LocalDate.now())
	 * @return la page
	 */
//	public String showSeeArTeam(String login, int month, int year) {
//		setEmployees(userRepo.getAllEmployeesByManagerLogin(login));
//
//		for (User employee : employees) {
//			Ar arToAdd = arRepo.findArWithUserDataByUserAndCreatedAt(employee.getId(), month, year);
//			ars.add(arToAdd);
//		}
//		return "SeeArTeam.xhtml";
//	}

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	public List<Ar> getArs() {
		return ars;
	}

	public void setArs(List<Ar> ars) {
		this.ars = ars;
	}

	public StateAr getDRAFT() {
		return DRAFT;
	}

	public StateAr getINHOLD() {
		return INHOLD;
	}

	public StateAr getVALIDATED() {
		return VALIDATED;
	}

}
