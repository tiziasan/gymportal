package it.univaq.disim.mwt.gymportal.business.impl;


import java.util.Arrays;
import java.util.HashSet;

import it.univaq.disim.mwt.gymportal.business.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.RoleRepository;
import it.univaq.disim.mwt.gymportal.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "transactionManager")
public class UserBOimpl implements UserBO {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("utente");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("utente");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }

    @Override
    public User updateGestore(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("gestore");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }


}