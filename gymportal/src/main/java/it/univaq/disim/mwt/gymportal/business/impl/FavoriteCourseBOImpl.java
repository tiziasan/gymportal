package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteCourseBO;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.repository.FavoriteCourseRepository;

@Service
@Transactional
public class FavoriteCourseBOImpl implements FavoriteCourseBO{
	
	@Autowired
	private FavoriteCourseRepository favoriteCourseRepository;

	@Override
	public void createFavoriteCourse(FavoriteCourse favoriteCourse) throws BusinessException {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public void updateFavoriteCourse(FavoriteCourse favoriteCourse) throws BusinessException {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public void deleteFavoriteCourse(FavoriteCourse favoriteCourse) throws BusinessException {
		favoriteCourseRepository.save(favoriteCourse);
		
	}

	@Override
	public List<FavoriteCourse> findAllFavoriteCourse(Long id) throws BusinessException {
		return favoriteCourseRepository.findAllFavoriteCourse(id);
	}

}