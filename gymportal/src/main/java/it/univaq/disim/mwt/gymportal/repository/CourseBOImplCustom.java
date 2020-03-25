package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;

	public interface CourseBOImplCustom {
		@Query(value = "FROM Course AS c INNER JOIN Gym AS g ON c.gym.id=g.id and g.id=:gym_id")
		public List<Course> findCourseByGymId(long gym_id);
		
		@Query(value = "FROM Course AS c WHERE c.id = :id")
		public Course findByID(Long id);
		
	}




