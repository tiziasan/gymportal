package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    void createChat(Chat chat) throws BusinessException;
    
    List<Chat> findByUser_id(Long user_id) throws BusinessException;

}
