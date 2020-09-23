package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserBO {

	User findUserByEmail(String email);

	User findUserByUserName(String userName);

	User saveUser (User user);

	User updateUser (User user);

	User updateGestore(User user);
}
