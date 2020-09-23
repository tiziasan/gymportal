package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;


@Repository
public interface FavoriteCourseRepository extends CrudRepository<FavoriteCourse, Long>, FavoriteCourseImplCustom{

    void deleteAllByCourse(Course course);
}
