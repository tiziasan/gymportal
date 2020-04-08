package it.univaq.disim.mwt.gymportal.repository;


import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserImplCustom {
	
	  @Query(value = "FROM User AS u WHERE u.username = :username") 
	  public User findByUsername(String username);
	
	  
	  @Query(value ="FROM Role_user ur, Role r WHERE ur.user.id = :id AND ur.role.id = r.id")
	  public Role findUserRole(Long id);
	 
	
	
	
}