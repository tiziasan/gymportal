package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackCourseRepository extends CrudRepository<FeedbackCourse, Long>, FeedbackCourseImplCustom{

    void deleteAllByCourse(Course course);
}
