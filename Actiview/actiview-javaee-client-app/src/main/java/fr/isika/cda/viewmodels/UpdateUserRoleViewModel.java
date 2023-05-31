package fr.isika.cda.viewmodels;

import java.util.ArrayList;
import java.util.List;

import fr.isika.cda.entities.common.RoleTypeEnum;

public class UpdateUserRoleViewModel {

	private Long userId;
	private List<RoleTypeEnum> roleTypes = new ArrayList<RoleTypeEnum>();

	public UpdateUserRoleViewModel(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<RoleTypeEnum> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<RoleTypeEnum> roleTypes) {
		this.roleTypes = roleTypes;
	}

}
