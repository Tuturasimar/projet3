package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.FormationUser;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.repository.FormationUserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class ShowFormationUserBean {

	@Inject
	private FormationUserRepository formationUserRepo;

	private List<FormationUser> formationsUser;

	private final StatusEnum ACTIVE = StatusEnum.ACTIVE;
	private final StatusEnum INACTIVE = StatusEnum.INACTIVE;

	@PostConstruct
	public void init() {
		formationsUser = formationUserRepo.getAllFormationUserByManagerLogin(SessionUtils.getUserLoginFromSession());
	}

	public List<FormationUser> getFormationsUser() {
		return formationsUser;
	}

	public void setFormationsUser(List<FormationUser> formationsUser) {
		this.formationsUser = formationsUser;
	}
	
	
	
	public StatusEnum getACTIVE() {
		return ACTIVE;
	}

	public StatusEnum getINACTIVE() {
		return INACTIVE;
	}

	public String changeAffectation(Long id) {
		FormationUser formationUser = formationUserRepo.findFormationUserById(id);
		
		if(formationUser != null) {
			if(formationUser.getFormationState() == ACTIVE) {
				formationUser.setFormationState(INACTIVE);
			} else {
				formationUser.setFormationState(ACTIVE);
			}
			formationUserRepo.updateFormationUser(formationUser);
		}
		init();
		
		return "formationUserList";
	}
}
