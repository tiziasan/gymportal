package it.univaq.disim.mwt.gymportal.repository;


import it.univaq.disim.mwt.gymportal.domain.Chat;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

	Chat findChatById(String id);

	List<Chat> findByUserId(long userId);
	
	List<Chat> findByGymId(long gymId);

	Chat findByUserIdAndGymId(long userId, long gymId);
	
	void deleteChatsByGymId(long gymId);
}
