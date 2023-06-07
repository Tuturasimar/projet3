package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import fr.isika.cda.entities.activities.Activity;
import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.common.ClassContextEnum;
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
	
	@ManagedProperty(value="#{notificationBean}")
	private NotificationBean notifBean;
	
	

	private SeeArOfEmployeeViewModel arOfEmployeeVM = new SeeArOfEmployeeViewModel();

	// pour condition d'affichage en front
	private final StateAr INHOLD = StateAr.INHOLD;
	private final StateAr DRAFT = StateAr.DRAFT;
	private final StateAr VALIDATED = StateAr.VALIDATED;

	private ScheduleModel calendar;

	@PostConstruct
	public void initTest() {
		calendar = new DefaultScheduleModel();
	}

	/**
	 * Méthode qui permet d'afficher un calendrier correspondant au mois du cra avec
	 * toutes les activityDate qui lui sont liées
	 * 
	 * @param arId Id du cra
	 */
	public String showArCalendar(Long arId) {
		// initialisation du calendrier vide
		calendar = new DefaultScheduleModel();
		Ar ar = arRepo.findById(arId);
		arOfEmployeeVM.setArId(ar.getId());

		// la date de création est utilisée pour définir le mois affiché (sinon par
		// défaut il affiche le mois en cours)
		arOfEmployeeVM.setCreatedAt(ar.getCreatedAt());

		// on récupère la liste des activityDate
		arOfEmployeeVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
		arOfEmployeeVM.setUpdatedAt(ar.getUpdatedAt());
		arOfEmployeeVM.setStateAr(ar.getStateArEnum());

		// chaque activityDate de la liste est ajouté comme event au calendrier
		List<ActivityDate> activityDatesAsEvents = arOfEmployeeVM.getActivityDates();
		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
			//création de l'event en fonction de la valeur de PartOfDay
			String colorClass = getColorClass(activityDateAsEvent);
			
			if (activityDateAsEvent.getPartOfDay() == PartDayEnum.MORNING) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.styleClass(colorClass)
						.endDate(activityDateAsEvent.getDate().atTime(13, 0)).build();
				calendar.addEvent(event);
			} else if (activityDateAsEvent.getPartOfDay() == PartDayEnum.AFTERNOON) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(14, 0))
						.styleClass(colorClass)
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(event);
			}else {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.styleClass(colorClass)
						.endDate(activityDateAsEvent.getDate().atTime(13, 0)).build();
				calendar.addEvent(event);
				DefaultScheduleEvent<?> eventAfternoon = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(14, 0))
						.styleClass(colorClass)
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(eventAfternoon);
			}	
		}
		return "SeeArOfEmployee.xhtml";
	}
	
	public String getColorClass(ActivityDate activityDateAsEvent) {
		Activity activity = activityDateAsEvent.getArActivity().getActivity();
		
		if(activity.getClass() == Mission.class) {
			return "redCalendarItem";
		} else if(activity.getClass() == Formation.class) {
			return "purpleCalendarItem";
		} else {
			return "blueCalendarItem";
		}
	
	}
	
	public String getMonth() {
		Ar actualAr = arRepo.findById(arOfEmployeeVM.getArId());

		int montValue = actualAr.getCreatedAt().getMonthValue();
		String month = "";

		switch (montValue) {
		case 1:
			month = "Janvier";
			break;
		case 2:
			month = "Février";
			break;
		case 3:
			month = "Mars";
			break;
		case 4:
			month = "Avril";
			break;
		case 5:
			month = "Mai";
			break;
		case 6:
			month = "Juin";
			break;
		case 7:
			month = "Juillet";
			break;
		case 8:
			month = "Août";
			break;
		case 9:
			month = "Septembre";
			break;
		case 10:
			month = "Octobre";
			break;
		case 11:
			month = "Novembre";
			break;
		case 12:
			month = "Décembre";
			break;
		}
		return month;
	}
	
	public int getYear() {
		Ar actualAr = arRepo.findById(arOfEmployeeVM.getArId());
		return actualAr.getCreatedAt().getYear();
		
	}
	
	

	public String acceptAr(Long arId) {
		arRepo.acceptAr(arId);
		
		notifBean.addNotification(arRepo.findUserByArId(arId).getId(), "Votre CRA a été validé", ClassContextEnum.SUCCESS);
		notifBean.load();
		return "SeeArTeam.xhtml";
	}

	public String refuseAr(Long arId) {
		arRepo.refuseAr(arId);
		
		notifBean.addNotification(arRepo.findUserByArId(arId).getId(), "Votre CRA a été refusé", ClassContextEnum.DANGER);
		notifBean.load();
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

	public NotificationBean getNotifBean() {
		return notifBean;
	}

	public void setNotifBean(NotificationBean notifBean) {
		this.notifBean = notifBean;
	}
	
	
	
}
