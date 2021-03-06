package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;

public interface UserService {

    User findUserByEmail(String email);

    <U extends User> U findUserByUsername(String username);

    <U extends User> U findUserByUsernameAndRole(String username, Role role);

    User saveUser(User user) throws BusinessException;

    User updateUser(User user, Role role) throws BusinessException;

}
