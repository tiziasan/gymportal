package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.MANAGER)
public class Manager extends User {

    @OneToMany(mappedBy = "user")
    private Set<Gym> gyms;

    public Manager() {
    }

    public Manager(String username, String email, String password, String name, String lastname) {
        super(username, email, password, name, lastname, Role.MANAGER);
    }

    public Manager(User user) {
        super(user.getId(), user.getVersion(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getLastname(), Role.MANAGER);
    }

    public Set<Gym> getGyms() {
        return gyms;
    }

}
