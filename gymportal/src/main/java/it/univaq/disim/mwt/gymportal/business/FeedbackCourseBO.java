package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

@Service
public interface FeedbackCourseBO {
	
	void createFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void updateFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	void deleteFeedbackCourse (FeedbackCourse feedbackCourse) throws BusinessException;
	
	List <FeedbackCourse> findAllFeedbackCourse (Long id) throws BusinessException;

}
