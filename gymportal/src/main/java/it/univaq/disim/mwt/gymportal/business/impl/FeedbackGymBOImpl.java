package it.univaq.disim.mwt.gymportal.business.impl;

import java.util.List;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.gymportal.business.FeedbackGymBO;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.repository.FeedbackGymRepository;

@Service
@Transactional
public class FeedbackGymBOImpl implements FeedbackGymBO {
	
	@Autowired
	private FeedbackGymRepository feedbackGymRepository;

	@Override
	public void createFeedbackGym(FeedbackGym feedbackGym)  {
		feedbackGymRepository.save(feedbackGym);
		
	}

	@Override
	public void updateFeedbackGym(FeedbackGym feedbackGym)  {
		feedbackGymRepository.save(feedbackGym);
		
	}

	@Override
	public void deleteFeedbackGym(FeedbackGym feedbackGym)  {
		feedbackGymRepository.delete(feedbackGym);
		
	}

	@Override
	public List<FeedbackGym> findAllFeedbackGym(long id)  {
		return feedbackGymRepository.findAllFeedbackGym(id);
	}

	@Override
	public List<FeedbackGym> findAllFeedbackByGym(long id)  {
		return feedbackGymRepository.findAllFeedbackByGym(id);

	}
	
	@Override
	public List<FeedbackGym> findAllFeedbackByUserId(long id)  {
		return feedbackGymRepository.findAllFeedbackByUserId(id);

	}

	@Override
	public void deleteAllByGym(Gym gym) {
		feedbackGymRepository.deleteAllByGym(gym);
	}

	@Override
	public FeedbackGym findByID(long id)  {
		return feedbackGymRepository.findByID(id);
	}
	

}
