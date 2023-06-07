package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import fr.isika.cda.entities.activities.Absence;
import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.AbsenceRepository;
import fr.isika.cda.repository.ActivityDateRepository;
import fr.isika.cda.repository.FormationRepository;
import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.StatEmployeeViewModel;

@ManagedBean
public class SeeProfileBean {

	@Inject
	private UserRepository userRepo;

	@Inject
	private ActivityDateRepository activityDateRepo;

	@Inject
	private MissionRepository missionRepo;

	@Inject
	private FormationRepository formationRepo;

	@Inject
	private AbsenceRepository absenceRepo;

	private StatEmployeeViewModel statEmployeeVM = new StatEmployeeViewModel();

	private DonutChartModel model;

	private User user;

	/**
	 * Méthode qui retourne la vue du profil d'un Salarié avec les stats d'activité
	 * du mois en cours
	 * 
	 * @param userId permet de récupérer l'info du user
	 */
	public String showSeeProfileWithStats(String login) {
		
		user = userRepo.findUserByLogin(login);
		Ar ar = userRepo.findLatestArByUserId(user.getId());
		Long companyId = userRepo.findCompanyByUserConnected().getId();
		statEmployeeVM.setArId(ar.getId());
		statEmployeeVM.setAllActivityDates(activityDateRepo.getAllActivityDateByArId(ar.getId()));

		// Méthode qui mériterait d'être factorisée (mais manque de temps...)

		// Liste des missions :
		// On récupère dans un premier temps toutes les missions proposées par l'ESN
		List<Mission> missions = missionRepo.findAllMissionsByCompanyId(companyId);
		// On récupère ensuite les activityDate qui correspondent à ces missions
		List<ActivityDate> allMissionDates = new ArrayList<ActivityDate>();
		for (Mission mission : missions) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> missionDates = activityDateRepo.getMissionsDateByActivityId(mission.getId());
			// récupère les résultats des query dans une liste globale
			allMissionDates.addAll(missionDates);
		}
		// Enfin, on ne garde que les ActivityDate correspondant au CRA choisi
		List<ActivityDate> finalmissionDate = new ArrayList<ActivityDate>();
		for (ActivityDate missionDate : allMissionDates) {
			if (missionDate.getArActivity().getAr().getId() == ar.getId()) {
				finalmissionDate.add(missionDate);
			}
		}
		// On ajoute cette liste finale au viewModel
		statEmployeeVM.setMissionActivityDates(finalmissionDate);

		// Liste des formations :
		List<Formation> formations = formationRepo.findAllFormationByCompanyId(companyId);
		List<ActivityDate> allFormationDates = new ArrayList<ActivityDate>();
		for (Formation formation : formations) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> formationDates = activityDateRepo.getFormationsDateByActivityId(formation.getId());
			// récupère les résultats des query dans une liste globale
			allFormationDates.addAll(formationDates);
		}
		List<ActivityDate> finalformationDate = new ArrayList<ActivityDate>();
		for (ActivityDate formationDate : allFormationDates) {
			if (formationDate.getArActivity().getAr().getId() == ar.getId()) {
				finalformationDate.add(formationDate);
			}
		}
		statEmployeeVM.setFormationActivityDates(finalformationDate);

		// Liste des absences :
		List<Absence> absences = absenceRepo.findAllAbsences();
		List<ActivityDate> allAbsenceDates = new ArrayList<ActivityDate>();
		for (Absence absence : absences) {
			// génère la liste des ActivityDate correspondant à une mission
			List<ActivityDate> absenceDates = activityDateRepo.getAbsencesDateByActivityId(absence.getId());
			// récupère les résultats des query dans une liste globale
			allAbsenceDates.addAll(absenceDates);
		}

		List<ActivityDate> finalabsenceDate = new ArrayList<ActivityDate>();
		for (ActivityDate absenceDate : allAbsenceDates) {
			if (absenceDate.getArActivity().getAr().getId() == ar.getId()) {
				finalabsenceDate.add(absenceDate);
			}
		}
		statEmployeeVM.setAbsenceActivityDates(finalabsenceDate);

		// calcule les différents nombres d'heures
		ratios();

		// initialisation du graph :
		createDonutModel();

		return "SeeProfile.xhtml";
	}

	public void ratios() {
		statEmployeeVM.setMissionHours(countHours(statEmployeeVM.getMissionActivityDates()));
		statEmployeeVM.setFormationHours(countHours(statEmployeeVM.getFormationActivityDates()));
		statEmployeeVM.setAbsenceHours(countHours(statEmployeeVM.getAbsenceActivityDates()));
		statEmployeeVM.setTotalHours(statEmployeeVM.getAbsenceHours() + statEmployeeVM.getFormationHours()
				+ statEmployeeVM.getMissionHours());
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

	// valable avant l'ajout des stats
	public String showSeeProfile(Long id) {
		user = userRepo.findUserById(id);
		return "SeeProfile.xhtml";
	}
//	
//	public String showSeeProfile(String login) {
//		user = userRepo.findUserByLogin(login);
//		return "SeeProfile.xhtml";
//	}

	public String getListUserRoleByUserId(Long id) {
		List<UserRole> roles = userRepo.getAllUserRolesByUserId(id);
		String rolesString = "";
		for (UserRole role : roles) {
			rolesString += role.toStringLabel();
			rolesString += " ";
		}
		return rolesString;
	}

	public boolean checkModel() {
		return model != null ? true : false;
	}

	// Getters & setters
	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ActivityDateRepository getActivityDateRepo() {
		return activityDateRepo;
	}

	public void setActivityDateRepo(ActivityDateRepository activityDateRepo) {
		this.activityDateRepo = activityDateRepo;
	}

	public MissionRepository getMissionRepo() {
		return missionRepo;
	}

	public void setMissionRepo(MissionRepository missionRepo) {
		this.missionRepo = missionRepo;
	}

	public FormationRepository getFormationRepo() {
		return formationRepo;
	}

	public void setFormationRepo(FormationRepository formationRepo) {
		this.formationRepo = formationRepo;
	}

	public AbsenceRepository getAbsenceRepo() {
		return absenceRepo;
	}

	public void setAbsenceRepo(AbsenceRepository absenceRepo) {
		this.absenceRepo = absenceRepo;
	}

	public StatEmployeeViewModel getStatEmployeeVM() {
		return statEmployeeVM;
	}

	public void setStatEmployeeVM(StatEmployeeViewModel statEmployeeVM) {
		this.statEmployeeVM = statEmployeeVM;
	}

	public DonutChartModel getModel() {
		return model;
	}

	public void setModel(DonutChartModel model) {
		this.model = model;
	}
}
