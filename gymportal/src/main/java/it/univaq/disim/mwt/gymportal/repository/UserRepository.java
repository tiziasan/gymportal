package it.univaq.disim.mwt.gymportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.User;

@Repository

public interface UserRepository extends CrudRepository<User, Long>, UserImplCustom{


}
