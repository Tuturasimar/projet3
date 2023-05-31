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
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.entities.config.Options;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserRole;


@Singleton
@Startup
public class DataInit {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {

		// Mock donnée User
		User user = new User();
		user.setCreatedAt(LocalDateTime.now());
		user.setLogin("actiview");
		user.setPassword("111");
		user.setStatus(StatusEnum.ACTIVE);
		
		UserRole role = new UserRole();
		role.setRoleTypeEnum(RoleTypeEnum.ADMINESN);
		role.setUser(user);
		
		em.persist(user);
		em.persist(role);
		
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
