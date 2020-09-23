package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.GymRepository;

@Service
@Transactional
public class GymBOImpl implements GymBO{
	
	@Autowired
	private GymRepository gymRepository;

	@Override
	public void deleteGym(Gym gym)  {
		gymRepository.delete(gym);
	}

	@Override
	public void createGym(Gym gym)  {
		gymRepository.save(gym);
	}

	@Override
	public void updateGym(Gym gym)  {
		gymRepository.save(gym);
	}

	@Override
	public List<Gym> findAllGym()  {
		return (List<Gym>) gymRepository.findAll();
	}

	@Override
	public List<Gym> findByRegion(String region)  {
		return gymRepository.findByRegionName(region);
	}

	@Override
	public Gym findByID(long id)  {
		return gymRepository.findByID(id);
	}

	@Override
	public List<Gym> searchByRegionAndName(String region, String name)  {
		return gymRepository.searchByRegionAndName(region, name);
	}
	
	@Override
	public List<Gym> searchByRegionAndUser(String region, long id)  {
		return gymRepository.searchByRegionAndUser(region, id);
	}
	
	@Override
	public List<Gym> searchByRegionAndNameAndUser(String region, String name, long id)  {
		return gymRepository.searchByRegionAndNameAndUser(region, name, id);
	}
	
	@Override
	public List<Gym> searchByUser(long id)  {
		return gymRepository.searchByUser(id);
	}


}
