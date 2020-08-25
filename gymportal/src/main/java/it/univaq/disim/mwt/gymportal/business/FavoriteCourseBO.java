package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;

public interface FavoriteCourseBO {
	
	void createFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void updateFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void deleteFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	List <FavoriteCourse> findAllFavoriteCourse (long id) throws BusinessException;
	
	List <FavoriteCourse> findAllFavoriteByUserId (long id) throws BusinessException;

}
