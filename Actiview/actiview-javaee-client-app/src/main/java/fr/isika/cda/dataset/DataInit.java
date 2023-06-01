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
import fr.isika.cda.entities.common.ArConfigEnum;

import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;
import fr.isika.cda.entities.common.MissionTypeEnum;

import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.entities.config.Options;

@Singleton
@Startup
public class DataInit {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {

		// Mock donnée UserData liée à user
		UserData data = new UserData();
		data.setFirstname("Toto");
		data.setLastname("Tutu");
		data.setBirthday(LocalDate.now());
		data.setEmail("toto.tutu@titi.com");
		data.setJobEnum(JobEnum.DEV);
		
		UserData dataManager = new UserData();
		dataManager.setFirstname("Bobby");
		dataManager.setLastname("Watson");
		dataManager.setJobEnum(JobEnum.CHEF_DE_PROJET);
		dataManager.setEmail("bobby.watson@gmail.com");
		dataManager.setBirthday(LocalDate.of(1966, 6, 6));

		em.persist(data);

		em.persist(dataManager);
		
		// Mock donnée User
		
		User manager = new User();
		manager.setLogin("Bobby");
		manager.setPassword("test");
		manager.setStatus(StatusEnum.ACTIVE);
		manager.setUserData(dataManager);
		
		User user = new User();
		user.setLogin("tutu");
		user.setPassword("tutu");
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus(StatusEnum.ACTIVE);
		user.setUserData(data);
		user.setManager(manager);
		
		em.persist(manager);
		em.persist(user);
		

		// Mock donnée UserRole liée à user
		UserRole role = new UserRole();
		role.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		role.setUser(user);

		em.persist(role);

		User user2 = new User();
		user2.setLogin("actiview");
		user2.setPassword("111");
		user2.setStatus(StatusEnum.ACTIVE);

		UserRole role2 = new UserRole();
		role2.setRoleTypeEnum(RoleTypeEnum.ADMINESN);

		role2.setUser(user2);

		em.persist(user2);
		em.persist(role2);

		// Mock données pour deux manager (pour tester le getManager)
		UserData dataManager1 = new UserData();
		dataManager1.setFirstname("Bob");
		dataManager1.setLastname("LeManager");
		dataManager1.setBirthday(LocalDate.now());
		dataManager1.setEmail("bob.lemanager@actiview.com");
		dataManager1.setJobEnum(JobEnum.DEV);
		em.persist(dataManager1);

		role2.setUser(user);
		
		UserRole managerRole = new UserRole();
		managerRole.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		managerRole.setUser(manager);
	
		
		em.persist(user2);
		em.persist(role2);
		em.persist(managerRole);

		
		User userManager1 = new User();
		userManager1.setLogin("boblemanager");
		userManager1.setPassword("bobob");
		userManager1.setCreatedAt(LocalDateTime.now());
		userManager1.setStatus(StatusEnum.ACTIVE);
		userManager1.setUserData(dataManager1);
		em.persist(userManager1);

		UserRole roleManager1 = new UserRole();
		roleManager1.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		roleManager1.setUser(userManager1);
		em.persist(roleManager1);
		
		UserData dataManager2 = new UserData();
		dataManager2.setFirstname("Lucie");
		dataManager2.setLastname("Fer");
		dataManager2.setBirthday(LocalDate.now());
		dataManager2.setEmail("lucie.fer@actiview.com");
		dataManager2.setJobEnum(JobEnum.CHEF_DE_PROJET);
		em.persist(dataManager2);
		
		User userManager2 = new User();
		userManager2.setLogin("luciefer");
		userManager2.setPassword("lulu");
		userManager2.setCreatedAt(LocalDateTime.now());
		userManager2.setStatus(StatusEnum.ACTIVE);
		userManager2.setUserData(dataManager2);
		em.persist(userManager2);

		UserRole roleManager2 = new UserRole();
		roleManager2.setRoleTypeEnum(RoleTypeEnum.SALARIE);
		roleManager2.setUser(userManager2);
		em.persist(roleManager2);
		
		UserRole role2Manager2 = new UserRole();
		role2Manager2.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		role2Manager2.setUser(userManager2);
		em.persist(role2Manager2);

