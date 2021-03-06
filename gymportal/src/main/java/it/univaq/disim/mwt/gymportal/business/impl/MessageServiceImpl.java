package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.MessageService;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message createMessage(Message msg) throws BusinessException {
        msg.setDate(LocalDateTime.now());
        return messageRepository.save(msg);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Message> findByChat(Chat chat) throws BusinessException {
        return messageRepository.findByChat(chat);
    }

    @Override
    public void deleteMessagesByChat(Chat chat) throws BusinessException {
        messageRepository.deleteMessagesByChat(chat);
    }
}
