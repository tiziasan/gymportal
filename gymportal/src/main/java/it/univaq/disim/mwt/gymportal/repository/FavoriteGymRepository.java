package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteGymRepository extends CrudRepository<FavoriteGym, Long>, FavoriteGymImplCustom{

    void deleteAllByGym(Gym gym);
}
