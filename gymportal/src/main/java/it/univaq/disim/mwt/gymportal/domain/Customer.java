package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue(Role.Values.CUSTOMER)
public class Customer extends User {

    @OneToMany(mappedBy="user")
    private List<FeedbackCourse> feedbackCourse;

    @OneToMany(mappedBy="user")
    private List<FavoriteGym> favoriteGym;

    @OneToMany(mappedBy="user")
    private List<FavoriteCourse> favoriteCourse;

    @OneToMany(mappedBy="user")
    private List<FeedbackGym> feedbackGym;

}
