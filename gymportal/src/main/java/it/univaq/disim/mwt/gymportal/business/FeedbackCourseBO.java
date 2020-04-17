package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

public interface FeedbackCourseBO {
	
	void createFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void updateFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void deleteFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	FeedbackCourse findByID(Long id) throws BusinessException;
	
	List <FeedbackCourse> findAllFeedbackCourse (Long id) throws BusinessException;
	
	List <FeedbackCourse> findAllFeedbackByCourse (Long id) throws BusinessException;
	
	List <FeedbackCourse> findAllFeedbackByUserId (Long id) throws BusinessException;



}
