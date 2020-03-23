package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface GymBOImplCustom {
	@Query(value = "SELECT gym.region FROM gym ")
	public List<Gym> findByRegionName(String region);
	
}