package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

import java.util.Set;

public interface FeedbackGymService {
	
	void createFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void updateFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void deleteFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	FeedbackGym findByID(long id) throws BusinessException;

	Set<FeedbackGym> findAllFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;

	Set <FeedbackGym> findAllFeedbackByGym (Gym gym) throws BusinessException;

	Set <FeedbackGym> findAllFeedbackByUserId (User user) throws BusinessException;

	void deleteAllByGym(Gym gym);


}
