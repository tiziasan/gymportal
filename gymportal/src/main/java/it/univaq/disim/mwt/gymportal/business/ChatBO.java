package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

import java.util.Set;

public interface ChatBO {

    Chat saveChat(Chat chat) throws BusinessException;

    void saveAllChats(Set<Chat> chatList) throws BusinessException;

    void deleteChatsByGymId(Gym gym) throws BusinessException;

    Chat findChatById(String id) throws BusinessException;

    Set<Chat> findByUserId(User user) throws BusinessException;

    Set<Chat> findByGymId(Gym gym) throws BusinessException;

    Chat findByUserIdAndGymId(User user, long gymId) throws BusinessException;


}
