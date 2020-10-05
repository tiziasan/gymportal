package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favoritecourse", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "user_id"}))
public class FavoriteCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer user;

    @ManyToOne
    private Course course;

    public FavoriteCourse(long id, Customer user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
    }

    public FavoriteCourse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteCourse)) return false;
        FavoriteCourse that = (FavoriteCourse) o;
        return getId() == that.getId() &&
                getUser().equals(that.getUser()) &&
                getCourse().equals(that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser().getId(), getCourse().getId());
    }

    @Override
    public String toString() {
        return "FavoriteCourse{" +
                "id=" + id +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
