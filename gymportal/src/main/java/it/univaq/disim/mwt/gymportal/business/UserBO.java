package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserBO {
	
	void deleteUser (Long id) throws BusinessException;

	void createUser (User user) throws BusinessException;
	
	void updateUser (User user) throws BusinessException;
}
