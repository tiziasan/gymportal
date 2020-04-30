package it.univaq.disim.mwt.gymportal.business;

import java.util.List;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

public interface GymBO {

	void deleteGym (Gym gym) throws BusinessException;

	void createGym (Gym gym) throws BusinessException;
	
	void updateGym (Gym gym) throws BusinessException;
	
	List<Gym> findAllGym() throws BusinessException;

	List<Gym> findByRegion(String region) throws BusinessException;
	
	Gym findByID(Long id) throws BusinessException;
	
	List<Gym> searchByRegionAndName(String region, String name) throws BusinessException;
	
	List<Gym> searchByRegionAndUser(String region, Long id) throws BusinessException;

	List<Gym> searchByRegionAndNameAndUser(String region, String name, Long id) throws BusinessException;

	List<Gym> searchByUser(Long id) throws BusinessException;

}
