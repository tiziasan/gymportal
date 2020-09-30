package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.CUSTOMER)
public class Customer extends User {

    public Customer() {
    }

    public Customer(String username, String email, String password, String name, String lastname) {
        super(username, email, password, name, lastname, Role.CUSTOMER);
    }

    public Customer(User user){
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getLastname(), Role.CUSTOMER);
    }

    @OneToMany(mappedBy="user")
    private Set<FeedbackCourse> feedbackCourse;

    @OneToMany(mappedBy="user")
    private Set<FavoriteGym> favoriteGym;

    @OneToMany(mappedBy="user")
    private Set<FavoriteCourse> favoriteCourse;

    @OneToMany(mappedBy="user")
    private Set<FeedbackGym> feedbackGym;

    public Set<FeedbackCourse> getFeedbackCourse() {
        return feedbackCourse;
    }

    public Set<FavoriteGym> getFavoriteGym() {
        return favoriteGym;
    }

    public Set<FavoriteCourse> getFavoriteCourse() {
        return favoriteCourse;
    }

    public Set<FeedbackGym> getFeedbackGym() {
        return feedbackGym;
    }


}
