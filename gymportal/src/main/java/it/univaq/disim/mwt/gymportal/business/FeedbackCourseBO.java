package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

public interface FeedbackCourseBO {
	
	void createFeedbackCourse (FeedbackCourse feedbackCourse) ;
	
	void updateFeedbackCourse (FeedbackCourse feedbackCourse) ;
	
	void deleteFeedbackCourse (FeedbackCourse feedbackCourse) ;
	
	FeedbackCourse findByID(long id) ;
	
	List <FeedbackCourse> findAllFeedbackCourse (long id) ;
	
	List <FeedbackCourse> findAllFeedbackByCourse (long id) ;
	
	List <FeedbackCourse> findAllFeedbackByUserId (long id) ;



}
