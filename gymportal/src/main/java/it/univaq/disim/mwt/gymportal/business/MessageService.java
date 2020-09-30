package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Message;

import java.util.Set;

public interface MessageService {

    Message createMessage (Message msg) throws BusinessException;

    Set<Message> findByChat(Chat chat) throws BusinessException;

    void deleteMessagesByChat(Chat chat) throws BusinessException;


}
