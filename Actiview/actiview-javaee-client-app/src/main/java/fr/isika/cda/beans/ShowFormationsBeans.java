package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.repository.FormationRepository;

@ManagedBean
public class ShowFormationsBeans {
	
	
	@Inject
	private FormationRepository formationRepo;
	
	private List<Formation> formations;
	
	@PostConstruct
	public void initMissionList() {
		formations = formationRepo.findAllFormations();
	}

	public FormationRepository getMissionRepo() {
		return formationRepo;
	}

	public void setMissionRepo(FormationRepository formationRepo) {
		this.formationRepo = formationRepo;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setMissions(List<Formation> formations) {
		this.formations = formations;
	}
	
	
}
