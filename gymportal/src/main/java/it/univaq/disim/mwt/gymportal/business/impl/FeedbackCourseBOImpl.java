package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.repository.FeedbackCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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
		feedbackCourseRepository.deleteById(feedbackCourse.getId());
		
	}

	@Override
	public Set<FeedbackCourse> findAllFeedbackCourse(long id) throws BusinessException {
		return feedbackCourseRepository.findAllFeedbackCourse(id);
	}

	@Override
	public Set<FeedbackCourse> findAllFeedbackByCourse(long id) throws BusinessException {
		return feedbackCourseRepository.findAllFeedbackByCourse(id);

	}
	
	@Override
	public Set<FeedbackCourse> findAllFeedbackByUserId(long id) throws BusinessException {
		return feedbackCourseRepository.findAllFeedbackByUserId(id);

	}

	@Override
	public void deleteAllByCourse(Course course) {
		feedbackCourseRepository.deleteAllByCourse(course);
	}

	@Override
	public FeedbackCourse findByID(long id) throws BusinessException {
		return feedbackCourseRepository.findByID(id);
	}

}
