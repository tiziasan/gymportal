package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Chat;

public interface ChatBO {

    void createChat(Chat chat) throws BusinessException;


}
