package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBOImpl implements MessageBO {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message createMessage(Message msg) {
        return messageRepository.save(msg);
    }

	@Override
	public List<Message> findByChat(Chat chat) {
		return messageRepository.findByChat(chat);
	}

    @Override
    public void deleteMessagesByChat(Chat chat) {
        messageRepository.deleteMessagesByChat(chat);
    }
}
