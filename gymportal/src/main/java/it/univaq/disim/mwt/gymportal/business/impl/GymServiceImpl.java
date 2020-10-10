package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.domain.*;
import it.univaq.disim.mwt.gymportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class GymServiceImpl implements GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private FavoriteGymRepository favoriteGymRepository;

    @Autowired
    private FeedbackGymRepository feedbackGymRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSchedulesRepository schedulesRepository;

    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    private FeedbackCourseRepository feedbackCourseRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void deleteGym(Gym gym) throws BusinessException {
        Set<Course> courses = courseRepository.findCourseByGymId(gym.getId());
        System.out.println(gym);
        System.out.println(courses.size());
        for (Course c : courses) {
            favoriteCourseRepository.deleteAllByCourse(c);
            feedbackCourseRepository.deleteAllByCourse(c);
            schedulesRepository.deleteByCourse(c);
            courseRepository.delete(c);
        }

        favoriteGymRepository.deleteAllByGym(gym);
        feedbackGymRepository.deleteAllByGym(gym);

        Set<Chat> chats = chatRepository.findByGymId(gym.getId());
        for (Chat c : chats) {
            messageRepository.deleteMessagesByChat(c);
        }
        chatRepository.deleteChatsByGymId(gym.getId());

        gymRepository.deleteById(gym.getId());
    }

    @Override
    public Gym createGym(Gym gym) throws BusinessException {
        return gymRepository.save(gym);
    }

    @Override
    public void updateGym(Gym gym) throws BusinessException {
        Set<Chat> chatList = chatRepository.findByGymId(gym.getId());
        for (Chat c : chatList) {
            c.setGymName(gym.getName());
        }
        chatRepository.saveAll(chatList);

        gymRepository.save(gym);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> findAllGym() throws BusinessException {
        return (Set<Gym>) gymRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> findByRegion(String region) throws BusinessException {
        return gymRepository.findByRegionName(region);
    }

    @Override
    @Transactional(readOnly = true)
    public Gym findByID(long id) throws BusinessException {
        return gymRepository.findByID(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> searchByRegionAndName(String region, String name) throws BusinessException {
        return gymRepository.searchByRegionAndName(region, name);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> searchByRegionAndUser(String region, User user) throws BusinessException {
        return gymRepository.searchByRegionAndUser(region, user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> searchByRegionAndNameAndUser(String region, String name, User user) throws BusinessException {
        return gymRepository.searchByRegionAndNameAndUser(region, name, user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Gym> searchByUser(User user) throws BusinessException {
        return gymRepository.searchByUser(user.getId());
    }


}
