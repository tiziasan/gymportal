package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.FavoriteCourseRepository;
import it.univaq.disim.mwt.gymportal.repository.FavoriteGymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteGymRepository favoriteGymRepository;
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
    public Set<FavoriteCourse> findAllFavoriteCourse(FavoriteCourse favoriteCourse) throws BusinessException {
        return favoriteCourseRepository.findAllFavoriteCourse(favoriteCourse.getId());
    }

    @Override
    public Set<FavoriteCourse> findAllFavoriteByUserId(long id) throws BusinessException {
        return favoriteCourseRepository.findAllFavoriteByUserId(id);
    }

    @Override
    public void deleteAllByCourse(Course course) {
        favoriteCourseRepository.deleteAllByCourse(course);
    }

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
        favoriteGymRepository.deleteById(favoriteGym.getId());

    }

    @Override
    public Set<FavoriteGym> findAllFavoritegym(FavoriteGym favoriteGym) throws BusinessException {
        return favoriteGymRepository.findAllFavoriteGym(favoriteGym.getId());
    }

    @Override
    public Set<FavoriteGym> findAllFavoriteByUserId(FavoriteGym favoriteGym) throws BusinessException {
        return favoriteGymRepository.findAllFavoriteByUserId(favoriteGym.getId());

    }

    @Override
    public void deleteAllByGym(Gym gym) {
        favoriteGymRepository.deleteAllByGym(gym);
    }
}
