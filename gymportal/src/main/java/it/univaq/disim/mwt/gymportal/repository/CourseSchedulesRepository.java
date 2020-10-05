package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.CourseSchedules;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseSchedulesRepository extends CrudRepository<CourseSchedules, Long>, CourseSchedulesImplCustom {

    Set<CourseSchedules> findAllByCourse(Course course);
}
