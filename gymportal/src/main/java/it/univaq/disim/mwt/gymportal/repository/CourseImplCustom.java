package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.domain.Course;

	public interface CourseImplCustom {
		@Query(value = "FROM Course AS c INNER JOIN Gym AS g ON c.gym.id=g.id and g.id=:gym_id")
		public List<Course> findCourseByGymId(long gym_id);
		
		@Query(value = "FROM Course AS c WHERE c.id = :id")
		public Course findByID(Long id);
		
		@Transactional
		@Modifying
		@Query(value = "delete FROM Course AS c WHERE c.gym.id = :id")
		public void deleteAllCourseByGymId(Long id);
		
		@Modifying
		@Query(value = "INSERT INTO FavoriteCourse (course_id, user_id) VALUES (:course_id, :user_id)", nativeQuery = true)
		public void addFavoriteCourse(@Param("course_id") Long course_id, @Param("user_id") Long user_id);
		
		@Modifying
		@Query(value = "INSERT INTO FeedbackCourse (feed, rating, course_id, user_id) VALUES (:feed, :rating, :course_id, :user_id)", nativeQuery = true)
		public void addFeedbackCourse(@Param("feed") String feed, @Param("rating") int rating, @Param("course_id") Long course_id, @Param("user_id") Long user_id);
	}




