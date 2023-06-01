package fr.isika.cda.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.repository.MissionUserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
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

	private List<Mission> missions;
	
	@Inject
	private MissionUserRepository missionUserRepo;
	
	public List<Mission> getMissions() {
		return missions;
	}
	
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	
	@PostConstruct
	/**
	 * Méthode pour obtenir l'ensemble des activités affectées à l'utilisateur connecté
	 */
	public void getAllActivities() {
		
		// Chercher toutes les missions affectées au User connecté
		missions = missionUserRepo.findAllAffectedMissionsByUserId(SessionUtils.getUserIdFromSession());
		
		// Chercher toutes les formations affectées au User connecté
		
		
	}

}
