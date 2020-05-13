package it.univaq.disim.mwt.gymportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.gymportal.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
    User findUserById(Long id);

}