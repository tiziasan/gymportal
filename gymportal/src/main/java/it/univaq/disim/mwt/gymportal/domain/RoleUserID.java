package it.univaq.disim.mwt.gymportal.domain;

import java.io.Serializable;

public class RoleUserID implements Serializable{
	
	private User user;
	
	private Role role;
	
	public RoleUserID(User user, Role role) {
		this.user = user;
		this.role = role;
	}
	

}
