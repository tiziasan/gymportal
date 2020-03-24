package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{
	public List<Course> findCourseByGymId(long gym_id);


}
