package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

public interface FeedbackCourseImplCustom {
	
	@Query(value="FROM FeedbackCourse as f WHERE f.id = :id")
	public Set<FeedbackCourse> findAllFeedbackCourse(long id);
	
	@Query(value="FROM FeedbackCourse AS f INNER JOIN User AS u ON f.course.id= :id AND f.user.id = u.id")
	public Set<FeedbackCourse> findAllFeedbackByCourse(long id);
	
	@Query(value="FROM FeedbackCourse as f WHERE f.user.id = :id")
	public Set<FeedbackCourse> findAllFeedbackByUserId(long id);
	
	@Query(value = "FROM FeedbackCourse AS c WHERE c.id = :id")
	public FeedbackCourse findByID(long id);
	
	

}
