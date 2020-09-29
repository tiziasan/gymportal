package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChatBOImpl implements ChatBO {

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
	public void deleteChatsByGymId(long gymId) throws BusinessException {
		chatRepository.deleteChatsByGymId(gymId);
	}

	@Override
	public Chat findChatById(String id) throws BusinessException {
		return chatRepository.findChatById(id);
	}

	@Override
	public Set<Chat> findByUserId(long userId) throws BusinessException {
		return chatRepository.findByUserId(userId);
	}

	@Override
	public Set<Chat> findByGymId(long gymId) throws BusinessException {
		return chatRepository.findByGymId(gymId);
	}

	@Override
	public Chat findByUserIdAndGymId(long userId, long gymId) throws BusinessException {
		return chatRepository.findByUserIdAndGymId(userId, gymId);

	}

}
