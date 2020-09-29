package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends CrudRepository<Gym, Long>, GymImplCustom{



	
}
