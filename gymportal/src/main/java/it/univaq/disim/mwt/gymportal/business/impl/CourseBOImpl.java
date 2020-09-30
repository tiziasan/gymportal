package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CourseBOImpl implements CourseBO {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void deleteCourse(Course course) throws BusinessException {
		courseRepository.deleteById(course.getId());
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
	public Set<Course> findAllCourse() throws BusinessException {
		return (Set<Course>) courseRepository.findAll();
	}

	@Override
	public Set<Course> findCourseByGymId(Gym gym) throws BusinessException {
		return courseRepository.findCourseByGymId(gym.getId());
	}

	@Override
	public Course findByID(long id) throws BusinessException {
		return courseRepository.findByID(id);
	}

	@Override
	public void deleteAllCourseByGymId(Gym gym) throws BusinessException {
		 courseRepository.deleteAllCourseByGymId(gym.getId());
	}

	@Override
	public Set<Course> searchByIdAndName(long id, String name) throws BusinessException {
		 return courseRepository.searchByIdAndName(id, name);
	}
	
	@Override
	public Set<Course> searchByIdAndNameAndUser(long id, String name, long idUtente) throws BusinessException {
		 return courseRepository.searchByIdAndNameAndUser(id, name, idUtente);
	}
	
	

	
		
}
