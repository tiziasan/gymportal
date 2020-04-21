package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.GymRepository;

@Service
@Transactional
public class GymBOImpl implements GymBO{
	
	@Autowired
	private GymRepository gymRepository;

	@Override
	public void deleteGym(Gym gym) throws BusinessException {
		gymRepository.delete(gym);
	}

	@Override
	public void createGym(Gym gym) throws BusinessException {
		gymRepository.save(gym);
	}

	@Override
	public void updateGym(Gym gym) throws BusinessException {
		gymRepository.save(gym);
	}

	@Override
	public List<Gym> findAllGym() throws BusinessException {
		return (List<Gym>) gymRepository.findAll();
	}

	@Override
	public List<Gym> findByRegion(String region) throws BusinessException {
		return gymRepository.findByRegionName(region);
	}

	@Override
	public Gym findByID(Long id) throws BusinessException {
		return gymRepository.findByID(id);
	}

	@Override
	public List<Gym> searchByRegionAndName(String region, String name) throws BusinessException {
		return gymRepository.searchByRegionAndName(region, name);
	}
	
	@Override
	public List<Gym> searchByRegionAndUser(String region, Long id) throws BusinessException {
		return gymRepository.searchByRegionAndUser(region, id);
	}
	
	@Override
	public List<Gym> searchByRegionAndNameAndUser(String region, String name, Long id) throws BusinessException {
		return gymRepository.searchByRegionAndNameAndUser(region, name, id);
	}

	

}
