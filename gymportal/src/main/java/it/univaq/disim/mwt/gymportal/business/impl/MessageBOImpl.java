package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBOImpl implements MessageBO {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message createMessage(Message msg) throws BusinessException {
        Message m = messageRepository.save(msg);
        System.out.println(m);
        return m;
    }
}
