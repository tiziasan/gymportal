package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Chat;

import java.util.List;
import java.util.Set;

public interface ChatBO {

    Chat saveChat(Chat chat) throws BusinessException;

    void saveAllChats(Set<Chat> chatList) throws BusinessException;

    void deleteChatsByGymId(long gymId) throws BusinessException;

    Chat findChatById(String id) throws BusinessException;

    Set<Chat> findByUserId(long userId) throws BusinessException;

    Set<Chat> findByGymId(long gymId) throws BusinessException;

    Chat findByUserIdAndGymId(long userId, long gymId) throws BusinessException;


}
