package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackGymBO {
	
	void createFeedbackGym (FeedbackGym feedbackGym) ;
	
	void updateFeedbackGym (FeedbackGym feedbackGym) ;
	
	void deleteFeedbackGym (FeedbackGym feedbackGym) ;
	
	FeedbackGym findByID(long id) ;
	
	List <FeedbackGym> findAllFeedbackGym (long id) ;
	
	List <FeedbackGym> findAllFeedbackByGym (long id) ;

	List <FeedbackGym> findAllFeedbackByUserId (long id) ;

}
