package it.univaq.disim.mwt.gymportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;

public interface FeedbackGymImplCustom {
	
	@Query(value="FROM FeedbackGym as F WHERE f.id = :id")
	public List<FeedbackGym> findAllFeedbackGym(Long id);
}
