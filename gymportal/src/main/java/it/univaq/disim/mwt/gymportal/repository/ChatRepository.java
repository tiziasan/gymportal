package it.univaq.disim.mwt.gymportal.repository;


import it.univaq.disim.mwt.gymportal.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

    Chat findChatById(String id);

    Set<Chat> findByUserId(long userId);

    Set<Chat> findByGymId(long gymId);

    Chat findByUserIdAndGymId(long userId, long gymId);

    void deleteChatsByGymId(long gymId);
}
