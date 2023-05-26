package fr.isika.cda.dataset;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.viewmodels.UserViewModel;

@Singleton
@Startup
public class DataInit {
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		
		UserViewModel userVM = new UserViewModel();
		
	}

}
