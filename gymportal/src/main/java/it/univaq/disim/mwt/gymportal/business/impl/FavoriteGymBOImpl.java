package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;
import java.util.Set;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteGymBO;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.repository.FavoriteGymRepository;

@Service
@Transactional
public class FavoriteGymBOImpl implements FavoriteGymBO{
	
	@Autowired
	private FavoriteGymRepository favoriteGymRepository;

	@Override
	public void createFavoriteGym(FavoriteGym favoriteGym) throws BusinessException {
		favoriteGymRepository.save(favoriteGym);
		
	}

	@Override
	public void updateFavoriteGym(FavoriteGym favoriteGym) throws BusinessException {
		favoriteGymRepository.save(favoriteGym);
		
	}

	@Override
	public void deleteFavoriteGym(FavoriteGym favoriteGym) throws BusinessException {
		favoriteGymRepository.delete(favoriteGym);
		
	}

	@Override
	public Set<FavoriteGym> findAllFavoritegym(long id) throws BusinessException {
		return favoriteGymRepository.findAllFavoriteGym(id);
	}
	
	@Override
	public Set<FavoriteGym> findAllFavoriteByUserId(long id) throws BusinessException {
		return favoriteGymRepository.findAllFavoriteByUserId(id);

	}

	@Override
	public void deleteAllByGym(Gym gym) {
		favoriteGymRepository.deleteAllByGym(gym);
	}

}
