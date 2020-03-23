package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

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
	public void deleteGym(int id) throws BusinessException {
		gymRepository.deleteById(id);
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

}
