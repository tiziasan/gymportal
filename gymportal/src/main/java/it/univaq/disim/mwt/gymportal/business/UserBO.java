package it.univaq.disim.mwt.gymportal.business;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.User;

@Service

public interface UserBO {
	
	void deleteUser (Long id) throws BusinessException;

	void createUser (User user) throws BusinessException;
	
	void updateUser (User user) throws BusinessException;
}
