package it.univaq.disim.mwt.gymportal.repository;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GymImplCustom {
    @Query(value = "FROM Gym AS g WHERE g.region = :region")
    Set<Gym> findByRegionName(String region);

    @Query(value = "FROM Gym AS g WHERE g.id = :id")
    Gym findByID(long id);

    @Query(value = "FROM Gym AS g WHERE g.region = :region AND g.name LIKE CONCAT('%',:name,'%')")
    Set<Gym> searchByRegionAndName(String region, String name);

    @Query(value = "FROM Gym AS g WHERE g.region = :region AND g.user.id = :id AND g.name LIKE CONCAT('%',:name,'%')")
    Set<Gym> searchByRegionAndNameAndUser(String region, String name, long id);

    @Query(value = "FROM Gym AS g WHERE g.region = :region AND g.user.id = :id")
    Set<Gym> searchByRegionAndUser(String region, long id);

    @Query(value = "FROM Gym AS g WHERE g.user.id = :id")
    Set<Gym> searchByUser(long id);

}