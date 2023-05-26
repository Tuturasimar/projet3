package fr.isika.cda.beans;

import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArActivityRepository;
import fr.isika.cda.viewmodels.ArDateViewModel;

@ManagedBean
/**
 * Bean qui gère la création des ActivityDate
 * 
 * @author Trévor
 *
 */
public class RegisterActivityDateFromArBean {

	private ArDateViewModel arDateVm = new ArDateViewModel();

	@Inject
	private ArActivityRepository arActivityRepo;

	@Inject
	private ActivityDateRepository activityDateRepository;

	/**
	 * 
	 * Méthode permettant d'ajouter une date liée à une Ar_activity
	 */
	public void addDate() {

		// Mock donnée pour tester
		arDateVm.setDate(LocalDate.now());
		arDateVm.setPartOfDay(PartDayEnum.ALLDAY);
		arDateVm.setRemote(false);
		arDateVm.setArId(1L);
		arDateVm.setActivityId(1L);
		
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
	}
	
	public void checkExistingActivityDate() {
		
		// Si le PartDay du nouveau est ALLDAY
			// Requete pour obtenir une Liste des ActivityDate sur la même date (et même AR évidemment)
			// Suppression des anciens
				
		// Sinon
			// Vérifier si la date et le partDay sont identiques à une ActivityDate déjà en BDD (correspondant au même AR)
			// Si oui
				// Suppression de l'ancien
	}
}
