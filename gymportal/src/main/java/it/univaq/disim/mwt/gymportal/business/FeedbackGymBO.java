package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackGymBO {
	
	void createFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void updateFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void deleteFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	FeedbackGym findByID(long id) throws BusinessException;
	
	List <FeedbackGym> findAllFeedbackGym (long id) throws BusinessException;
	
	List <FeedbackGym> findAllFeedbackByGym (long id) throws BusinessException;

	List <FeedbackGym> findAllFeedbackByUserId (long id) throws BusinessException;

}
