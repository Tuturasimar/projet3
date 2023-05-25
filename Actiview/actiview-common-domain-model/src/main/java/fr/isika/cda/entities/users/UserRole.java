package fr.isika.cda.entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import fr.isika.cda.entities.common.RoleTypeEnum;

@Entity

public class UserRole {

	@Id
	@GeneratedValue
	private Long Id;

	@Enumerated(EnumType.STRING)
	private RoleTypeEnum roleTypeEnum;

	@ManyToOne
	private User user;

	public RoleTypeEnum getRoleTypeEnum() {
		return roleTypeEnum;
	}

	public void setRoleTypeEnum(RoleTypeEnum roleTypeEnum) {
		this.roleTypeEnum = roleTypeEnum;
	}

	public Long getId() {
		return Id;
	}

}