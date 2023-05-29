package fr.isika.cda.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Activity;
import fr.isika.cda.repository.ActivityRepository;

@ManagedBean
@ViewScoped
/**
 * Bean pour récupérer et afficher l'ensemble des activités
 * @author Trévor
 *
 */
public class ShowActivityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Activity> activities = new ArrayList<Activity>();
	
	@Inject
	private ActivityRepository activityRepo;
	
	@PostConstruct
	/**
	 * Méthode pour obtenir l'ensemble des activités sans contrainte (test)
	 * A changer quand les attributions seront opérationnelles
	 */
	public void getAllActivities() {
		activities = activityRepo.getAllActivities();
	}

	public List<Activity> getActivities() {
		return activities;
	}

}
