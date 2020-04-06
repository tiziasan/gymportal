package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;

public interface FavoriteGymImplCustom {

	@Query(value="FROM FavoriteGym as f WHERE f.id = :id")
	public List<FavoriteGym> findAllFavoriteGym(Long id);
}
