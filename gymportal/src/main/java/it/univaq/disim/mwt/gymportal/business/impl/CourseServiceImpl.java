package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.CourseRepository;
import it.univaq.disim.mwt.gymportal.repository.CourseSchedulesRepository;
import it.univaq.disim.mwt.gymportal.repository.FavoriteCourseRepository;
import it.univaq.disim.mwt.gymportal.repository.FeedbackCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSchedulesRepository schedulesRepository;

    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    private FeedbackCourseRepository feedbackCourseRepository;

    @Override
    public void deleteCourse(Course course) throws BusinessException {
        schedulesRepository.deleteAll(course.getCourseSchedules());
        favoriteCourseRepository.deleteAllByCourse(course);
        feedbackCourseRepository.deleteAllByCourse(course);
        courseRepository.delete(course);
    }

    @Override
    public Course createCourse(Course course) throws BusinessException {
        return courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) throws BusinessException {
        courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Course> findAllCourse() throws BusinessException {
        return (Set<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Course> findCourseByGymId(Gym gym) throws BusinessException {
        return courseRepository.findCourseByGymId(gym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Course findByID(long id) throws BusinessException {
        return courseRepository.findByID(id);
    }

    @Override
    public void deleteAllCourseByGymId(Gym gym) throws BusinessException {
        courseRepository.deleteAllCourseByGymId(gym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Course> searchByIdAndName(long id, String name) throws BusinessException {
        return courseRepository.searchByIdAndName(id, name);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Course> searchByIdAndNameAndUser(long id, String name, long idUtente) throws BusinessException {
        return courseRepository.searchByIdAndNameAndUser(id, name, idUtente);
    }


}
