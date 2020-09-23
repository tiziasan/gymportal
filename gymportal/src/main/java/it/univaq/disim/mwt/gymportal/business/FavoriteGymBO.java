package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FavoriteGymBO {
	
	void createFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void updateFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	void deleteFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;
	
	List <FavoriteGym> findAllFavoritegym (long id) throws BusinessException;
	
	List <FavoriteGym> findAllFavoriteByUserId (long id) throws BusinessException;

}
