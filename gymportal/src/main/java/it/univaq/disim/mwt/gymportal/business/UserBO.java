package it.univaq.disim.mwt.gymportal.business;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.User;

@Service

public interface UserBO {
	
	void deleteUser (Long id) throws BusinessException;

	void createUser (User user) throws BusinessException;
	
	void updateUser (User user) throws BusinessException;
	
	void removeFavoriteCourse (Long id) throws BusinessException;
	
	void removeFavoriteGym (Long id) throws BusinessException;
	
	void updateFeedbackCourse(String feed, int rating, Long course_id, Long user_id, Long id) throws BusinessException;
	
	void updateFeedbackGym(String feed, int rating, Long gym_id, Long user_id, Long id) throws BusinessException;
	
	void removeFeedbackCourse(Long id) throws BusinessException;
	
	void removeFeedbackGym(Long id) throws BusinessException;

}
