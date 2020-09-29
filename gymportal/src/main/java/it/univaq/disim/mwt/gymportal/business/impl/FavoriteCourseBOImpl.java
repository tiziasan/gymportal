package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteCourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.repository.FavoriteCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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
		favoriteCourseRepository.deleteById(favoriteCourse.getId());
		
	}

	@Override
	public Set<FavoriteCourse> findAllFavoriteCourse(long id) throws BusinessException {
		return favoriteCourseRepository.findAllFavoriteCourse(id);
	}
	
	@Override
	public Set<FavoriteCourse> findAllFavoriteByUserId(long id) throws BusinessException {
		return favoriteCourseRepository.findAllFavoriteByUserId(id);
	}

	@Override
	public void deleteAllByCourse(Course course) {
		favoriteCourseRepository.deleteAllByCourse(course);
	}
}
