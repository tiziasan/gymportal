package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.FavoriteGymBO;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.repository.FavoriteGymRepository;

@Service
@Transactional(transactionManager = "transactionManager")
public class FavoriteGymBOImpl implements FavoriteGymBO{
	
	@Autowired
	private FavoriteGymRepository favoriteGymRepository;

	@Override
	public void createFavoriteGym(FavoriteGym favoriteGym)  {
		favoriteGymRepository.save(favoriteGym);
		
	}

	@Override
	public void updateFavoriteGym(FavoriteGym favoriteGym)  {
		favoriteGymRepository.save(favoriteGym);
		
	}

	@Override
	public void deleteFavoriteGym(FavoriteGym favoriteGym)  {
		favoriteGymRepository.delete(favoriteGym);
		
	}

	@Override
	public List<FavoriteGym> findAllFavoritegym(long id)  {
		return favoriteGymRepository.findAllFavoriteGym(id);
	}
	
	@Override
	public List<FavoriteGym> findAllFavoriteByUserId(long id)  {
		return favoriteGymRepository.findAllFavoriteByUserId(id);

	}

	@Override
	public void deleteAllByGym(Gym gym) {
		favoriteGymRepository.deleteAllByGym(gym);
	}

}
