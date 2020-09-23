package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.CombinedTransactionalBO;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;
import it.univaq.disim.mwt.gymportal.repository.GymRepository;
import it.univaq.disim.mwt.gymportal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "chainedTransactionManager")
public class CombinedTransactionalBOImpl implements CombinedTransactionalBO {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void deleteGymAndRelatedChats(Gym gym) {
        List<Chat> chats = chatRepository.findByGymId(gym.getId());
        for ( Chat c: chats ) {
            messageRepository.deleteMessagesByChat(c);
        }
        chatRepository.deleteChatsByGymId(gym.getId());
        gymRepository.delete(gym);
    }

}
