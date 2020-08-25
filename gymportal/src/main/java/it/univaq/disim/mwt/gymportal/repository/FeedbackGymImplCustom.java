package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackGymImplCustom {
	
	@Query(value="FROM FeedbackGym as f WHERE f.id = :id")
	public List<FeedbackGym> findAllFeedbackGym(long id);
	
	@Query(value="FROM FeedbackGym AS f INNER JOIN User AS u ON f.gym.id= :id AND f.user.id = u.id")
	public List<FeedbackGym> findAllFeedbackByGym(long id);
	
	@Query(value="FROM FeedbackGym as f WHERE f.user.id = :id")
	public List<FeedbackGym> findAllFeedbackByUserId(long id);
	
	@Query(value = "FROM FeedbackGym AS c WHERE c.id = :id")
	public FeedbackGym findByID(long id);
	
}



