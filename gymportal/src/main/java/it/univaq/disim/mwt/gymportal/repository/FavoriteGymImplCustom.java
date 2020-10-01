package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FavoriteGymImplCustom {

    @Query(value = "FROM FavoriteGym as f WHERE f.id = :id")
    Set<FavoriteGym> findAllFavoriteGym(long id);

    @Query(value = "FROM FavoriteGym as f WHERE f.user.id = :id")
    Set<FavoriteGym> findAllFavoriteByUserId(long id);


}
