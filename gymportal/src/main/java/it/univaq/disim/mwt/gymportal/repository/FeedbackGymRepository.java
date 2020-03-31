package it.univaq.disim.mwt.gymportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

@Repository
public interface FeedbackGymRepository extends CrudRepository<FeedbackGym, Long>, FeedbackGymImplCustom{

}
