package fr.isika.cda.beans;


import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.MissionRepository;
import fr.isika.cda.viewmodels.MissionViewModel;

@ManagedBean
public class RegisterMission {
	
	private MissionViewModel missionVm = new MissionViewModel();
	
	@Inject
	private MissionRepository missionRepository;
	
	public void register() {
		Long id = missionRepository.register(missionVm);
		System.out.println("La mission a bien été créee avec l'id : " + id);
		clear();
	}
	
	public void clear() {
		missionVm = new MissionViewModel();
	}
	
	public void updateMission(Long id) {
		System.out.println("La mission à mettre à jour est : " + id);
	}

	public MissionViewModel getMissionVm() {
		return missionVm;
	}

	public void setMissionVm(MissionViewModel missionVm) {
		this.missionVm = missionVm;
	}
	
	

}
