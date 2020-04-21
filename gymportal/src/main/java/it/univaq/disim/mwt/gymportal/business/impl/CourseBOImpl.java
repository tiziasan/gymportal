package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.repository.CourseRepository;

@Service
@Transactional
public class CourseBOImpl implements CourseBO {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void deleteCourse(Course course) throws BusinessException {
		courseRepository.delete(course);
	}

	@Override
	public void createCourse(Course course) throws BusinessException {
		courseRepository.save(course);	
	}

	@Override
	public void updateCourse(Course course) throws BusinessException {
		courseRepository.save(course);
	}

	@Override
	public List<Course> findAllCourse() throws BusinessException {
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public List<Course> findCourseByGymId(long gym_id) throws BusinessException {
		return courseRepository.findCourseByGymId(gym_id);
	}

	@Override
	public Course findByID(Long id) throws BusinessException {
		return courseRepository.findByID(id);
	}

	@Override
	public void deleteAllCourseByGymId(Long id) throws BusinessException {
		 courseRepository.deleteAllCourseByGymId(id);
	}

	@Override
	public List<Course> searchByIdAndName(Long id, String name) throws BusinessException {
		 return courseRepository.searchByIdAndName(id, name);
	}
	
	@Override
	public List<Course> searchByIdAndNameAndUser(Long id, String name, Long idUtente) throws BusinessException {
		 return courseRepository.searchByIdAndNameAndUser(id, name, idUtente);
	}
	
	

	
		
}
