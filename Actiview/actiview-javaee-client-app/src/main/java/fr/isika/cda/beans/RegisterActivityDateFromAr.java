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
public class RegisterActivityDateFromAr {
	
	private ArDateViewModel arDateVm = new ArDateViewModel();
	
	@Inject
	private ArActivityRepository arActivityRepo;
	
	@Inject
	private ActivityDateRepository activityDateRepository;

	
	public void addDate() {
		// Mock donnée pour tester
		arDateVm.setDate(LocalDate.now());
		arDateVm.setPartOfDay(PartDayEnum.ALLDAY);
		arDateVm.setRemote(false);
		
		ArActivity arActivity = new ArActivity();
	}
}
