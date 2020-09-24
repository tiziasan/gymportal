package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    Chat saveChat(Chat chat) throws BusinessException;
    void saveAllChats(List<Chat> chatList) throws BusinessException;

    void deleteChatsByGymId(long gymId) throws BusinessException;

    Chat findChatById(String id) throws BusinessException;
    List<Chat> findByUserId(long userId) throws BusinessException;
    List<Chat> findByGymId(long gymId) throws BusinessException;
    Chat findByUserIdAndGymId(long userId, long gymId) throws BusinessException;


}
