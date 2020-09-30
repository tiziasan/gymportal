package it.univaq.disim.mwt.gymportal.business.impl;


import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.Manager;
import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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
        Customer customer = new Customer(user);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

        return userRepository.save(customer);
    }

    @Override
    public User updateManager(User user) {
        Manager manager = new Manager(user);
        manager.setPassword(bCryptPasswordEncoder.encode(manager.getPassword()));

        return userRepository.save(manager);
    }


}