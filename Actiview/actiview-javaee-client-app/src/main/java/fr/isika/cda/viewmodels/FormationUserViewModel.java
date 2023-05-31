package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.StatusEnum;

public class FormationUserViewModel {

	private Long userId;
	
	private Long formationId;
	
	private StatusEnum formationUserState;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFormationId() {
		return formationId;
	}

	public void setFormationId(Long formationId) {
		this.formationId = formationId;
	}

	public StatusEnum getFormationUserState() {
		return formationUserState;
	}

	public void setFormationUserState(StatusEnum formationUserState) {
		this.formationUserState = formationUserState;
	}
	
	
	
}
