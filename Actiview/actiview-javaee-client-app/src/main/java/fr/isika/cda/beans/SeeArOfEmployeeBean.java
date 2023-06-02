package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.viewmodels.SeeArOfEmployeeViewModel;

@ManagedBean
@SessionScoped
public class SeeArOfEmployeeBean {

	@Inject
	private ActivityDateRepository activityDateRepo;

	@Inject
	private ArRepository arRepo;

	private SeeArOfEmployeeViewModel arOfEmployeeVM = new SeeArOfEmployeeViewModel();

	private final StateAr INHOLD = StateAr.INHOLD;
	private final StateAr DRAFT = StateAr.DRAFT;
	private final StateAr VALIDATED = StateAr.VALIDATED;

	public String showArOfEmployee(Long arId) {
		Ar ar = arRepo.findById(arId);
		arOfEmployeeVM.setArId(ar.getId());
		arOfEmployeeVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
		arOfEmployeeVM.setStateAr(ar.getStateArEnum());
		System.out.println("test");
		return "SeeArOfEmployee.xhtml";
	}

	public void test() {
		System.out.println("test");
	}
	
	public String acceptAr(Long arId) {
		arRepo.acceptAr(arId);
		return "SeeArTeam.xhtml";
	}

	public String refuseAr(Long arId) {
		arRepo.refuseAr(arId);
		return "SeeArTeam.xhtml";
	}

	// Getters & setters
	public ActivityDateRepository getActivityDateRepo() {
		return activityDateRepo;
	}

	public void setActivityDateRepo(ActivityDateRepository activityDateRepo) {
		this.activityDateRepo = activityDateRepo;
	}

	public ArRepository getArRepo() {
		return arRepo;
	}

	public void setArRepo(ArRepository arRepo) {
		this.arRepo = arRepo;
	}

	public SeeArOfEmployeeViewModel getArOfEmployeeVM() {
		return arOfEmployeeVM;
	}

	public void setArOfEmployeeVM(SeeArOfEmployeeViewModel arOfEmployeeVM) {
		this.arOfEmployeeVM = arOfEmployeeVM;
	}

	public StateAr getInhold() {
		return INHOLD;
	}

	public StateAr getDraft() {
		return DRAFT;
	}

	public StateAr getValidated() {
		return VALIDATED;
	}
}
