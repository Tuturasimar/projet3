package fr.isika.cda.dataset;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Absence;
import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.ar.ActivityDate;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.ArActivity;
import fr.isika.cda.entities.ar.PartDayEnum;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.assignement.UserFeedback;
import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.common.ClassContextEnum;
import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;
import fr.isika.cda.entities.common.MissionTypeEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.Config;
import fr.isika.cda.entities.config.Contract;
import fr.isika.cda.entities.config.ContractOptions;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.entities.config.ImageConfig;
import fr.isika.cda.entities.config.Options;
import fr.isika.cda.entities.users.Notification;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;

import fr.isika.cda.entities.config.FontConfig;

@Singleton
@Startup
public class DataInit {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {

		// Mock donnée d'une ESN

		Company company = new Company();
		company.setAdress("Paris");
		company.setName("BeMyTech");
		company.setStatus(StatusEnum.ACTIVE);
		company.setPhone("0606060606");
		company.setSiren("123456789");

		Company orange = new Company();
		orange.setAdress("Genève");
		orange.setName("Orange");
		orange.setStatus(StatusEnum.ACTIVE);
		orange.setPhone("0450463027");
		orange.setSiren("123456987");

		Company alten = new Company();
		alten.setAdress("Nîmes");
		alten.setName("Alten");
		alten.setStatus(StatusEnum.ACTIVE);
		alten.setPhone("0450461327");
		alten.setSiren("673456987");

		Company sopra = new Company();
		sopra.setAdress("Annecy");
		sopra.setName("Sopra Steria");
		sopra.setStatus(StatusEnum.ACTIVE);
		sopra.setPhone("0445461313");
		sopra.setSiren("673946987");

		em.persist(orange);
		em.persist(alten);
		em.persist(sopra);
		em.persist(company);

		// Mock donnée ColorConfig liée à l'ESN
		ColorConfig colorConfigDefault = new ColorConfig();
		colorConfigDefault.setBackgroundColor("7FB2AE");
		colorConfigDefault.setButtonColor("8FBCB8");
		colorConfigDefault.setTextColor("000000");
		colorConfigDefault.setTitleColor("FFFFFF");

		// Mock donnée FontCOnfig liée à l'ESN
		FontConfig fontConfigDefault = new FontConfig();
		fontConfigDefault.setFontFamily("sans-serif");

		Config config = new Config();
		config.setColorConfig(colorConfigDefault);
		config.setCompany(company);
		config.setFontConfig(fontConfigDefault);
		config.setImageConfig(new ImageConfig());

		em.persist(config);

		// Mock donnée UserData liée à user

		// Users de la company BeMyTech
		// Managers
		UserData dataManager = new UserData();
		dataManager.setFirstname("Bobby");
		dataManager.setLastname("Watson");
		dataManager.setJobEnum(JobEnum.CHEF_DE_PROJET);
		dataManager.setEmail("bobby.watson@bemytech.com");
		dataManager.setBirthday(LocalDate.of(1966, 6, 6));
		em.persist(dataManager);

		User manager = new User();
		manager.setLogin("BobbyW");
		manager.setPassword("test");
		manager.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		manager.setStatus(StatusEnum.ACTIVE);
		manager.setUserData(dataManager);
		manager.setCompany(company);
		em.persist(manager);

		UserRole managerRole = new UserRole();
		managerRole.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		managerRole.setUser(manager);
		em.persist(managerRole);

		UserData dataManager2 = new UserData();
		dataManager2.setFirstname("Olivia");
		dataManager2.setLastname("Smith");
		dataManager2.setJobEnum(JobEnum.CHEF_DE_PROJET);
		dataManager2.setEmail("olivia.smith@bemytech.com");
		dataManager2.setBirthday(LocalDate.of(1983, 11, 11));
		em.persist(dataManager2);

		User manager2 = new User();
		manager2.setLogin("OliviaS");
		manager2.setPassword("test");
		manager2.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		manager2.setStatus(StatusEnum.ACTIVE);
		manager2.setUserData(dataManager2);
		manager2.setCompany(company);
		em.persist(manager2);

