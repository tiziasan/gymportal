package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserBO {

	User findUserByEmail(String email);

	User findUserByUsername(String username);

	User saveUser (User user) throws BusinessException;

	User updateCustomer (User user) throws BusinessException;

	User updateManager(User user) throws BusinessException;
}
