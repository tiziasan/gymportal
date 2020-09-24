package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface FeedbackGymBO {
	
	void createFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void updateFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void deleteFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	FeedbackGym findByID(long id) throws BusinessException;

	Set<FeedbackGym> findAllFeedbackGym (long id) throws BusinessException;

	Set <FeedbackGym> findAllFeedbackByGym (long id) throws BusinessException;

	Set <FeedbackGym> findAllFeedbackByUserId (long id) throws BusinessException;

	void deleteAllByGym(Gym gym);


}
