package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.repository.CourseRepository;

@Service
@Transactional
public class CourseBOImpl implements CourseBO {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void deleteCourse(Course course)  {
		courseRepository.delete(course);
	}

	@Override
	public void createCourse(Course course)  {
		courseRepository.save(course);	
	}

	@Override
	public void updateCourse(Course course)  {
		courseRepository.save(course);
	}

	@Override
	public List<Course> findAllCourse()  {
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public List<Course> findCourseByGymId(long gym_id)  {
		return courseRepository.findCourseByGymId(gym_id);
	}

	@Override
	public Course findByID(long id)  {
		return courseRepository.findByID(id);
	}

	@Override
	public void deleteAllCourseByGymId(long id)  {
		 courseRepository.deleteAllCourseByGymId(id);
	}

	@Override
	public List<Course> searchByIdAndName(long id, String name)  {
		 return courseRepository.searchByIdAndName(id, name);
	}
	
	@Override
	public List<Course> searchByIdAndNameAndUser(long id, String name, long idUtente)  {
		 return courseRepository.searchByIdAndNameAndUser(id, name, idUtente);
	}
	
	

	
		
}
