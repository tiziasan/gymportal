package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserBO {

	User findUserByEmail(String email);

	User findUserByUsername(String username);

	User saveUser (User user) throws BusinessException;

	User updateUser (User user) throws BusinessException;

	User updateGestore(User user) throws BusinessException;
}
