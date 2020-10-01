package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FeedbackGymImplCustom {

    @Query(value = "FROM FeedbackGym as f WHERE f.id = :id")
    Set<FeedbackGym> findAllFeedbackGym(long id);

    @Query(value = "FROM FeedbackGym AS f INNER JOIN User AS u ON f.gym.id= :id AND f.user.id = u.id")
    Set<FeedbackGym> findAllFeedbackByGym(long id);

    @Query(value = "FROM FeedbackGym as f WHERE f.user.id = :id")
    Set<FeedbackGym> findAllFeedbackByUserId(long id);

    @Query(value = "FROM FeedbackGym AS c WHERE c.id = :id")
    FeedbackGym findByID(long id);

}



