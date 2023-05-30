package fr.isika.cda.entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.isika.cda.entities.common.RoleTypeEnum;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private RoleTypeEnum roleTypeEnum;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	private User user;
	
	public RoleTypeEnum getRoleTypeEnum() {
		return roleTypeEnum;
	}

	public void setRoleTypeEnum(RoleTypeEnum roleTypeEnum) {
		this.roleTypeEnum = roleTypeEnum;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}