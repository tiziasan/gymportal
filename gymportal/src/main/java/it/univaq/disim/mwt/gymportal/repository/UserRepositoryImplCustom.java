package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface UserRepositoryImplCustom {
	
@Query(value = "DELETE FROM FavoriteGym AS f WHERE f.id = :id")
public List<FavoriteGym> removeFavoriteGym(Long id);

@Query(value = "DELETE FROM FavoriteCourse AS f WHERE f.id = :id")
public List<FavoriteCourse> removeFavoriteCourse(Long id);

@Query(value = "DELETE FROM FeedbackGym as f WHERE f.id = :id")
public List<FeedbackGym> removeFeedbackGym(Long id);

@Query(value = "DELETE FROM FeedbackCourse as f WHERE f.id = :id")
public List<FeedbackCourse> removeFeedbackCourse(Long id);

@Modifying
@Query(value = "UPDATE FeedbackGym AS f SET feed = :feed, rating = :rating, gym_id = :gym_id, user_id= :user_id WHERE f.id = :id")
public void updateFeedbackGym(@Param("feed") String feed, @Param("rating") int rating, @Param("gym_id") Long gym_id, @Param("user_id") Long user_id, @Param("id") Long id);

@Modifying
@Query(value = "UPDATE FeedbackCourse as f SET feed = :feed, rating = :rating, course_id = :course_id, user_id = :user_id WHERE f.id = :id")
public void updateFeedbackCourse(@Param("feed") String feed, @Param("rating") int rating, @Param("course_id") Long course_id, @Param("user_id") Long user_id, @Param("id") Long id);

}
