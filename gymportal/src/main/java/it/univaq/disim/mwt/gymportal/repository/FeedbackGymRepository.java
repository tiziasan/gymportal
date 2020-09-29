package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackGymRepository extends CrudRepository<FeedbackGym, Long>, FeedbackGymImplCustom{

    void deleteAllByGym(Gym gym);

}
