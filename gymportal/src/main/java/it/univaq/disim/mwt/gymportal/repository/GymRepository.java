package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.Gym;

@Repository
public interface GymRepository extends CrudRepository<Gym, Long>, GymBOImplCustom{
	
	List<Gym> findByRegion(String region);
	Gym findByID(Long id);


	
}
