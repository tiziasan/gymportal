package it.univaq.disim.mwt.gymportal.business;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Message;

public interface MessageBO {

    Message createMessage (Message msg) throws BusinessException;

    List<Message> findByChat(Chat chat) throws BusinessException;

    void deleteMessagesByChat(Chat chat) throws BusinessException;


}
