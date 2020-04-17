package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackGymBO {
	
	void createFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void updateFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	void deleteFeedbackGym (FeedbackGym feedbackGym) throws BusinessException;
	
	FeedbackGym findByID(Long id) throws BusinessException;
	
	List <FeedbackGym> findAllFeedbackGym (Long id) throws BusinessException;
	
	List <FeedbackGym> findAllFeedbackByGym (Long id) throws BusinessException;

	List <FeedbackGym> findAllFeedbackByUserId (Long id) throws BusinessException;

}
