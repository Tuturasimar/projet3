package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import fr.isika.cda.entities.activities.Absence;
import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.repository.AbsenceRepository;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.StatEmployeeViewModel;

@ManagedBean
@SessionScoped
public class StatEmployeeBean {

	@Inject
	private ArRepository arRepo;

	@Inject
	private ActivityDateRepository activityDateRepo;

	@Inject
	private MissionRepository missionRepo;

	@Inject
	private FormationRepository formationRepo;

	@Inject
	private AbsenceRepository absenceRepo;

	@Inject
	private UserRepository userRepo;

	private StatEmployeeViewModel statEmployeeVM = new StatEmployeeViewModel();

	private DonutChartModel model;

	/**
	 * Méthode qui retourne la vue d'affichage de stats d'un Salarié pour le mois
	 * choisi (par défaut mois en cours)
	 * 
	 * @param arId   permet de récupérer l'info du mois choisi
	 */
	public String showStatOfEmployee(Long arId) {
		Long companyId = userRepo.findCompanyByUserConnected().getId();
		statEmployeeVM.setArId(arId);
		statEmployeeVM.setAllActivityDates(activityDateRepo.getAllActivityDateByArId(arId));

		// Liste des missions :
		List<Mission> missions = missionRepo.findAllMissionsByCompanyId(companyId);
		List<ActivityDate> allMissionDates = new ArrayList<ActivityDate>();
		for (Mission mission : missions) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> missionDates = activityDateRepo.getMissionsDateByActivityId(mission.getId());
			// récupère les résultats des query dans une liste globale
			allMissionDates.addAll(missionDates);
		}
		statEmployeeVM.setMissionActivityDates(allMissionDates);

		// Liste des formations :
		List<Formation> formations = formationRepo.findAllFormationByCompanyId(companyId);
		List<ActivityDate> allFormationDates = new ArrayList<ActivityDate>();
		for (Formation formation : formations) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> formationDates = activityDateRepo.getFormationsDateByActivityId(formation.getId());
			// récupère les résultats des query dans une liste globale
			allFormationDates.addAll(formationDates);
		}
		statEmployeeVM.setFormationActivityDates(allFormationDates);

		// Liste des absences :
		List<Absence> absences = absenceRepo.findAllAbsences();
		List<ActivityDate> allAbsenceDates = new ArrayList<ActivityDate>();
		for (Absence absence : absences) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> absenceDates = activityDateRepo.getAbsencesDateByActivityId(absence.getId());
			// récupère les résultats des query dans une liste globale
			allAbsenceDates.addAll(absenceDates);
		}
		statEmployeeVM.setAbsenceActivityDates(allAbsenceDates);

		// calcule les différents nombres d'heures
		ratios();

		// initialisation du graph :
		createDonutModel();

		return "ShowStatOfEmployee";
	}

	public int countHours(List<ActivityDate> activityDate) {
		List<ActivityDate> allDayDate = activityDateRepo.getActivityDateDependingOnPartOfDay(activityDate,
				PartDayEnum.ALLDAY);
		List<ActivityDate> morningDate = activityDateRepo.getActivityDateDependingOnPartOfDay(activityDate,
				PartDayEnum.MORNING);
		List<ActivityDate> afternoonDate = activityDateRepo.getActivityDateDependingOnPartOfDay(activityDate,
				PartDayEnum.AFTERNOON);
		int result = allDayDate.size() * 8 + morningDate.size() * 4 + afternoonDate.size() * 4;
		return result;
	}

	public void ratios() {
		statEmployeeVM.setMissionHours(countHours(statEmployeeVM.getMissionActivityDates()));
		statEmployeeVM.setFormationHours(countHours(statEmployeeVM.getFormationActivityDates()));
		statEmployeeVM.setAbsenceHours(countHours(statEmployeeVM.getAbsenceActivityDates()));
		statEmployeeVM.setTotalHours(statEmployeeVM.getAbsenceHours() + statEmployeeVM.getFormationHours()
				+ statEmployeeVM.getMissionHours());
	}

	public void createDonutModel() {
		model = new DonutChartModel();
		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(statEmployeeVM.getAbsenceHours());
		values.add(statEmployeeVM.getFormationHours());
		values.add(statEmployeeVM.getMissionHours());
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Absence");
		labels.add("Formation");
		labels.add("Mission");
		data.setLabels(labels);

		model.setData(data);
	}

	public ArRepository getArRepo() {
		return arRepo;
	}

	public void setArRepo(ArRepository arRepo) {
		this.arRepo = arRepo;
	}

	public ActivityDateRepository getActivityDateRepo() {
		return activityDateRepo;
	}

	public void setActivityDateRepo(ActivityDateRepository activityDateRepo) {
		this.activityDateRepo = activityDateRepo;
	}

	public StatEmployeeViewModel getStatEmployeeVM() {
		return statEmployeeVM;
	}

	public void setStatEmployeeVM(StatEmployeeViewModel statEmployeeVM) {
		this.statEmployeeVM = statEmployeeVM;
	}

	public MissionRepository getMissionRepo() {
		return missionRepo;
	}

	public void setMissionRepo(MissionRepository missionRepo) {
		this.missionRepo = missionRepo;
	}

	public AbsenceRepository getAbsenceRepo() {
		return absenceRepo;
	}

	public void setAbsenceRepo(AbsenceRepository absenceRepo) {
		this.absenceRepo = absenceRepo;
	}

	public DonutChartModel getModel() {
		return model;
	}

	public void setModel(DonutChartModel model) {
		this.model = model;
	}
}
