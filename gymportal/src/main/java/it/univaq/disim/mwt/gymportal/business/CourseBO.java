package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.Course;

public interface CourseBO {
	
	void deleteCourse (Course course) ;

	void createCourse (Course course) ;
	
	void updateCourse (Course course) ;
	
	List<Course> findAllCourse() ;
	
	List<Course> findCourseByGymId(long id) ;
	
	Course findByID(long id) ;
	
	void deleteAllCourseByGymId (long id) ;

	List<Course> searchByIdAndName(long id, String name) ;

	List<Course> searchByIdAndNameAndUser(long id, String name, long idUser) ;


}
