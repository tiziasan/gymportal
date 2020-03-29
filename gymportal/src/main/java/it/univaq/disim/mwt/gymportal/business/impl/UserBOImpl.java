
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

	@Override
	public void removeFavoriteCourse(Long id) throws BusinessException {
		userRepository.removeFavoriteCourse(id);
		
	}

	@Override
	public void removeFavoriteGym(Long id) throws BusinessException {
		userRepository.removeFavoriteGym(id);
		
	}

	@Override
	public void updateFeedbackCourse(String feed, int rating, Long course_id, Long user_id, Long id) throws BusinessException {
		userRepository.updateFeedbackCourse(feed, rating, course_id, user_id, id);
		
	}

	@Override
	public void updateFeedbackGym(String feed, int rating, Long gym_id, Long user_id, Long id) throws BusinessException {
		userRepository.updateFeedbackGym(feed, rating, gym_id, user_id, id);
		
	}

	@Override
	public void removeFeedbackCourse(Long id) throws BusinessException {
		userRepository.removeFeedbackCourse(id);
		
	}

	@Override
	public void removeFeedbackGym(Long id) throws BusinessException {
		userRepository.removeFeedbackGym(id);
		
	}

}
