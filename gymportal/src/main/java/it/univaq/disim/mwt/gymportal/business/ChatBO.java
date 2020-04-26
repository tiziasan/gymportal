package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    Chat createChat(Chat chat) throws BusinessException;
    
    List<Chat> findByUsername(String username) throws BusinessException;
    List<Chat> findByGymId(Long gymId) throws BusinessException;

}
