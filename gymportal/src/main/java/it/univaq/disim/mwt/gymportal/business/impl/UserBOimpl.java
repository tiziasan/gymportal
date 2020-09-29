package it.univaq.disim.mwt.gymportal.business.impl;


import it.univaq.disim.mwt.gymportal.business.UserBO;
import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBOimpl implements UserBO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public <U extends User> U findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public <U extends User> U findUserByUsernameAndRole(String username, Role role) {
        return userRepository.findByUsernameAndRole(username, role);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateCustomer(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public User updateManager(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.MANAGER);

        return userRepository.save(user);
    }


}