package it.univaq.disim.mwt.gymportal.business.impl;


import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.*;
import it.univaq.disim.mwt.gymportal.repository.ChatRepository;
import it.univaq.disim.mwt.gymportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional(transactionManager = "standardtrans")
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(transactionManager = "standardtrans")
    public <U extends User> U findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional(transactionManager = "standardtrans")
    public <U extends User> U findUserByUsernameAndRole(String username, Role role) {
        return userRepository.findByUsernameAndRole(username, role);
    }

    @Override
    @Transactional(transactionManager = "standardtrans")
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager")
    public User updateUser(User user, Role role) {
        Set<Chat> chatList = chatRepository.findByUserId(user.getId());
        for (Chat c : chatList) {
            c.setUserName(user.getName() + " " + user.getLastname());
        }
        chatRepository.saveAll(chatList);

        if ( role == Role.CUSTOMER) {
            Customer customer = new Customer(user);
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

            return userRepository.save(customer);

        } else {
            Manager manager = new Manager(user);
            manager.setPassword(bCryptPasswordEncoder.encode(manager.getPassword()));

            return userRepository.save(manager);
        }
    }



}