		UserRole managerRole2 = new UserRole();
		managerRole2.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		managerRole2.setUser(manager2);
		em.persist(managerRole2);

		// AdminESN
		UserData adminBeMyTech = new UserData();
		adminBeMyTech.setFirstname("Gabriel");
		adminBeMyTech.setLastname("NGuyen");
		adminBeMyTech.setBirthday(LocalDate.of(1988, 6, 1));
		adminBeMyTech.setEmail("gabriel.nguyen@bemytech.com");
		adminBeMyTech.setJobEnum(JobEnum.ADMIN);
		em.persist(adminBeMyTech);

		User userAdmin = new User();
		userAdmin.setLogin("GabrielN");
		userAdmin.setPassword("test");
		userAdmin.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		userAdmin.setStatus(StatusEnum.ACTIVE);
		userAdmin.setUserData(adminBeMyTech);
		userAdmin.setCompany(company);
		em.persist(userAdmin);

		UserRole roleAdminESN = new UserRole();
		roleAdminESN.setRoleTypeEnum(RoleTypeEnum.ADMINESN);
		roleAdminESN.setUser(userAdmin);
		em.persist(roleAdminESN);

		// Salariés
		UserData alexia = new UserData();
		alexia.setFirstname("Alexia");
		alexia.setLastname("Dupont");
		alexia.setBirthday(LocalDate.of(1970, 10, 1));
		alexia.setEmail("alexia.dupont.@bemytech.com");
		alexia.setJobEnum(JobEnum.DEV);
		em.persist(alexia);

		User userSalarie1 = new User();
		userSalarie1.setLogin("AlexiaD");
		userSalarie1.setPassword("test");
		userSalarie1.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		userSalarie1.setStatus(StatusEnum.ACTIVE);
		userSalarie1.setUserData(alexia);
		userSalarie1.setManager(manager);
		userSalarie1.setCompany(company);
		em.persist(userSalarie1);

		UserRole roleSalarie1 = new UserRole();
		roleSalarie1.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie1.setUser(userSalarie1);
		em.persist(roleSalarie1);

		UserData maxime = new UserData();
		maxime.setFirstname("Maxime");
		maxime.setLastname("Martin");
		maxime.setBirthday(LocalDate.of(1990, 10, 10));
		maxime.setEmail("maxime.martin@bemytech.com");
		maxime.setJobEnum(JobEnum.DEV);
		em.persist(maxime);

		User userSalarie2 = new User();
		userSalarie2.setLogin("MaximeM");
		userSalarie2.setPassword("test");
		userSalarie2.setCreatedAt(LocalDateTime.of(2021, 9, 5, 8, 0));
		userSalarie2.setStatus(StatusEnum.ACTIVE);
		userSalarie2.setUserData(maxime);
		userSalarie2.setManager(manager2);
		userSalarie2.setCompany(company);
		em.persist(userSalarie2);

		UserRole roleSalarie2 = new UserRole();
		roleSalarie2.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie2.setUser(userSalarie2);
		em.persist(roleSalarie2);

		UserData emma = new UserData();
		emma.setFirstname("Emma");
		emma.setLastname("Wilson");
		emma.setBirthday(LocalDate.of(1999, 12, 25));
		emma.setEmail("emma.wilson.@bemytech.com");
		emma.setJobEnum(JobEnum.DEV);
		em.persist(emma);

		User userSalarie3 = new User();
		userSalarie3.setLogin("EmmaW");
		userSalarie3.setPassword("test");
		userSalarie3.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		userSalarie3.setStatus(StatusEnum.ACTIVE);
		userSalarie3.setUserData(emma);
		userSalarie3.setManager(manager);
		userSalarie3.setCompany(company);
		em.persist(userSalarie3);

		UserRole roleSalarie3 = new UserRole();
		roleSalarie3.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie3.setUser(userSalarie3);
		em.persist(roleSalarie3);

