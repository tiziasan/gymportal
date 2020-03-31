package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>, CourseImplCustom{
	List<Course> findCourseByGymId(long gym_id);

	Course findByID(Long id);

	void deleteAllCourseByGymId(Long id);


}
