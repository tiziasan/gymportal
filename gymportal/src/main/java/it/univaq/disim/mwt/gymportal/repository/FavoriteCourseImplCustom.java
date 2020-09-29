package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FavoriteCourseImplCustom {
	
	@Query(value="FROM FavoriteCourse as f WHERE f.id = :id")
	public Set<FavoriteCourse> findAllFavoriteCourse(long id);
	
	@Query(value="FROM FavoriteCourse as f WHERE f.user.id = :id")
	public Set<FavoriteCourse> findAllFavoriteByUserId(long id);

}
