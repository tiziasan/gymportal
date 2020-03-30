package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface GymImplCustom {
	@Query(value = "FROM Gym AS g WHERE g.region = :region")
	public List<Gym> findByRegionName(String region);
	
	@Query(value = "FROM Gym AS g WHERE g.id = :id")
	public Gym findByID(Long id);
	
	@Modifying
	@Query(value = "INSERT INTO FavoriteGym (gym_id, user_id) VALUES (:gym_id, :user_id)", nativeQuery = true)
	public void addFavoriteGym(@Param("gym_id") Long gym_id, @Param("user_id") Long user_id);
	
	@Modifying
	@Query(value = "INSERT INTO FeedbackGym (feed, rating, gym_id, user_id) VALUES (:feed, :rating, :gym_id, :user_id)", nativeQuery = true)
	public void addFeedbackGym(@Param("feed") String feed, @Param("rating") int rating, @Param("gym_id") Long gym_id, @Param("user_id") Long user_id);
	
}