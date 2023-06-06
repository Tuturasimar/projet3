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

@Singleton
@Startup
public class DataInit {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		
		// Mock donnée d'une ESN 

		Company company = new Company();
		company.setAdress("Je suis une adresse");
		company.setName("BeMyTech");
		company.setStatus(StatusEnum.ACTIVE);
		company.setPhone(0606060606);
		company.setSiren(123456789);		

		em.persist(company);
		
		//Mock donnée ColorConfig liée à l'ESN
		ColorConfig colorConfigDefault = new ColorConfig();
		colorConfigDefault.setBackgroundColor("7FB2AE");
		colorConfigDefault.setButtonColor("8FBCB8");
		colorConfigDefault.setTextColor("000000");
		colorConfigDefault.setTitleColor("FFFFFF");
		
		Config config = new Config();
		config.setColorConfig(colorConfigDefault);
		config.setCompany(company);
		config.setImageConfig(new ImageConfig());
		
		em.persist(config);

		// Mock donnée UserData liée à user
		UserData data = new UserData();
		data.setFirstname("Toto");
		data.setLastname("Tutu");
		data.setBirthday(LocalDate.of(1970, 1, 1));
		data.setEmail("toto.tutu@titi.com");
		data.setJobEnum(JobEnum.DEV);

		UserData dataManager = new UserData();
		dataManager.setFirstname("Bobby");
		dataManager.setLastname("Watson");
		dataManager.setJobEnum(JobEnum.CHEF_DE_PROJET);
		dataManager.setEmail("bobby.watson@gmail.com");
		dataManager.setBirthday(LocalDate.of(1966, 6, 6));

		UserData dataSalarie1 = new UserData();
		dataSalarie1.setFirstname("Bob");
		dataSalarie1.setLastname("LeSalarié");
		dataSalarie1.setBirthday(LocalDate.of(1990, 10, 10));
		dataSalarie1.setEmail("bob.lesalarie@actiview.com");
		dataSalarie1.setJobEnum(JobEnum.DEV);

		UserData dataSalarie2 = new UserData();
		dataSalarie2.setFirstname("Lucie");
		dataSalarie2.setLastname("Fer");
		dataSalarie2.setBirthday(LocalDate.of(1988, 6, 1));
		dataSalarie2.setEmail("lucie.fer@actiview.com");
		dataSalarie2.setJobEnum(JobEnum.DEV);

		em.persist(data);
		em.persist(dataManager);
		em.persist(dataSalarie1);
		em.persist(dataSalarie2);

		// Mock donnée User

		User manager = new User();
		manager.setLogin("Bobby");
		manager.setPassword("test");
		manager.setStatus(StatusEnum.ACTIVE);
		manager.setUserData(dataManager);
		manager.setCompany(company);
		

		User user = new User();
		user.setLogin("tutu");
		user.setPassword("tutu");
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus(StatusEnum.ACTIVE);
		user.setUserData(data);
		user.setManager(manager);
		user.setCompany(company);

		User userSalarie1 = new User();
		userSalarie1.setLogin("boblesalarie");
		userSalarie1.setPassword("bobob");
		userSalarie1.setCreatedAt(LocalDateTime.now());
		userSalarie1.setStatus(StatusEnum.ACTIVE);
		userSalarie1.setUserData(dataSalarie1);
		userSalarie1.setManager(manager);
		userSalarie1.setCompany(company);

		User userSalarie2 = new User();
		userSalarie2.setLogin("luciefer");
		userSalarie2.setPassword("lulu");
		userSalarie2.setCreatedAt(LocalDateTime.now());
		userSalarie2.setStatus(StatusEnum.ACTIVE);
		userSalarie2.setUserData(dataSalarie2);
		userSalarie2.setManager(manager);
		userSalarie2.setCompany(company);

		em.persist(manager);
		em.persist(user);
		em.persist(userSalarie1);
		em.persist(userSalarie2);

		// Mock donnée UserRole liée à user
		UserRole role = new UserRole();
		role.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		role.setUser(user);

		UserRole managerRole = new UserRole();
		managerRole.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		managerRole.setUser(manager);

