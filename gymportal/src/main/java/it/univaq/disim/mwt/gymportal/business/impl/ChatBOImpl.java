package it.univaq.disim.mwt.gymportal.business.impl;

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
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

	@Override
	public void saveAllChats(List<Chat> chatList) {
		chatRepository.saveAll(chatList);
	}

	@Override
	public void deleteChatsByGymId(long gymId) {
		chatRepository.deleteChatsByGymId(gymId);
	}

	@Override
	public Chat findChatById(String id) {
		return chatRepository.findChatById(id);
	}

	@Override
	public List<Chat> findByUserId(long userId) {
		return chatRepository.findByUserId(userId);
	}

	@Override
	public List<Chat> findByGymId(long gymId) {
		return chatRepository.findByGymId(gymId);
	}

	@Override
	public Chat findByUserIdAndGymId(long userId, long gymId) {
		return chatRepository.findByUserIdAndGymId(userId, gymId);

	}

}
