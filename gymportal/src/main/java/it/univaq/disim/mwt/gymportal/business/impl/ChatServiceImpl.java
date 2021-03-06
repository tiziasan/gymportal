package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatService;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat saveChat(Chat chat) throws BusinessException {
        return chatRepository.save(chat);
    }

    @Override
    public void saveAllChats(Set<Chat> chatList) throws BusinessException {
        chatRepository.saveAll(chatList);
    }

    @Override
    public void deleteChatsByGymId(Gym gym) throws BusinessException {
        chatRepository.deleteChatsByGymId(gym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Chat findChatById(String id) throws BusinessException {
        return chatRepository.findChatById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Chat> findByUserId(User user) throws BusinessException {
        return chatRepository.findByUserId(user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Chat> findByGymId(Gym gym) throws BusinessException {
        return chatRepository.findByGymId(gym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Chat findByUserIdAndGymId(User user, long gymId) throws BusinessException {
        return chatRepository.findByUserIdAndGymId(user.getId(), gymId);

    }

}
