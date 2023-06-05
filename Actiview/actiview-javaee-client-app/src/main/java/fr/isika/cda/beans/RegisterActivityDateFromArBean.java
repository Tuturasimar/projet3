package fr.isika.cda.beans;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArActivityRepository;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.viewmodels.ArCalendarViewModel;
import fr.isika.cda.viewmodels.ArDateViewModel;

/**
 * Bean qui gère la création des ActivityDate
 * 
 * @author Trévor
 *
 */
@ManagedBean
@SessionScoped
public class RegisterActivityDateFromArBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArDateViewModel arDateVm = new ArDateViewModel();
	private ArCalendarViewModel arCalendarVM = new ArCalendarViewModel();

	private List<ActivityDate> activityDates;
	
	private ScheduleModel calendar;

	@Inject
	private ArActivityRepository arActivityRepo;

	@Inject
	private ActivityDateRepository activityDateRepository;
	
	@Inject
	private ArRepository arRepo;
	
	/**
	 * Initialise un calendrier au chargement de la page
	 */
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
		arCalendarVM.setActivityDates(activityDateRepository.getAllActivityDateByArId(ar.getId()));
		arCalendarVM.setUpdatedAt(ar.getUpdatedAt());
		arCalendarVM.setStateAr(ar.getStateArEnum());
		
		//initialisation de l'id du cra et de la liste des activités liées au cra (issu de l'ancienne méthode, pour grder ce qui a été fait je le reprends ici)
		arDateVm.setArId(arId);
		activityDates = activityDateRepository.getAllActivityDateByArId(arId);
		
		// chaque activityDate de la liste est ajouté comme event au calendrier
		List<ActivityDate> activityDatesAsEvents = arCalendarVM.getActivityDates();
		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
			//création de l'event en fonction de la valeur de PartOfDay
			if (activityDateAsEvent.getPartOfDay() == PartDayEnum.MORNING) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepository.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.endDate(activityDateAsEvent.getDate().atTime(13, 0)).build();
				calendar.addEvent(event);
			} else if (activityDateAsEvent.getPartOfDay() == PartDayEnum.AFTERNOON) {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepository.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(14, 0))
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(event);
			}else {
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title(activityDateRepository.getActivityLabelFromActivityDate(activityDateAsEvent.getId()))
						.startDate(activityDateAsEvent.getDate().atTime(9, 0))
						.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
				calendar.addEvent(event);
			}
		}
		return "addActivityDates.xhtml";
	}

	/**
	 * Méthode qui va chercher toutes les dates liées à l'ID de l'Ar en cours si le calendrier fonctionne devient obsolète
	 */
//	public String getAllActivityDates(Long arId) {
//		arDateVm.setArId(arId);
//		activityDates = activityDateRepository.getAllActivityDateByArId(arId);
//
//		return "addActivityDates";
//	}

	/**
	 * 
	 * Méthode permettant d'ajouter une date liée à une Ar_activity
	 */
	public void addDate() {

		// Avant l'ajout d'une nouvelle ActivityDate, on check l'ancienne
		checkExistingActivityDate();

		// Appel d'une méthode pour vérifier s'il existe déjà un ArActivity avec la même
		// Id du Ar et de l'Activity
		ArActivity arActivity = arActivityRepo.alreadyExist(arDateVm.getArId(), arDateVm.getActivityId());

		// S'il existe
		if (arActivity != null) {
			// Appel d'une méthode du Repository d'ActivityDate pour créer une nouvelle date
			activityDateRepository.createActivityDate(arDateVm, arActivity.getId());
		}
		// S'il n'existe pas
		else {
			// Appel d'une méthode du Repository d'ArActivity pour le créer en premier
			Long id = arActivityRepo.register(arDateVm.getArId(), arDateVm.getActivityId());
			// On se sert de son ID pour l'envoyer en paramètre à la méthode qui va créer
			// l'activityDate
			activityDateRepository.createActivityDate(arDateVm, id);
		}

		// On rafraichit la nouvelle liste suite à l'ajout ou la suppression de
		// plusieurs activityDate
		showArCalendar(arDateVm.getArId());

	}

	/**
	 * Méthode permettant de vérifier les dates déjà existantes pour l'AR en cours,
	 * afin de supprimer les incohérences
	 */
	public void checkExistingActivityDate() {

		// Si le PartDay du nouveau est ALLDAY
		if (arDateVm.getPartOfDay() == PartDayEnum.ALLDAY) {
			// On boucle sur la liste récupérée plus tôt qui contient toutes les
			// activityDate
			for (ActivityDate activityDate : activityDates) {
				// Si les dates sont identiques
				if (activityDate.getDate().equals(arDateVm.getDate())) {
					// On supprime l'ancienne
					activityDateRepository.delete(activityDate);
				}
			}
		} else
		// Si le PartDay est MORNING ou AFTERNOON
		{
			// On utilise un stream qui va filtrer la liste et stocker celui qui correspond
			// dans une variable
			// Si la date est identique ET ( Si le PartDay est identique OU le PartDay de
			// l'ancien est ALLDAY)
			ActivityDate activityDateToDelete = activityDates.stream()
					.filter(activityDate -> arDateVm.getDate().equals(activityDate.getDate())
							&& (arDateVm.getPartOfDay().equals(activityDate.getPartOfDay())
									|| activityDate.getPartOfDay() == PartDayEnum.ALLDAY))
					.findAny().orElse(null);
			// Si la recherche a été fructueuse
			if (activityDateToDelete != null) {
				// On supprime l'ancien
				activityDateRepository.delete(activityDateToDelete);
			}
		}
	}

	public void deleteAllExistingActivityDate() {

		// requete pour supprimer toutes les activityDate liées à l'Ar
		for(ActivityDate activityDateToDelete : activityDates) {
			activityDateRepository.delete(activityDateToDelete);
		}
		
		showArCalendar(arDateVm.getArId());
		
	}

	public void addAllMonth() {

		deleteAllExistingActivityDate();
		
		Ar actualAr = arRepo.findById(arDateVm.getArId());
		
		LocalDate actualDate = actualAr.getCreatedAt();
		
		
		LocalDate firstDayOfMonth = LocalDate.of(actualDate.getYear(), actualDate.getMonthValue(), 1);
		
		LocalDate firstDayNextMonth = LocalDate.of(actualDate.getYear(), actualDate.plusMonths(1).getMonthValue(), 1);
		
		Stream<LocalDate> allDaysOfMonthStream = firstDayOfMonth.datesUntil(firstDayNextMonth);
		
		List<LocalDate> allDaysOfMonth = allDaysOfMonthStream.collect(Collectors.toList());
		
		for(LocalDate dateToAdd : allDaysOfMonth) {
			if(dateToAdd.getDayOfWeek() != DayOfWeek.SATURDAY && dateToAdd.getDayOfWeek() != DayOfWeek.SUNDAY)
			arDateVm.setDate(dateToAdd);
			addDate();
		}
	
	}

	//Getters & setters
	public List<ActivityDate> getActivityDates() {
		return activityDates;
	}

	public void setActivityDates(List<ActivityDate> activityDates) {
		this.activityDates = activityDates;
	}

	public ArDateViewModel getArDateVm() {
		return arDateVm;
	}

	public void setArDateVm(ArDateViewModel arDateVm) {
		this.arDateVm = arDateVm;
	}
	
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
