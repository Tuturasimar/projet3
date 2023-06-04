package fr.isika.cda.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
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

//	@PostConstruct
//    public void init() {
//        calendar = new DefaultScheduleModel();
//        Ar ar = arRepo.findById(4L);
//        arCalendarVM.setCreatedAt(ar.getCreatedAt());
//        arCalendarVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
//        DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
//            .title("Champions League Match")
//            // as I tested, you NEED a start date and enddate
//            .startDate(LocalDateTime.of(2023, 4, 13, 0, 0))
//            .endDate(LocalDateTime.of(2023, 4, 15, 12, 0)) // change with tomorrow's date
//            .build();
//        calendar.addEvent(event);
//    
//        List<ActivityDate> activityDatesAsEvents = arCalendarVM.getActivityDates();
//		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
//			DefaultScheduleEvent<?> event2 = DefaultScheduleEvent.builder()
//					.title("test")
//					.startDate(activityDateAsEvent.getDate().atTime(9, 0))
//					.endDate(activityDateAsEvent.getDate().atTime(18, 0))
//					.build();
//			calendar.addEvent(event2);
//		}
//	}  

	@PostConstruct
	public void initTest() {
		calendar = new DefaultScheduleModel();
	}

	public String showArCalendar(Long arId) {
		calendar = new DefaultScheduleModel();
		Ar ar = arRepo.findById(arId);
		arCalendarVM.setArId(ar.getId());
		arCalendarVM.setCreatedAt(ar.getCreatedAt());
		arCalendarVM.setActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));
		arCalendarVM.setUpdatedAt(ar.getUpdatedAt());
		arCalendarVM.setStateAr(ar.getStateArEnum());
		List<ActivityDate> activityDatesAsEvents = arCalendarVM.getActivityDates();
		for (ActivityDate activityDateAsEvent : activityDatesAsEvents) {
			DefaultScheduleEvent<?> event2 = DefaultScheduleEvent.builder().title("test")
					.startDate(activityDateAsEvent.getDate().atTime(9, 0))
					.endDate(activityDateAsEvent.getDate().atTime(18, 0)).build();
			calendar.addEvent(event2);
		}
		return "ShowArAsCalendar.xhtml";
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