		UserData amir = new UserData();
		amir.setFirstname("Amir");
		amir.setLastname("Hassan");
		amir.setBirthday(LocalDate.of(1986, 5, 3));
		amir.setEmail("amir.hassan@bemytech.com");
		amir.setJobEnum(JobEnum.DEV);
		em.persist(amir);

		User userSalarie4 = new User();
		userSalarie4.setLogin("AmirH");
		userSalarie4.setPassword("test");
		userSalarie4.setCreatedAt(LocalDateTime.of(2020, 4, 11, 8, 0));
		userSalarie4.setStatus(StatusEnum.ACTIVE);
		userSalarie4.setUserData(amir);
		userSalarie4.setManager(manager);
		userSalarie4.setCompany(company);
		em.persist(userSalarie4);

		UserRole roleSalarie4 = new UserRole();
		roleSalarie4.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie4.setUser(userSalarie4);
		em.persist(roleSalarie4);

		UserData mei = new UserData();
		mei.setFirstname("Mei");
		mei.setLastname("Li");
		mei.setBirthday(LocalDate.of(1962, 7, 19));
		mei.setEmail("mei.li@bemytech.com");
		mei.setJobEnum(JobEnum.DEV);
		em.persist(mei);

		User userSalarie5 = new User();
		userSalarie5.setLogin("MeiL");
		userSalarie5.setPassword("test");
		userSalarie5.setCreatedAt(LocalDateTime.of(2023, 1, 15, 8, 0));
		userSalarie5.setStatus(StatusEnum.ACTIVE);
		userSalarie5.setUserData(mei);
		userSalarie5.setManager(manager);
		userSalarie5.setCompany(company);
		em.persist(userSalarie5);

		UserRole roleSalarie5 = new UserRole();
		roleSalarie5.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie5.setUser(userSalarie5);
		em.persist(roleSalarie5);

		// Super admin de la plateforme
		User superAdmin = new User();
		superAdmin.setLogin("admin");
		superAdmin.setPassword("admin");
		superAdmin.setCreatedAt(LocalDateTime.of(2019, 12, 14, 8, 0));
		superAdmin.setStatus(StatusEnum.ACTIVE);
		em.persist(superAdmin);

		UserRole superAdminRole = new UserRole();
		superAdminRole.setRoleTypeEnum(RoleTypeEnum.SUPERADMIN);
		superAdminRole.setUser(superAdmin);
		em.persist(superAdminRole);

		// Mock donnée d'un CRA
		// Pour le salarié 1 : avril mai juin à utiliser pour l'exemple
		Ar arSalarie1 = new Ar();
		arSalarie1.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie1.setArConfig(ArConfigEnum.MONTH);
		arSalarie1.setStateArEnum(StateAr.VALIDATED);
		arSalarie1.setUpdatedAt(LocalDate.of(2023, 04, 30));
		arSalarie1.setUser(userSalarie1);
		em.persist(arSalarie1);

		Ar ar2Salarie1 = new Ar();
		ar2Salarie1.setCreatedAt(LocalDate.of(2023, 05, 01));
		ar2Salarie1.setArConfig(ArConfigEnum.MONTH);
		ar2Salarie1.setStateArEnum(StateAr.INHOLD);
		ar2Salarie1.setUpdatedAt(LocalDate.of(2023, 05, 28));
		ar2Salarie1.setUser(userSalarie1);
		em.persist(ar2Salarie1);

		Ar ar3Salarie1 = new Ar();
		ar3Salarie1.setCreatedAt(LocalDate.of(2023, 06, 1));
		ar3Salarie1.setArConfig(ArConfigEnum.MONTH);
		ar3Salarie1.setStateArEnum(StateAr.DRAFT);
		ar3Salarie1.setUpdatedAt(LocalDate.of(2023, 06, 8));
		ar3Salarie1.setUser(userSalarie1);
		em.persist(ar3Salarie1);

