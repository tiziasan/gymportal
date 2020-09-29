package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Course;

import java.util.Set;

public interface CourseBO {
	
	void deleteCourse (Course course) throws BusinessException;

	void createCourse (Course course) throws BusinessException;
	
	void updateCourse (Course course) throws BusinessException;
	
	Set<Course> findAllCourse() throws BusinessException;
	
	Set<Course> findCourseByGymId(long id) throws BusinessException;
	
	Course findByID(long id) throws BusinessException;
	
	void deleteAllCourseByGymId (long id) throws BusinessException;

	Set<Course> searchByIdAndName(long id, String name) throws BusinessException;

	Set<Course> searchByIdAndNameAndUser(long id, String name, long idUser) throws BusinessException;


}
