
package it.univaq.disim.mwt.gymportal.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.UserBO;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.UserRepository;

@Service

@Transactional

public class UserBOImpl implements UserBO {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void deleteUser(Long id) throws BusinessException {
		userRepository.deleteById(id);

	}

	@Override
	public void createUser(User user) throws BusinessException {
		userRepository.save(user);

	}

	@Override
	public void updateUser(User user) throws BusinessException {
		userRepository.save(user);

	}

}
