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
		role2.setUser(user);
		
		UserRole managerRole = new UserRole();
		managerRole.setRoleTypeEnum(RoleTypeEnum.MANAGER);
		managerRole.setUser(manager);
	
		
		em.persist(user2);
		em.persist(role2);
		em.persist(managerRole);
		

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
		
		// Mock donnée OptionsForfait
		Options option1 = new Options();
		option1.setLabel("customColor");
		option1.setDescription("cadre pour choisir une couleur");
		option1.setPrice(20);
		
		em.persist(option1);
		
		
	}

}
