package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.FavoriteCourseBO;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.repository.FavoriteCourseRepository;

@Service
@Transactional(transactionManager = "transactionManager")
public class FavoriteCourseBOImpl implements FavoriteCourseBO{
	
	@Autowired
	private FavoriteCourseRepository favoriteCourseRepository;

	@Override
	public void createFavoriteCourse(FavoriteCourse favoriteCourse)  {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public void updateFavoriteCourse(FavoriteCourse favoriteCourse)  {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public void deleteFavoriteCourse(FavoriteCourse favoriteCourse)  {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public List<FavoriteCourse> findAllFavoriteCourse(long id)  {
		return favoriteCourseRepository.findAllFavoriteCourse(id);
	}
	
	@Override
	public List<FavoriteCourse> findAllFavoriteByUserId(long id)  {
		return favoriteCourseRepository.findAllFavoriteByUserId(id);

	}
}
