package it.univaq.disim.mwt.gymportal.repository;


import it.univaq.disim.mwt.gymportal.domain.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

}
