package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CourseImplCustom {
    @Query(value = "FROM Course AS c INNER JOIN Gym AS g ON c.gym.id=g.id and g.id=:gym_id")
    Set<Course> findCourseByGymId(long gym_id);

    @Query(value = "FROM Course AS c WHERE c.id = :id")
    Course findByID(long id);

    @Modifying
    @Query(value = "DELETE FROM Course AS c WHERE c.gym.id = :id")
    void deleteAllCourseByGymId(long id);

    @Query(value = "FROM Course AS c WHERE c.gym.id = :id AND c.name LIKE CONCAT('%',:name,'%')")
    Set<Course> searchByIdAndName(long id, String name);

    @Query(value = "FROM Course AS c WHERE c.gym.id = :id AND c.gym.user.id = :idUser AND c.name LIKE CONCAT('%',:name,'%')")
    Set<Course> searchByIdAndNameAndUser(long id, String name, long idUser);

}




