package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue(Role.Values.MANAGER)
public class Manager extends User{

    public Manager() {
    }

    public Manager(String username, String email, String password, String name, String lastname) {
        super(username, email, password, name, lastname, Role.MANAGER);
    }

    public Manager(User user){
        super(user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getLastname(), Role.MANAGER);
    }

    @OneToMany(mappedBy="user")
    private List<Gym> gym;

    public List<Gym> getGym() {
        return gym;
    }

}
