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
import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserData;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
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

		em.persist(data);

		// Mock donnée User
		User user = new User();
		user.setLogin("tutu");
		user.setPassword("tutu");
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus(StatusEnum.ACTIVE);
		user.setUserData(data);
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
		ar.setCreatedAt(LocalDate.now());
		ar.setArConfig(ArConfigEnum.MONTH);
		ar.setStateArEnum(StateAr.DRAFT);
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

		em.persist(mission);

		Absence absence = new Absence();
		absence.setCreatorId(1L);
		absence.setLabelActivity("Absence Test");

		em.persist(absence);

		Formation formation = new Formation();
		formation.setCreatorId(1L);
		formation.setLabelActivity("Formation Test");

		em.persist(formation);

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

		// Mock donnée OptionsForfait
		Options option1 = new Options();
		option1.setLabel("customColor");
		option1.setDescription("cadre pour choisir une couleur");
		option1.setPrice(20);

		em.persist(option1);
	}

}
