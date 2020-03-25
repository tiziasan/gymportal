package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Course;

	public interface CourseBOImplCustom {
		@Query(value = "FROM Course AS c INNER JOIN Gym AS g ON c.gym.id=g.id and g.id=:gym_id")
		public List<Course> findCourseByGymId(long gym_id);
		
	}




