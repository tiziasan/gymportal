package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

public interface FeedbackCourseImplCustom {
	
	@Query(value="FROM FeedbackCourse as F WHERE f.id = :id")
	public List<FeedbackCourse> findAllFeedbackCourse(Long id);

}
