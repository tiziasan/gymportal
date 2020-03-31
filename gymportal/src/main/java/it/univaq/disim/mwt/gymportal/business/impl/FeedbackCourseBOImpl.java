package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseBO;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.repository.FeedbackCourseRepository;

@Service
@Transactional
public class FeedbackCourseBOImpl implements FeedbackCourseBO{
	
	@Autowired
	private FeedbackCourseRepository feedbackCourseRepository;

	@Override
	public void createFeedbackCourse(FeedbackCourse feedbackCourse) throws BusinessException {
		feedbackCourseRepository.save(feedbackCourse);
		
	}

	@Override
	public void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws BusinessException {
		feedbackCourseRepository.save(feedbackCourse);
		
	}

	@Override
	public void deleteFeedbackCourse(FeedbackCourse feedbackCourse) throws BusinessException {
		feedbackCourseRepository.delete(feedbackCourse);
		
	}

	@Override
	public List<FeedbackCourse> findAllFeedbackCourse(Long id) throws BusinessException {
		return feedbackCourseRepository.findAllFeedbackCourse(id);
	}

}
