package fr.isika.cda.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.ArViewModel;

@ManagedBean
public class SeeArTeamBean {

	@Inject
	private ArRepository arRepo;

	@Inject
	private UserRepository userRepo;

	//Liste des salariés du manager
	private List<User> employees = new ArrayList<User>();
	
	//Liste des cras des salariés du manager pour le mois sélectionné
	private List<Ar> ars = new ArrayList<Ar>();

	//repris d'uneautre vue, set à stocker l'infos du mois et de l'année sélectionnés
	private ArViewModel arVm = new ArViewModel();

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
			if (arToAdd != null) {
				ars.add(arToAdd);
			}
		}
	}

	/**
	 * Méthode permettant d'afficher un tableau des cras de tous les salariés du manager
	 * pour le mois et l'année sélectionnés
	 */
	public void getAr() {
		Long managerId = SessionUtils.getUserIdFromSession();
		setEmployees(userRepo.getAllEmployeesByManagerId(managerId));

		for (User employee : employees) {
			Ar arToAdd = arRepo.findArWithUserDataByUserAndCreatedAt(employee.getId(), arVm.getDate().getMonthValue(),
					arVm.getDate().getYear());
			if (arToAdd != null) {
				ars.add(arToAdd);
			}
		}

	}
	
	public void test() {
		System.out.println("test");
	}

	//Getters & setters
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

	public ArViewModel getArVm() {
		return arVm;
	}

	public void setArVm(ArViewModel arVm) {
		this.arVm = arVm;
	}

}
