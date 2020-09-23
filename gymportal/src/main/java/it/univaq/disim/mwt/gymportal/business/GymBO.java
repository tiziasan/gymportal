package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

public interface GymBO {

	void deleteGym (Gym gym);

	void createGym (Gym gym);
	
	void updateGym (Gym gym);
	
	List<Gym> findAllGym();

	List<Gym> findByRegion(String region);
	
	Gym findByID(long id);
	
	List<Gym> searchByRegionAndName(String region, String name);
	
	List<Gym> searchByRegionAndUser(String region, long id);

	List<Gym> searchByRegionAndNameAndUser(String region, String name, long id);

	List<Gym> searchByUser(long id);

}
