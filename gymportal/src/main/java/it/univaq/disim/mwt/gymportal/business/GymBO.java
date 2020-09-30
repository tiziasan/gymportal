package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

import java.util.Set;

public interface GymBO {

	void deleteGym (Gym gym) throws BusinessException;

	void createGym (Gym gym) throws BusinessException;
	
	void updateGym (Gym gym) throws BusinessException;
	
	Set<Gym> findAllGym() throws BusinessException;

	Set<Gym> findByRegion(String region) throws BusinessException;
	
	Gym findByID(long id) throws BusinessException;
	
	Set<Gym> searchByRegionAndName(String region, String name) throws BusinessException;
	
	Set<Gym> searchByRegionAndUser(String region, User user) throws BusinessException;

	Set<Gym> searchByRegionAndNameAndUser(String region, String name, User user) throws BusinessException;

	Set<Gym> searchByUser(User user) throws BusinessException;

}
