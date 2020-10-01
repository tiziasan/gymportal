package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.CUSTOMER)
public class Customer extends User {

    @OneToMany(mappedBy = "user")
    private Set<FeedbackCourse> feedbackCourses;
    @OneToMany(mappedBy = "user")
    private Set<FavoriteGym> favoriteGyms;
    @OneToMany(mappedBy = "user")
    private Set<FavoriteCourse> favoriteCourses;
    @OneToMany(mappedBy = "user")
    private Set<FeedbackGym> feedbackGyms;

    public Customer() {
    }

    public Customer(String username, String email, String password, String name, String lastname) {
        super(username, email, password, name, lastname, Role.CUSTOMER);
    }

    public Customer(User user) {
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getLastname(), Role.CUSTOMER);
    }

    public Set<FeedbackCourse> getFeedbackCourses() {
        return feedbackCourses;
    }

    public Set<FavoriteGym> getFavoriteGyms() {
        return favoriteGyms;
    }

    public Set<FavoriteCourse> getFavoriteCourses() {
        return favoriteCourses;
    }

    public Set<FeedbackGym> getFeedbackGyms() {
        return feedbackGyms;
    }


}
