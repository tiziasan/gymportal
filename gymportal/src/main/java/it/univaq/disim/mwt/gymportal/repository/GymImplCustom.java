package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface GymImplCustom {
	@Query(value = "FROM Gym AS g WHERE g.region = :region")
	public List<Gym> findByRegionName(String region);
	
	@Query(value = "FROM Gym AS g WHERE g.id = :id")
	public Gym findByID(Long id);
	
	@Query(value = "FROM Gym AS g WHERE g.region = :region AND g.name LIKE CONCAT('%',:name,'%')")
	public List<Gym> searchByRegionAndName(String region, String name);
	
	@Query(value = "FROM Gym AS g WHERE g.region = :region AND g.user.id = :id AND g.name LIKE CONCAT('%',:name,'%')")
	public List<Gym> searchByRegionAndNameAndUser(String region, String name, Long id);
	
	@Query(value = "FROM Gym AS g WHERE g.region = :region AND g.user.id = :id")
	public List<Gym> searchByRegionAndUser(String region, Long id);
	
}