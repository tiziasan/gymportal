package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface GymBOImplCustom {
	@Query(value = "FROM Gym AS g WHERE g.region = :region")
	public List<Gym> findByRegionName(String region);
	
}