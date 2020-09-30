package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

import java.util.Set;

public interface FavoriteGymBO {
	
	void createFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void updateFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void deleteFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;

	Set<FavoriteGym> findAllFavoritegym (FavoriteGym favoriteGym) throws BusinessException;

	Set <FavoriteGym> findAllFavoriteByUserId (FavoriteGym favoriteGym) throws BusinessException;

	void deleteAllByGym(Gym gym);


}
