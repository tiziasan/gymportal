package it.univaq.disim.mwt.gymportal.business.impl;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackGymService;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.FeedbackGymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(transactionManager = "standardtrans")
public class FeedbackGymServiceImpl implements FeedbackGymService {

    @Autowired
    private FeedbackGymRepository feedbackGymRepository;

    @Override
    public void createFeedbackGym(FeedbackGym feedbackGym) throws BusinessException {
        feedbackGymRepository.save(feedbackGym);

    }

    @Override
    public void updateFeedbackGym(FeedbackGym feedbackGym) throws BusinessException {
        feedbackGymRepository.save(feedbackGym);

    }

    @Override
    public void deleteFeedbackGym(FeedbackGym feedbackGym) throws BusinessException {
        feedbackGymRepository.deleteById(feedbackGym.getId());

    }

    @Override
    @Transactional(readOnly = true)
    public Set<FeedbackGym> findAllFeedbackGym(FeedbackGym feedbackGym) throws BusinessException {
        return feedbackGymRepository.findAllFeedbackGym(feedbackGym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<FeedbackGym> findAllFeedbackByGym(Gym gym) throws BusinessException {
        return feedbackGymRepository.findAllFeedbackByGym(gym.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<FeedbackGym> findAllFeedbackByUserId(User user) throws BusinessException {
        return feedbackGymRepository.findAllFeedbackByUserId(user.getId());
    }

    @Override
    public void deleteAllByGym(Gym gym) {
        feedbackGymRepository.deleteAllByGym(gym);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackGym findByID(long id) throws BusinessException {
        return feedbackGymRepository.findByID(id);
    }


}
