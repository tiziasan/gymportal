package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;

@Repository
public interface FavoriteGymRepository extends CrudRepository<FavoriteGym, Long>, FavoriteGymImplCustom{

    void deleteAllByGym(Gym gym);
}
