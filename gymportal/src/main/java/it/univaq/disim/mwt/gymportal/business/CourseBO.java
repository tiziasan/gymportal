package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.Course;

public interface CourseBO {
	
	void deleteCourse (Course course) throws BusinessException;

	void createCourse (Course course) throws BusinessException;
	
	void updateCourse (Course course) throws BusinessException;
	
	List<Course> findAllCourse() throws BusinessException;
	
	List<Course> findCourseByGymId(long id) throws BusinessException;
	
	Course findByID(Long id) throws BusinessException;
	
	void deleteAllCourseByGymId (Long id) throws BusinessException;

	List<Course> searchByIdAndName(Long id, String name) throws BusinessException;



}
