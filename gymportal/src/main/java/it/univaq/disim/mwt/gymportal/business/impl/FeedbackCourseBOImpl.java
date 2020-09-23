package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.FeedbackCourseBO;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.repository.FeedbackCourseRepository;

@Service
@Transactional(transactionManager = "transactionManager")
public class FeedbackCourseBOImpl implements FeedbackCourseBO{
	
	@Autowired
	private FeedbackCourseRepository feedbackCourseRepository;

	@Override
	public void createFeedbackCourse(FeedbackCourse feedbackCourse)  {
		feedbackCourseRepository.save(feedbackCourse);
		
	}

	@Override
	public void updateFeedbackCourse(FeedbackCourse feedbackCourse)  {
		feedbackCourseRepository.save(feedbackCourse);
		
	}

	@Override
	public void deleteFeedbackCourse(FeedbackCourse feedbackCourse)  {
		feedbackCourseRepository.delete(feedbackCourse);
		
	}

	@Override
	public List<FeedbackCourse> findAllFeedbackCourse(long id)  {
		return feedbackCourseRepository.findAllFeedbackCourse(id);
	}

	@Override
	public List<FeedbackCourse> findAllFeedbackByCourse(long id)  {
		return feedbackCourseRepository.findAllFeedbackByCourse(id);

	}
	
	@Override
	public List<FeedbackCourse> findAllFeedbackByUserId(long id)  {
		return feedbackCourseRepository.findAllFeedbackByUserId(id);

	}

	@Override
	public FeedbackCourse findByID(long id)  {
		return feedbackCourseRepository.findByID(id);
	}

}
