package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.Set;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackGymBO;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.repository.FeedbackGymRepository;

@Service
@Transactional
public class FeedbackGymBOImpl implements FeedbackGymBO {
	
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
		feedbackGymRepository.delete(feedbackGym);
		
	}

	@Override
	public Set<FeedbackGym> findAllFeedbackGym(long id) throws BusinessException {
		return feedbackGymRepository.findAllFeedbackGym(id);
	}

	@Override
	public Set<FeedbackGym> findAllFeedbackByGym(long id) throws BusinessException {
		return feedbackGymRepository.findAllFeedbackByGym(id);

	}
	
	@Override
	public Set<FeedbackGym> findAllFeedbackByUserId(long id) throws BusinessException {
		return feedbackGymRepository.findAllFeedbackByUserId(id);

	}

	@Override
	public void deleteAllByGym(Gym gym) {
		feedbackGymRepository.deleteAllByGym(gym);
	}

	@Override
	public FeedbackGym findByID(long id) throws BusinessException {
		return feedbackGymRepository.findByID(id);
	}
	

}
