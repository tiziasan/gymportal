package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.FeedbackCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(transactionManager = "standardtrans")
public class FeedbackCourseServiceImpl implements FeedbackCourseService {

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
    @Transactional(readOnly = true)
    public Set<FeedbackCourse> findAllFeedbackCourse(FeedbackCourse feedbackCourse) throws BusinessException {
        return feedbackCourseRepository.findAllFeedbackCourse(feedbackCourse.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<FeedbackCourse> findAllFeedbackByCourse(long id) throws BusinessException {
        return feedbackCourseRepository.findAllFeedbackByCourse(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<FeedbackCourse> findAllFeedbackByUserId(User user) throws BusinessException {
        return feedbackCourseRepository.findAllFeedbackByUserId(user.getId());
    }

    @Override
    public void deleteAllByCourse(Course course) {
        feedbackCourseRepository.deleteAllByCourse(course);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackCourse findByID(long id) throws BusinessException {
        return feedbackCourseRepository.findByID(id);
    }

}
