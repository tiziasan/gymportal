package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;

@Service
public interface FavoriteCourseBO {
	
	void createFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void updateFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	void deleteFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;
	
	List <FavoriteCourse> findAllFavoriteCourse (Long id) throws BusinessException;
}
