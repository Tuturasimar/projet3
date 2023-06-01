package fr.isika.cda.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Absence;
import fr.isika.cda.entities.activities.CustomActivity;
import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.activities.Mission;
import fr.isika.cda.repository.ActivityRepository;
import fr.isika.cda.repository.FormationUserRepository;
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
	
	@Inject
	private MissionUserRepository missionUserRepo;
	
	@Inject
	private FormationUserRepository formationUserRepo;
	
	@Inject ActivityRepository activityRepository;

	private List<Mission> missions;
	
	private List<Formation> formations;
	
	private List<Absence> absences;
	
	private List<CustomActivity> customActivities;
	
	public List<Mission> getMissions() {
		return missions;
	}
	
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	
	public List<Formation> getFormations() {
		return formations;
	}
	
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	public List<Absence> getAbsences() {
		return absences;
	}
	
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	
	public List<CustomActivity> getCustomActivities() {
		return customActivities;
	}
	
	public void setCustomActivities(List<CustomActivity> customActivities) {
		this.customActivities = customActivities;
	}
	
	@PostConstruct
	/**
	 * Méthode pour obtenir l'ensemble des activités affectées à l'utilisateur connecté
	 */
	public void getAllActivities() {
		Long userConnectedId = SessionUtils.getUserIdFromSession();
		// Chercher toutes les missions affectées au User connecté
		missions = missionUserRepo.findAllAffectedMissionsByUserId(userConnectedId);
		
		// Chercher toutes les formations affectées au User connecté
		formations = formationUserRepo.finAllAffectedFormationsByUserId(userConnectedId);
		
		absences = activityRepository.getAllAbsences();
		
		customActivities = activityRepository.getAllCustomActivities();
		
	}

}
