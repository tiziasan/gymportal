package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;

	public interface CourseImplCustom {
		@Query(value = "FROM Course AS c INNER JOIN Gym AS g ON c.gym.id=g.id and g.id=:gym_id")
		public List<Course> findCourseByGymId(long gym_id);
		
		@Query(value = "FROM Course AS c WHERE c.id = :id")
		public Course findByID(Long id);
		
		@Transactional
		@Modifying
		@Query(value = "DELETE FROM Course AS c WHERE c.gym.id = :id")
		public void deleteAllCourseByGymId(Long id);
		
		@Query(value = "FROM Course AS c WHERE c.gym.id = :id AND c.name LIKE CONCAT('%',:name,'%')")
		public List<Course> searchByIdAndName(Long id, String name);
		
		@Query(value = "FROM Course AS c WHERE c.gym.id = :id AND c.gym.user.id = :idUser AND c.name LIKE CONCAT('%',:name,'%')")
		public List<Course> searchByIdAndNameAndUser(Long id, String name, Long idUser);
		
	}




