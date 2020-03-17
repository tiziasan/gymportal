package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Gym;

public interface GymBO {

	void deleteGym (int id) throws BusinessException;

	void createGym (Gym gym) throws BusinessException;
	
	void updateGym (Gym gym) throws BusinessException;
	
	List<Gym> findAllGym() throws BusinessException;
}
