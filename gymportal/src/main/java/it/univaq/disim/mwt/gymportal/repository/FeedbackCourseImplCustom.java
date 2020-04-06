package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackCourseImplCustom {
	
	@Query(value="FROM FeedbackCourse as f WHERE f.id = :id")
	public List<FeedbackCourse> findAllFeedbackCourse(Long id);
	
	@Query(value="FROM FeedbackCourse AS f INNER JOIN User AS u ON f.course.id= :id AND f.user.id = u.id")
	public List<FeedbackCourse> findAllFeedbackByCourse(Long id);

}
