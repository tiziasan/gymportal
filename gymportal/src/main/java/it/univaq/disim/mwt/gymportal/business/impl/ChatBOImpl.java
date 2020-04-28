package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBOImpl implements ChatBO {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) throws BusinessException {
        return chatRepository.save(chat);
    }

	@Override
	public Chat findChatById(String id) throws BusinessException {
		return chatRepository.findChatById(id);
	}

	@Override
	public List<Chat> findByUserId(Long userId) throws BusinessException {
		return chatRepository.findByUserId(userId);
	}

	@Override
	public List<Chat> findByGymId(Long gymId) throws BusinessException {
		return chatRepository.findByGymId(gymId);
	}

	@Override
	public Chat findByUserIdAndGymId(Long userId, Long gymId) throws BusinessException {
		return chatRepository.findByUserIdAndGymId(userId, gymId);

	}

}
