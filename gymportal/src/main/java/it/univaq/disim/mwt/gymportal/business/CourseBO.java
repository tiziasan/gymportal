package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.Course;

@Service
public interface CourseBO {
	
	void deleteCourse (Long id) throws BusinessException;

	void createCourse (Course course) throws BusinessException;
	
	void updateCourse (Course course) throws BusinessException;
	
	List<Course> findAllCourse() throws BusinessException;
	
	List<Course> findCourseByGymId(long id) throws BusinessException;



}
