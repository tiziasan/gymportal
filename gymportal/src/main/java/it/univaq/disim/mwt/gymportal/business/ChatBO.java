package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    Chat saveChat(Chat chat);
    void saveAllChats(List<Chat> chatList);

    void deleteChatsByGymId(long gymId);

    Chat findChatById(String id);
    List<Chat> findByUserId(long userId);
    List<Chat> findByGymId(long gymId);
    Chat findByUserIdAndGymId(long userId, long gymId);


}
