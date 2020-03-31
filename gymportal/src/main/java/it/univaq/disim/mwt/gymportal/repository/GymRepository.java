package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.Gym;

@Repository
public interface GymRepository extends CrudRepository<Gym, Long>, GymImplCustom{
	
	List<Gym> findByRegion(String region);
	Gym findByID(Long id);


	
}
