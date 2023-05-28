package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.repository.ActivityDateRepository;

@ManagedBean
@ViewScoped
public class ShowActivityDatesFromArBean {
	
	private List<ActivityDate> activityDates;

	@Inject
	private ActivityDateRepository activityDateRepo;

	public List<ActivityDate> getActivityDates() {
		return activityDates;
	}

	public void setActivityDates(List<ActivityDate> activityDates) {
		this.activityDates = activityDates;
	}
	
	@PostConstruct
	public void getAllActivityDates() {
		// 1L pour tester
		activityDates = activityDateRepo.getAllActivityDateByArId(1L);
	}
	
	
}
