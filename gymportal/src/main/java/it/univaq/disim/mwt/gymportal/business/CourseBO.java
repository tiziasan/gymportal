package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.Course;

public interface CourseBO {
	
	void deleteCourse (Course course) throws BusinessException;

	void createCourse (Course course) throws BusinessException;
	
	void updateCourse (Course course) throws BusinessException;
	
	List<Course> findAllCourse() throws BusinessException;
	
	List<Course> findCourseByGymId(long id) throws BusinessException;
	
	Course findByID(long id) throws BusinessException;
	
	void deleteAllCourseByGymId (long id) throws BusinessException;

	List<Course> searchByIdAndName(long id, String name) throws BusinessException;

	List<Course> searchByIdAndNameAndUser(long id, String name, long idUser) throws BusinessException;


}
