package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Course;

public class CourseBOImplCustom {
	public interface GymBOImplCustom {
		@Query(value = "FROM course INNER JOIN gym ON course.gym_id=gym.id and gym.id=:gym_id")
		public List<Course> findCourseByGymId(long gym_id);
		
	}

}


