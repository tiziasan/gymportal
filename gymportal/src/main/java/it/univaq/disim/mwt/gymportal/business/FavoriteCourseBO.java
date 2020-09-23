package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface FavoriteCourseBO {
	
	void createFavoriteCourse (FavoriteCourse favoriteCourse);
	
	void updateFavoriteCourse (FavoriteCourse favoriteCourse);
	
	void deleteFavoriteCourse (FavoriteCourse favoriteCourse);
	
	List <FavoriteCourse> findAllFavoriteCourse (long id);
	
	List <FavoriteCourse> findAllFavoriteByUserId (long id);

	void deleteAllByCourse(Course course);


}
