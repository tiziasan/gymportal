package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

import java.util.Set;

public interface FeedbackCourseBO {
	
	void createFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void updateFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void deleteFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	FeedbackCourse findByID(long id) throws BusinessException;

	Set<FeedbackCourse> findAllFeedbackCourse (long id) throws BusinessException;

	Set <FeedbackCourse> findAllFeedbackByCourse (long id) throws BusinessException;

	Set <FeedbackCourse> findAllFeedbackByUserId (long id) throws BusinessException;

	void deleteAllByCourse(Course course);

}
