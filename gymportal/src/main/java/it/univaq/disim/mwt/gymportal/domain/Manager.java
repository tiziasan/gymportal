package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue(Role.Values.MANAGER)
public class Manager extends User{

    @OneToMany(mappedBy="user")
    private List<Gym> gym;

}
