package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.Gym;

@Service
public interface GymBO {

	void deleteGym (int id) throws BusinessException;

	void createGym (Gym gym) throws BusinessException;
	
	void updateGym (Gym gym) throws BusinessException;
	
	List<Gym> findAllGym() throws BusinessException;

	List<Gym> findByRegion(String region) throws BusinessException;
	
}
