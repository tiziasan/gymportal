package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface FavoriteGymBO {
	
	void createFavoriteGym (FavoriteGym favoriteGym);
	
	void updateFavoriteGym (FavoriteGym favoriteGym);
	
	void deleteFavoriteGym (FavoriteGym favoriteGym);
	
	List <FavoriteGym> findAllFavoritegym (long id);
	
	List <FavoriteGym> findAllFavoriteByUserId (long id);

	void deleteAllByGym(Gym gym);


}
