package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;

import java.util.Set;

public interface FavoriteCourseBO {
	
	void createFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void updateFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void deleteFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

	Set<FavoriteCourse> findAllFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

	Set <FavoriteCourse> findAllFavoriteByUserId (long id) throws BusinessException;

	void deleteAllByCourse(Course course);


}
