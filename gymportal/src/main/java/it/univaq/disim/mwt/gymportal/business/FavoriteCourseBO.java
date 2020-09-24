package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface FavoriteCourseBO {
	
	void createFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void updateFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void deleteFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

	Set<FavoriteCourse> findAllFavoriteCourse (long id) throws BusinessException;

	Set <FavoriteCourse> findAllFavoriteByUserId (long id) throws BusinessException;

	void deleteAllByCourse(Course course);


}
