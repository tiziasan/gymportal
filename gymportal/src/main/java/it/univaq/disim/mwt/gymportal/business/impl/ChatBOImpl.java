package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBOImpl implements ChatBO {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public void createChat(Chat chat) throws BusinessException {
        chatRepository.save(chat);
    }
}