		// Pour le salarié 2 : avril mai juin
		Ar arSalarie2 = new Ar();
		arSalarie2.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie2.setArConfig(ArConfigEnum.MONTH);
		arSalarie2.setStateArEnum(StateAr.VALIDATED);
		arSalarie2.setUpdatedAt(LocalDate.of(2023, 04, 29));
		arSalarie2.setUser(userSalarie2);
		em.persist(arSalarie2);

		Ar ar2Salarie2 = new Ar();
		ar2Salarie2.setCreatedAt(LocalDate.of(2023, 05, 01));
		ar2Salarie2.setArConfig(ArConfigEnum.MONTH);
		ar2Salarie2.setStateArEnum(StateAr.VALIDATED);
		ar2Salarie2.setUpdatedAt(LocalDate.of(2023, 05, 30));
		ar2Salarie2.setUser(userSalarie2);
		em.persist(ar2Salarie2);

		Ar ar3Salarie2 = new Ar();
		ar3Salarie2.setCreatedAt(LocalDate.of(2023, 06, 1));
		ar3Salarie2.setArConfig(ArConfigEnum.MONTH);
		ar3Salarie2.setStateArEnum(StateAr.DRAFT);
		ar3Salarie2.setUpdatedAt(LocalDate.of(2023, 06, 3));
		ar3Salarie2.setUser(userSalarie2);
		em.persist(ar3Salarie2);

		// Pour le salarié 3 : avril mai juin
		Ar arSalarie3 = new Ar();
		arSalarie3.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie3.setArConfig(ArConfigEnum.MONTH);
		arSalarie3.setStateArEnum(StateAr.VALIDATED);
		arSalarie3.setUpdatedAt(LocalDate.of(2023, 04, 29));
		arSalarie3.setUser(userSalarie3);
		em.persist(arSalarie3);

		Ar ar2Salarie3 = new Ar();
		ar2Salarie3.setCreatedAt(LocalDate.of(2023, 05, 01));
		ar2Salarie3.setArConfig(ArConfigEnum.MONTH);
		ar2Salarie3.setStateArEnum(StateAr.VALIDATED);
		ar2Salarie3.setUpdatedAt(LocalDate.of(2023, 05, 30));
		ar2Salarie3.setUser(userSalarie3);
		em.persist(ar2Salarie3);

		Ar ar3Salarie3 = new Ar();
		ar3Salarie3.setCreatedAt(LocalDate.of(2023, 06, 1));
		ar3Salarie3.setArConfig(ArConfigEnum.MONTH);
		ar3Salarie3.setStateArEnum(StateAr.DRAFT);
		ar3Salarie3.setUpdatedAt(LocalDate.of(2023, 06, 3));
		ar3Salarie3.setUser(userSalarie3);
		em.persist(ar3Salarie3);

		// Pour le salarié 4 : avril mai juin
		Ar arSalarie4 = new Ar();
		arSalarie4.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie4.setArConfig(ArConfigEnum.MONTH);
		arSalarie4.setStateArEnum(StateAr.VALIDATED);
		arSalarie4.setUpdatedAt(LocalDate.of(2023, 04, 29));
		arSalarie4.setUser(userSalarie4);
		em.persist(arSalarie4);

		Ar ar2Salarie4 = new Ar();
		ar2Salarie4.setCreatedAt(LocalDate.of(2023, 05, 01));
		ar2Salarie4.setArConfig(ArConfigEnum.MONTH);
		ar2Salarie4.setStateArEnum(StateAr.VALIDATED);
		ar2Salarie4.setUpdatedAt(LocalDate.of(2023, 05, 30));
		ar2Salarie4.setUser(userSalarie4);
		em.persist(ar2Salarie4);

		Ar ar3Salarie4 = new Ar();
		ar3Salarie4.setCreatedAt(LocalDate.of(2023, 06, 1));
		ar3Salarie4.setArConfig(ArConfigEnum.MONTH);
		ar3Salarie4.setStateArEnum(StateAr.DRAFT);
		ar3Salarie4.setUpdatedAt(LocalDate.of(2023, 06, 3));
		ar3Salarie4.setUser(userSalarie4);
		em.persist(ar3Salarie4);

