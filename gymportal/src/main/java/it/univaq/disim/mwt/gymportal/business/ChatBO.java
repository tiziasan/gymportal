package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    Chat saveChat(Chat chat) throws BusinessException;
    void saveAllChats(List<Chat> chatList) throws BusinessException;

    void deleteChatsByGymId(Long gymId) throws BusinessException;

    Chat findChatById(String id) throws BusinessException;
    List<Chat> findByUserId(Long userId) throws BusinessException;
    List<Chat> findByGymId(Long gymId) throws BusinessException;
    Chat findByUserIdAndGymId(Long userId, Long gymId) throws BusinessException;


}
