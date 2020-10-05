package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.CourseSchedulesService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.CourseSchedules;
import it.univaq.disim.mwt.gymportal.repository.CourseSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CourseSchedulesImpl implements CourseSchedulesService {

    @Autowired
    CourseSchedulesRepository courseSchedulesRepository;

    @Override
    public void deleteCourseSchedules(CourseSchedules courseSchedules) {
        courseSchedulesRepository.delete(courseSchedules);
    }

    @Override
    public CourseSchedules addCourseSchedules(CourseSchedules courseSchedules) {
        return courseSchedulesRepository.save(courseSchedules);
    }

    @Override
    public void updateCourseschedules(CourseSchedules courseSchedules) {
        courseSchedulesRepository.save(courseSchedules);
    }

    @Override
    public Set<CourseSchedules> findAllCourseSchedulesByCourse(Course course) {
        return courseSchedulesRepository.findAllByCourse(course);
    }

    @Override
    public CourseSchedules findCourseSchedulesById(long id) {
        return courseSchedulesRepository.findCourseSchedulesById(id);
    }
}
