package fr.isika.cda.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArActivityRepository;
import fr.isika.cda.viewmodels.ArDateViewModel;

/**
 * Bean qui gère la création des ActivityDate
 * 
 * @author Trévor
 *
 */
@ManagedBean
@ViewScoped
public class RegisterActivityDateFromArBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArDateViewModel arDateVm = new ArDateViewModel();

	private List<ActivityDate> activityDates;

	@Inject
	private ArActivityRepository arActivityRepo;

	@Inject
	private ActivityDateRepository activityDateRepository;

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
	

	@PostConstruct
	public void init() {
		// On fixe l'ID de l'AR ici
		
		getAllActivityDates();
	}
	
	/**
	 * Méthode qui va chercher toutes les dates liées à l'ID de l'Ar en cours
	 */
	public void getAllActivityDates() {
		// 1L pour tester
		activityDates = activityDateRepository.getAllActivityDateByArId(arDateVm.getArId());
	}

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

		// On rafraichit la nouvelle liste suite à l'ajout ou la suppression de plusieurs activityDate
		getAllActivityDates();

	}

	/**
	 * Méthode permettant de vérifier les dates déjà existantes pour l'AR en cours, afin de supprimer les incohérences
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
			// Si la date est identique ET ( Si le PartDay est identique OU le PartDay de l'ancien est ALLDAY)
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
	
}
