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
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.viewmodels.ArCalendarViewModel;

@ManagedBean
@SessionScoped
public class ShowArAsCalendarBean {

	@Inject
	private ArRepository arRepo;

	@Inject
	private ActivityDateRepository activityDateRepo;

	private ArCalendarViewModel arCalendarVM = new ArCalendarViewModel();

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
		arCalendarVM.setArId(ar.getId());

		// la date de création est utilisée pour définir le mois affiché (sinon par
		// défaut il affiche le mois en cours)
		arCalendarVM.setCreatedAt(ar.getCreatedAt());
		
		// on récupère la liste des activityDate
		arCalendarVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
		arCalendarVM.setUpdatedAt(ar.getUpdatedAt());
		arCalendarVM.setStateAr(ar.getStateArEnum());
		
		// chaque activityDate de la liste est ajouté comme event au calendrier
		List<ActivityDate> activityDatesAsEvents = arCalendarVM.getActivityDates();
		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
			//création de l'event en fonction de la valeur de PartOfDay
			if (activityDateAsEvent.getPartOfDay() == PartDayEnum.MORNING) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.endDate(activityDateAsEvent.getDate().atTime(13, 0)).build();
				calendar.addEvent(event);
			} else if (activityDateAsEvent.getPartOfDay() == PartDayEnum.AFTERNOON) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(14, 0))
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(event);
			}else {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepo.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(event);
			}
		}
		return "ShowArAsCalendar.xhtml";
	}

	//Getters & setters
	public ArCalendarViewModel getArCalendarVM() {
		return arCalendarVM;
	}

	public void setArCalendarVM(ArCalendarViewModel arCalendarVM) {
		this.arCalendarVM = arCalendarVM;
	}

	public ScheduleModel getCalendar() {
		return calendar;
	}

	public void setCalendar(ScheduleModel calendar) {
		this.calendar = calendar;
	}

}