		// Pour le salarié 5 : avril mai juin
		Ar arSalarie5 = new Ar();
		arSalarie5.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie5.setArConfig(ArConfigEnum.MONTH);
		arSalarie5.setStateArEnum(StateAr.VALIDATED);
		arSalarie5.setUpdatedAt(LocalDate.of(2023, 04, 29));
		arSalarie5.setUser(userSalarie5);
		em.persist(arSalarie5);

		Ar ar2Salarie5 = new Ar();
		ar2Salarie5.setCreatedAt(LocalDate.of(2023, 05, 01));
		ar2Salarie5.setArConfig(ArConfigEnum.MONTH);
		ar2Salarie5.setStateArEnum(StateAr.INHOLD);
		ar2Salarie5.setUpdatedAt(LocalDate.of(2023, 05, 20));
		ar2Salarie5.setUser(userSalarie5);
		em.persist(ar2Salarie5);

		Ar ar3Salarie5 = new Ar();
		ar3Salarie5.setCreatedAt(LocalDate.of(2023, 06, 1));
		ar3Salarie5.setArConfig(ArConfigEnum.MONTH);
		ar3Salarie5.setStateArEnum(StateAr.DRAFT);
		ar3Salarie5.setUpdatedAt(LocalDate.of(2023, 06, 3));
		ar3Salarie5.setUser(userSalarie5);
		em.persist(ar3Salarie5);

		// Mock donnée d'une activity
		Mission mission = new Mission();
		mission.setCreator(manager);
		mission.setLabelActivity("Forfait SNCF");
		mission.setMissionStart(LocalDate.of(2023, 3, 23));
		mission.setMissionEnd(LocalDate.of(2024, 6, 6));
		mission.setStatus(StatusEnum.ACTIVE);
		mission.setMissionType(MissionTypeEnum.FPC);

		Mission mission2 = new Mission();
		mission2.setCreator(manager);
		mission2.setLabelActivity("Régie L'Oréal");
		mission2.setMissionStart(LocalDate.of(2019, 11, 1));
		mission2.setMissionEnd(LocalDate.of(2023, 4, 30));
		mission2.setStatus(StatusEnum.INACTIVE);
		mission2.setMissionType(MissionTypeEnum.TMC);

		Mission mission3 = new Mission();
		mission3.setCreator(manager);
		mission3.setLabelActivity("Forfait Groupama");
		mission3.setMissionStart(LocalDate.of(2022, 4, 14));
		mission3.setMissionEnd(LocalDate.of(2026, 12, 31));
		mission3.setStatus(StatusEnum.ACTIVE);
		mission3.setMissionType(MissionTypeEnum.FPC);
		
		Mission mission4 = new Mission();
		mission4.setCreator(manager);
		mission4.setLabelActivity("Régie Crédit Mutuel");
		mission4.setMissionStart(LocalDate.of(2020, 4, 14));
		mission4.setMissionEnd(LocalDate.of(2026, 12, 31));
		mission4.setStatus(StatusEnum.ACTIVE);
		mission4.setMissionType(MissionTypeEnum.TMC);

		em.persist(mission);
		em.persist(mission2);
		em.persist(mission3);
		em.persist(mission4);

		Absence absence = new Absence();
		absence.setCreator(manager);
		absence.setLabelActivity("Congés payés");
		
		Absence absence2 = new Absence();
		absence2.setCreator(manager);
		absence2.setLabelActivity("Arrêt maladie");
		
		Absence absence3 = new Absence();
		absence3.setCreator(manager);
		absence3.setLabelActivity("Absence injustifiée");

		em.persist(absence);
		em.persist(absence2);
		em.persist(absence3);

