package it.univaq.disim.mwt.gymportal.repository;


import it.univaq.disim.mwt.gymportal.domain.Chat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository <Chat, String>{

	List<Chat> findByUsername(String username);
	
	List<Chat> findByGymId(Long gymId);
	
	
	
}