package fr.isika.cda.dataset;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.users.User;

@Singleton
@Startup
public class DataInit {

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		
		// Mock donnée User
		User user = new User();
		user.setCreatedAt(LocalDate.now());
		
		// Mock donnée d'un CRA
				Ar ar = new Ar();
				ar.setCreatedAt(LocalDate.now());
				ar.setArConfig(ArConfigEnum.MONTH);
				ar.setStateArEnum(StateAr.DRAFT);
				ar.setUpdatedAt(LocalDate.now());
				ar.setUser(user);
				
				// Mock donnée d'une activity
				Mission mission = new Mission();
				mission.setCreatorId(1L);
				
				em.persist(user);
				em.persist(ar);
				em.persist(mission);
	}
}