		Formation formation = new Formation();
		formation.setCreator(manager);
		formation.setLabelActivity("Formation Scrum Master");
		formation.setDescription("Apprenez les bases de Scrum et devenez plus agile !");
		formation.setFormationStatus(FormationStatusEnum.FOLLOWED);
		formation.setLocation("La Défense");
		formation.setLocationFormation(LocationFormationEnum.EXTERN);
		formation.setNbOfDays(8);
		formation.setStatus(StatusEnum.ACTIVE);

		Formation formation2 = new Formation();
		formation2.setCreator(manager);
		formation2.setLabelActivity("Formation GIT");
		formation2.setDescription("Maitrisez le logiciel de gestion de versions le plus utilisé");
		formation2.setFormationStatus(FormationStatusEnum.GIVEN);
		formation2.setLocation("BeMyTech");
		formation2.setLocationFormation(LocationFormationEnum.INTERN);
		formation2.setNbOfDays(5);
		formation2.setStatus(StatusEnum.ACTIVE);

		em.persist(formation);
		em.persist(formation2);

		// Mock donnée de MissionUser pour le salarié 1
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setComment("Test de mission");
		userFeedback.setGradeClientRelation(4);
		userFeedback.setGradeManager(3);
		userFeedback.setGradeMission(2);
		userFeedback.setGradeUserComfort(4);

		em.persist(userFeedback);

		MissionUser missionUser = new MissionUser();
		missionUser.setUser(userSalarie1);
		missionUser.setMissionState(StatusEnum.ACTIVE);
		missionUser.setAdr(200.68f);
		missionUser.setMission(mission);
		missionUser.setUserFeedback(userFeedback);

		em.persist(missionUser);


		//Pour le salarié 5
		MissionUser missionUser2 = new MissionUser();
		missionUser2.setUser(userSalarie5);
		missionUser2.setMissionState(StatusEnum.ACTIVE);
		missionUser2.setAdr(280.30f);
		missionUser2.setMission(mission4);

		em.persist(missionUser);
		// Mock donnée d'une ArActivity

		//Correspond au CRA de mai du salarié 1
		ArActivity arActivity1 = new ArActivity();
		arActivity1.setAr(ar2Salarie1);
		arActivity1.setActivity(mission);

		ArActivity arActivity2 = new ArActivity();
		arActivity2.setAr(ar2Salarie1);
		arActivity2.setActivity(absence);

		//Correspond au CRA de mai du salarié 5
		ArActivity arActivity3 = new ArActivity();
		arActivity3.setAr(ar2Salarie5);
		arActivity3.setActivity(formation);

		ArActivity arActivity4 = new ArActivity();
		arActivity4.setAr(ar2Salarie5);
		arActivity4.setActivity(absence);
		
		ArActivity arActivity5 = new ArActivity();
		arActivity5.setAr(ar2Salarie5);
		arActivity5.setActivity(mission4);

		em.persist(arActivity1);
		em.persist(arActivity2);
		em.persist(arActivity3);
		em.persist(arActivity4);
		em.persist(arActivity5);

		// Mock donnée d'une ActivityDate
		//CRA de mai du salarié 1
		ActivityDate activityDate = new ActivityDate();
		activityDate.setArActivity(arActivity1);
		activityDate.setDate(LocalDate.of(2023, 5, 2));
		activityDate.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate.setRemote(false);
		em.persist(activityDate);

		ActivityDate activityDate2 = new ActivityDate();
		activityDate2.setArActivity(arActivity1);
		activityDate2.setDate(LocalDate.of(2023, 5, 3));
		activityDate2.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate2.setRemote(false);
		em.persist(activityDate2);
		
		ActivityDate activityDate3 = new ActivityDate();
		activityDate3.setArActivity(arActivity1);
		activityDate3.setDate(LocalDate.of(2023, 5, 4));
		activityDate3.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate3.setRemote(false);
		em.persist(activityDate3);
		
