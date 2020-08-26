package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;

public interface FavoriteCourseImplCustom {
	
	@Query(value="FROM FavoriteCourse as f WHERE f.id = :id")
	public List<FavoriteCourse> findAllFavoriteCourse(long id);
	
	@Query(value="FROM FavoriteCourse as f WHERE f.user.id = :id")
	public List<FavoriteCourse> findAllFavoriteByUserId(long id);

}
