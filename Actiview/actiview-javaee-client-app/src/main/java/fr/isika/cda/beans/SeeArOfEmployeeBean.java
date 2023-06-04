package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.viewmodels.ArCalendarViewModel;
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

	private ScheduleModel calendar;

	@PostConstruct
	public void initTest() {
		calendar = new DefaultScheduleModel();
	}

	public String showArCalendar(Long arId) {
		calendar = new DefaultScheduleModel();
		Ar ar = arRepo.findById(arId);
		arOfEmployeeVM.setArId(ar.getId());
		arOfEmployeeVM.setCreatedAt(ar.getCreatedAt());
		arOfEmployeeVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
		arOfEmployeeVM.setUpdatedAt(ar.getUpdatedAt());
		arOfEmployeeVM.setStateAr(ar.getStateArEnum());
		List<ActivityDate> activityDatesAsEvents = arOfEmployeeVM.getActivityDates();
		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
			DefaultScheduleEvent<?> event2 = DefaultScheduleEvent.builder().title("test")
					.startDate(activityDateAsEvent.getDate().atTime(9, 0))
					.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
			calendar.addEvent(event2);
		}
		return "SeeArOfEmployee.xhtml";
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

	public ScheduleModel getCalendar() {
		return calendar;
	}

	public void setCalendar(ScheduleModel calendar) {
		this.calendar = calendar;
	}
}
