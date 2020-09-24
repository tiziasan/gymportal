package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface FavoriteGymBO {
	
	void createFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void updateFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void deleteFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;

	Set<FavoriteGym> findAllFavoritegym (long id) throws BusinessException;

	Set <FavoriteGym> findAllFavoriteByUserId (long id) throws BusinessException;

	void deleteAllByGym(Gym gym);


}
