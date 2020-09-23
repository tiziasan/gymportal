package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Course;
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
    private GymBO serviceGym;

    @Autowired
    private FavoriteGymBO serviceFavoriteGym;
    @Autowired
    private FeedbackGymBO serviceFeedbackGym;

    @Autowired
    private CourseBO serviceCourse;

    @Autowired
    private FavoriteCourseBO serviceFavoriteCourse;
    @Autowired
    private FeedbackCourseBO serviceFeedbackCourse;

    @Autowired
    private ChatBO serviceChat;

    @Autowired
    private MessageBO serviceMessage;


    @Override
    public void deleteGymAndRelatedChats(Gym gym){
        List<Course> courses = serviceCourse.findCourseByGymId(gym.getId());
        for (Course c: courses){
            serviceFavoriteCourse.deleteAllByCourse(c);
            serviceFeedbackCourse.deleteAllByCourse(c);
        }
        serviceCourse.deleteAllCourseByGymId(gym.getId());

        serviceFavoriteGym.deleteAllByGym(gym);
        serviceFeedbackGym.deleteAllByGym(gym);
        serviceGym.deleteGym(gym);

        List<Chat> chats = serviceChat.findByGymId(gym.getId());
        for ( Chat c: chats ) {
            serviceMessage.deleteMessagesByChat(c);
        }
        serviceChat.deleteChatsByGymId(gym.getId());
    }

}
