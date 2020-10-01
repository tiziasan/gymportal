package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FeedbackCourseImplCustom {

    @Query(value = "FROM FeedbackCourse as f WHERE f.id = :id")
    Set<FeedbackCourse> findAllFeedbackCourse(long id);

    @Query(value = "FROM FeedbackCourse AS f INNER JOIN User AS u ON f.course.id= :id AND f.user.id = u.id")
    Set<FeedbackCourse> findAllFeedbackByCourse(long id);

    @Query(value = "FROM FeedbackCourse as f WHERE f.user.id = :id")
    Set<FeedbackCourse> findAllFeedbackByUserId(long id);

    @Query(value = "FROM FeedbackCourse AS c WHERE c.id = :id")
    FeedbackCourse findByID(long id);


}