		UserRole roleSalarie1 = new UserRole();
		roleSalarie1.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie1.setUser(userSalarie1);

		UserRole roleSalarie2 = new UserRole();
		roleSalarie2.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleSalarie2.setUser(userSalarie2);
		
		UserRole role2Salarie2 = new UserRole();
		role2Salarie2.setRoleTypeEnum(RoleTypeEnum.ADMINESN);
		role2Salarie2.setUser(userSalarie2);

		em.persist(role);
		em.persist(managerRole);
		em.persist(roleSalarie1);
		em.persist(roleSalarie2);
		em.persist(role2Salarie2);
		

		// Mock donnée d'un CRA
		Ar ar = new Ar();
		ar.setCreatedAt(LocalDate.of(2023, 5, 6));
		ar.setArConfig(ArConfigEnum.MONTH);
		ar.setStateArEnum(StateAr.VALIDATED);
		ar.setUpdatedAt(LocalDate.now());
		ar.setUser(user);
		
		Ar arSalarie = new Ar();
		arSalarie.setCreatedAt(LocalDate.of(2023, 04, 30));
		arSalarie.setArConfig(ArConfigEnum.MONTH);
		arSalarie.setStateArEnum(StateAr.DRAFT);
		arSalarie.setUpdatedAt(LocalDate.now());
		arSalarie.setUser(user);

		Ar arSalarie1 = new Ar();
		arSalarie1.setCreatedAt(LocalDate.of(2023, 04, 23));
		arSalarie1.setArConfig(ArConfigEnum.MONTH);
		arSalarie1.setStateArEnum(StateAr.VALIDATED);
		arSalarie1.setUpdatedAt(LocalDate.now());
		arSalarie1.setUser(userSalarie1);
		
		Ar arSalarie2 = new Ar();
		arSalarie2.setCreatedAt(LocalDate.of(2023, 04, 01));
		arSalarie2.setArConfig(ArConfigEnum.MONTH);
		arSalarie2.setStateArEnum(StateAr.INHOLD);
		arSalarie2.setUpdatedAt(LocalDate.now());
		arSalarie2.setUser(userSalarie2);
		
		em.persist(ar);
		em.persist(arSalarie);
		em.persist(arSalarie1);
		em.persist(arSalarie2);

		// Mock donnée d'une activity
		Mission mission = new Mission();
		mission.setCreator(manager);
		mission.setLabelActivity("Mission Test");
		mission.setMissionStart(LocalDate.of(2023, 4, 23));
		mission.setMissionEnd(LocalDate.of(2024, 6, 6));
		mission.setStatus(StatusEnum.ACTIVE);
		mission.setMissionType(MissionTypeEnum.TMC);

		Mission mission2 = new Mission();
		mission2.setCreator(manager);
		mission2.setLabelActivity("Ancienne mission");
		mission2.setMissionStart(LocalDate.of(2022, 4, 23));
		mission2.setMissionEnd(LocalDate.of(2023, 4, 25));
		mission2.setStatus(StatusEnum.INACTIVE);
		mission2.setMissionType(MissionTypeEnum.TMC);

		Mission mission3 = new Mission();
		mission3.setCreator(manager);
		mission3.setLabelActivity("Mission forfait");
		mission3.setMissionStart(LocalDate.of(2023, 4, 23));
		mission3.setMissionEnd(LocalDate.of(2026, 6, 6));
		mission3.setStatus(StatusEnum.ACTIVE);
		mission3.setMissionType(MissionTypeEnum.FPC);

		em.persist(mission);
		em.persist(mission2);
		em.persist(mission3);

		Absence absence = new Absence();
		absence.setCreator(manager);
		absence.setLabelActivity("Absence Test");

		em.persist(absence);

		Formation formation = new Formation();
		formation.setCreator(manager);
		formation.setLabelActivity("Formation Test");
		formation.setDescription("C'est une formation test");
		formation.setFormationStatus(FormationStatusEnum.FOLLOWED);
		formation.setLocation("N'importe où");
		formation.setLocationFormation(LocationFormationEnum.EXTERN);
		formation.setNbOfDays(8);
		formation.setStatus(StatusEnum.ACTIVE);