		// Mock donnée d'un CRA
		Ar ar = new Ar();
		ar.setCreatedAt(LocalDate.of(2023,5,6));
		ar.setArConfig(ArConfigEnum.MONTH);
		ar.setStateArEnum(StateAr.VALIDATED);
		ar.setUpdatedAt(LocalDate.now());
		ar.setUser(user);

		Ar ar2 = new Ar();
		ar2.setCreatedAt(LocalDate.of(2023, 04, 23));

		em.persist(ar);
		em.persist(ar2);

		// Mock donnée d'une activity
		Mission mission = new Mission();
		mission.setCreatorId(1L);
		mission.setLabelActivity("Mission Test");
		mission.setMissionStart(LocalDate.of(2023, 4, 23));
		mission.setMissionEnd(LocalDate.of(2024, 6, 6));
		mission.setStatus(StatusEnum.ACTIVE);
		mission.setMissionType(MissionTypeEnum.TMC);
		
		Mission mission2 = new Mission();
		mission2.setCreatorId(1L);
		mission2.setLabelActivity("Ancienne mission");
		mission2.setMissionStart(LocalDate.of(2022, 4, 23));
		mission2.setMissionEnd(LocalDate.of(2023, 4, 25));
		mission2.setStatus(StatusEnum.INACTIVE);
		mission2.setMissionType(MissionTypeEnum.TMC);
		
		Mission mission3 = new Mission();
		mission3.setCreatorId(1L);
		mission3.setLabelActivity("Mission forfait");
		mission3.setMissionStart(LocalDate.of(2023, 4, 23));
		mission3.setMissionEnd(LocalDate.of(2026, 6, 6));
		mission3.setStatus(StatusEnum.ACTIVE);
		mission3.setMissionType(MissionTypeEnum.FPC);

		em.persist(mission);
		em.persist(mission2);
		em.persist(mission3);

		Absence absence = new Absence();
		absence.setCreatorId(1L);
		absence.setLabelActivity("Absence Test");

		em.persist(absence);

		Formation formation = new Formation();
		formation.setCreatorId(1L);
		formation.setLabelActivity("Formation Test");
		formation.setDescription("C'est une formation test");
		formation.setFormationStatus(FormationStatusEnum.FOLLOWED);
		formation.setLocation("N'importe où");
		formation.setLocationFormation(LocationFormationEnum.EXTERN);
		formation.setNbOfDays(8);
		formation.setStatus(StatusEnum.ACTIVE);
		
		Formation formation2 = new Formation();
		formation2.setCreatorId(1L);
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
		
		MissionUser missionUser = new MissionUser();
		missionUser.setUser(user);
		missionUser.setMissionState(StatusEnum.ACTIVE);
		missionUser.setAdr(200.68f);
		missionUser.setMission(mission);
		
		em.persist(missionUser);

		// Mock donnée d'une ArActivity

		ArActivity arActivity = new ArActivity();
		arActivity.setAr(ar);
		arActivity.setActivity(mission);

		em.persist(arActivity);

		// Mock donnée d'une ActivityDate

		ActivityDate activityDate = new ActivityDate();
		activityDate.setArActivity(arActivity);
		activityDate.setDate(LocalDate.now());
		activityDate.setPartOfDay(PartDayEnum.ALLDAY);
		activityDate.setRemote(false);

		em.persist(activityDate);

		//Mock donnée d'une ESN
		
		Company company = new Company();
		company.setAdress("Je suis une adresse");
		company.setName("BeMyTech");
		company.setStatus(StatusEnum.ACTIVE);
		
		em.persist(company);


		// Mock donnée OptionsForfait

  // Mock données Options(1,2,3)
		Options option1 = new Options();
		option1.setLabel("Personnalisation poussée");
		option1.setDescription("Permet de définir n’importe quelle couleur pour l’application (couleur principale, secondaire, boutons)");
		option1.setPrice(20);

		em.persist(option1);

		
		Options option2 = new Options();
		option2.setLabel("FeedBack des missions (salarié)");
		option2.setDescription("Permet aux salariés de votre ESN de transmettre au sein de l’application leur avis sur leur mission en cours.");
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
		

	}

}
