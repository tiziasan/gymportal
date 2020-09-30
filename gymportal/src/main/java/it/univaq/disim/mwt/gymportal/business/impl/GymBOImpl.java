package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class GymBOImpl implements GymBO{
	
	@Autowired
	private GymRepository gymRepository;

	@Override
	public void deleteGym(Gym gym) throws BusinessException {
		gymRepository.deleteById(gym.getId());
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
	public Set<Gym> findAllGym() throws BusinessException {
		return (Set<Gym>) gymRepository.findAll();
	}

	@Override
	public Set<Gym> findByRegion(String region) throws BusinessException {
		return gymRepository.findByRegionName(region);
	}

	@Override
	public Gym findByID(long id) throws BusinessException {
		return gymRepository.findByID(id);
	}

	@Override
	public Set<Gym> searchByRegionAndName(String region, String name) throws BusinessException {
		return gymRepository.searchByRegionAndName(region, name);
	}
	
	@Override
	public Set<Gym> searchByRegionAndUser(String region, User user) throws BusinessException {
		return gymRepository.searchByRegionAndUser(region, user.getId());
	}
	
	@Override
	public Set<Gym> searchByRegionAndNameAndUser(String region, String name, User user) throws BusinessException {
		return gymRepository.searchByRegionAndNameAndUser(region, name, user.getId());
	}
	
	@Override
	public Set<Gym> searchByUser(User user) throws BusinessException {
		return gymRepository.searchByUser(user.getId());
	}


}
