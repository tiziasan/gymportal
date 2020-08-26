package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;

public interface FavoriteGymImplCustom {

	@Query(value="FROM FavoriteGym as f WHERE f.id = :id")
	public List<FavoriteGym> findAllFavoriteGym(long id);
	
	@Query(value="FROM FavoriteGym as f WHERE f.user.id = :id")
	public List<FavoriteGym> findAllFavoriteByUserId(long id);
	
	
	
}
