package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteCourseRepository extends CrudRepository<FavoriteCourse, Long>, FavoriteCourseImplCustom{

    void deleteAllByCourse(Course course);
}