		Formation formation2 = new Formation();
		formation2.setCreator(manager);
		formation2.setLabelActivity("Autre formation");
		formation2.setDescription("Pour tester des choses");
		formation2.setFormationStatus(FormationStatusEnum.GIVEN);
		formation2.setLocation("Chez nous");
		formation2.setLocationFormation(LocationFormationEnum.INTERN);
		formation2.setNbOfDays(5);
		formation2.setStatus(StatusEnum.ACTIVE);

		em.persist(formation);
		em.persist(formation2);

		// Mock donnée de MissionUser
		
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setComment("Test de mission");
		userFeedback.setGradeClientRelation(4);
		userFeedback.setGradeManager(3);
		userFeedback.setGradeMission(2);
		userFeedback.setGradeUserComfort(4);
		
		em.persist(userFeedback);

		MissionUser missionUser = new MissionUser();
		missionUser.setUser(user);
		missionUser.setMissionState(StatusEnum.ACTIVE);
		missionUser.setAdr(200.68f);
		missionUser.setMission(mission);
		missionUser.setUserFeedback(userFeedback);

		em.persist(missionUser);

		// Mock donnée d'une ArActivity

		ArActivity arActivity = new ArActivity();
		arActivity.setAr(ar);
		arActivity.setActivity(mission);

		ArActivity arActivity2 = new ArActivity();
		arActivity2.setAr(arSalarie2);
		arActivity2.setActivity(mission);
		
		ArActivity arActivity3 = new ArActivity();
		arActivity3.setAr(ar);
		arActivity3.setActivity(formation);
		
		ArActivity arActivity4 = new ArActivity();
		arActivity4.setAr(ar);
		arActivity4.setActivity(absence);

		em.persist(arActivity);
		em.persist(arActivity2);
		em.persist(arActivity3);
		em.persist(arActivity4);

		// Mock donnée d'une ActivityDate

		ActivityDate activityDate = new ActivityDate();
		activityDate.setArActivity(arActivity);
		activityDate.setDate(LocalDate.of(2023, 5, 6));
		activityDate.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate.setRemote(false);
		
		ActivityDate activityDate2 = new ActivityDate();
		activityDate2.setArActivity(arActivity2);
		activityDate2.setDate(LocalDate.of(2023, 4, 6));
		activityDate2.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate2.setRemote(false);
		
		ActivityDate activityDate3 = new ActivityDate();
		activityDate3.setArActivity(arActivity2);
		activityDate3.setDate(LocalDate.of(2023, 4, 11));
		activityDate3.setPartOfDay(PartDayEnum.AFTERNOON);
		activityDate3.setRemote(false);
		
		ActivityDate activityDate4 = new ActivityDate();
		activityDate4.setArActivity(arActivity2);
		activityDate4.setDate(LocalDate.of(2023, 4, 13));
		activityDate4.setPartOfDay(PartDayEnum.MORNING);
		activityDate4.setRemote(false);
		
		ActivityDate activityDate5 = new ActivityDate();
		activityDate3.setArActivity(arActivity3);
		activityDate3.setDate(LocalDate.of(2023, 4, 14));
		activityDate3.setPartOfDay(PartDayEnum.AFTERNOON);
		activityDate3.setRemote(false);
		
		ActivityDate activityDate6 = new ActivityDate();
		activityDate4.setArActivity(arActivity4);
		activityDate4.setDate(LocalDate.of(2023, 4, 14));
		activityDate4.setPartOfDay(PartDayEnum.MORNING);
		activityDate4.setRemote(false);

		em.persist(activityDate);
		em.persist(activityDate2);
		em.persist(activityDate3);
		em.persist(activityDate4);
		em.persist(activityDate5);
		em.persist(activityDate6);

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
		option3.setDescription("Permet le remplissage automatique du CRA, C’est très pratique !");
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
		notif.setMessageContent("Au secours");
		notif.setUser(user);
		
		em.persist(notif);
		
		// Mock Afficher détails d'un forfait
		
		//Liés au nom de la compagny, le nom du contrat, le prix du contrat, la date de création du contrat, le nom du forfait.
		
		

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
