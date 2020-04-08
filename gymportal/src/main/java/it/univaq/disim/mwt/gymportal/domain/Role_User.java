package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="role_user")
@IdClass(RoleUserID.class)
public class Role_User {
	
	@Id
	@OneToOne
	private User user;

	@Id
	@OneToOne
	private Role role;
}
