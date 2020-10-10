package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.CourseSchedules;

import java.util.Set;

public interface CourseSchedulesService {

    void deleteCourseSchedules(CourseSchedules courseSchedules);
    CourseSchedules addCourseSchedules(CourseSchedules courseSchedules);
    void updateCourseschedules(CourseSchedules courseSchedules);
    Set<CourseSchedules> findAllCourseSchedulesByCourse(Course course);
    CourseSchedules findCourseSchedulesById(long id);
}
