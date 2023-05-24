package fr.isika.cda.entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.isika.cda.entities.common.RoleTypeEnum;

@Entity
@Table(name = "user_role_details")
public class UserRoleDetails {

	@Id
	@GeneratedValue
	private Long userId;

	@Enumerated(EnumType.STRING)
	private RoleTypeEnum roleTypeEnum;

	public RoleTypeEnum getRoleTypeEnum() {
		return roleTypeEnum;
	}

	public void setRoleTypeEnum(RoleTypeEnum roleTypeEnum) {
		this.roleTypeEnum = roleTypeEnum;
	}

	public Long getUserId() {
		return userId;
	}

}
