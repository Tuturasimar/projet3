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
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
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

		// Mock donnée d'un CRA
		Ar ar = new Ar();
		ar.setCreatedAt(LocalDate.now());
		ar.setArConfig(ArConfigEnum.MONTH);
		ar.setStateArEnum(StateAr.DRAFT);
		ar.setUpdatedAt(LocalDate.now());
		ar.setUser(user);

		em.persist(ar);

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

	}

}
