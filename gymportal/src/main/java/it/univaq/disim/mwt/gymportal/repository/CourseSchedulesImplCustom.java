package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.CourseSchedules;
import org.springframework.data.jpa.repository.Query;

public interface CourseSchedulesImplCustom {

    @Query(value = "FROM CourseSchedules AS cs WHERE cs.id = :id")
    CourseSchedules findCourseSchedulesById(long id);
}