		ActivityDate activityDate4 = new ActivityDate();
		activityDate4.setArActivity(arActivity1);
		activityDate4.setDate(LocalDate.of(2023, 5, 5));
		activityDate4.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate4.setRemote(false);
		em.persist(activityDate4);

		ActivityDate activityDate5 = new ActivityDate();
		activityDate5.setArActivity(arActivity1);
		activityDate5.setDate(LocalDate.of(2023, 5, 9));
		activityDate5.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate5.setRemote(false);
		em.persist(activityDate5);

		ActivityDate activityDate6 = new ActivityDate();
		activityDate6.setArActivity(arActivity1);
		activityDate6.setDate(LocalDate.of(2023, 5, 10));
		activityDate6.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate6.setRemote(false);
		em.persist(activityDate6);

		ActivityDate activityDate7 = new ActivityDate();
		activityDate7.setArActivity(arActivity1);
		activityDate7.setDate(LocalDate.of(2023, 5, 11));
		activityDate7.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate7.setRemote(false);
		em.persist(activityDate7);
		
		ActivityDate activityDate8 = new ActivityDate();
		activityDate8.setArActivity(arActivity1);
		activityDate8.setDate(LocalDate.of(2023, 5, 12));
		activityDate8.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate8.setRemote(false);
		em.persist(activityDate8);
		
		

		// Mock donnée OptionsForfait

		// Mock données Options(1,2,3)
		Options option1 = new Options();
		option1.setLabel("Personnalisation poussée");
		option1.setDescription(
				"Permet de définir n’importe quelle couleur pour l’application (couleur principale, secondaire, boutons)");
		option1.setPrice(20);

		em.persist(option1);

		Options option2 = new Options();
		option2.setLabel("FeedBack des missions (salarié)");
		option2.setDescription(
				"Permet aux salariés de votre ESN de transmettre au sein de l’application leur avis sur leur mission en cours.");
		option2.setPrice(100);

		em.persist(option2);

		Options option3 = new Options();
		option3.setLabel("Auto-Remplissage du CRA");
		option3.setDescription("Permet le remplissage automatique du CRA, c’est très pratique !");
		option3.setPrice(200);

		em.persist(option3);

		// Mock données Features(Forfait Basique, Premium, Ultimate)

		Feature feature1 = new Feature();
		feature1.setLabel("Forfait BASIQUE");
		feature1.setNbOfCollaborators(50);
		feature1.setArConfig(ArConfigEnum.MONTH);
		feature1.setPrice(200);
		feature1.setNbOfOptions(1);

		em.persist(feature1);

		Feature feature2 = new Feature();
		feature2.setLabel("Forfait PREMIUM");
		feature2.setNbOfCollaborators(100);
		feature2.setArConfig(ArConfigEnum.MONTH);
		feature2.setPrice(375);
		feature2.setNbOfOptions(2);

		em.persist(feature2);

		Feature feature3 = new Feature();
		feature3.setLabel("Forfait ULTIMATE");
		feature3.setNbOfCollaborators(200);
		feature3.setArConfig(ArConfigEnum.MONTH);
		feature3.setPrice(666);
		feature3.setNbOfOptions(3);

		em.persist(feature3);

		Notification notif = new Notification();
		notif.setClassContext(ClassContextEnum.SUCCESS);
		notif.setMessageContent("Bienvenue");
		notif.setUser(userSalarie5);

		em.persist(notif);

		// Mock Afficher détails d'un forfait

		// Liés au nom de la compagny, le nom du contrat, le prix du contrat, la date de
		// création du contrat, le nom du forfait.

		Contract contract = new Contract();
		contract.setCreatedAt(LocalDate.now());
		contract.setFeature(feature2);
		contract.setPrice(495f);

		ContractOptions co = new ContractOptions();
		co.setContract(contract);
		co.setOption(option1);

		ContractOptions co2 = new ContractOptions();
		co2.setContract(contract);
		co2.setOption(option2);

		company.setContract(contract);

		em.persist(co);
		em.persist(co2);
		em.persist(contract);
		em.merge(company);
	}

}
