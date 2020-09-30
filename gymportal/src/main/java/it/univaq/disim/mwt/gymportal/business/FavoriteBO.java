package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

import java.util.Set;

public interface FavoriteBO {
    void createFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

    void updateFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

    void deleteFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

    Set<FavoriteCourse> findAllFavoriteCourse (FavoriteCourse favoriteCourse) throws BusinessException;

    Set <FavoriteCourse> findAllFavoriteByUserId (long id) throws BusinessException;

    void deleteAllByCourse(Course course);

    void createFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;

    void updateFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;

    void deleteFavoriteGym (FavoriteGym favoriteGym) throws BusinessException;

    Set<FavoriteGym> findAllFavoritegym (FavoriteGym favoriteGym) throws BusinessException;

    Set <FavoriteGym> findAllFavoriteByUserId (FavoriteGym favoriteGym) throws BusinessException;

    void deleteAllByGym(Gym gym);
}
