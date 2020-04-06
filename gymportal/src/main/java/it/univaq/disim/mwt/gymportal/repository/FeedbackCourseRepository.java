package it.univaq.disim.mwt.gymportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

@Repository
public interface FeedbackCourseRepository extends CrudRepository<FeedbackCourse, Long>, FeedbackCourseImplCustom{

}