package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "feedbackcourse", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "user_id"}))
public class FeedbackCourse extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 150)
    @Column(name = "FEED")
    private String feed;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "RATING")
    private int rating;

    @ManyToOne
    private Customer user;

    @ManyToOne
    private Course course;

    public FeedbackCourse(String feed, int rating, Customer user, Course course) {
        this.feed = feed;
        this.rating = rating;
        this.user = user;
        this.course = course;
    }

    public FeedbackCourse() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        if (!(o instanceof FeedbackCourse)) return false;
        FeedbackCourse that = (FeedbackCourse) o;
        return getRating() == that.getRating() &&
                getFeed().equals(that.getFeed()) &&
                getUser().equals(that.getUser()) &&
                getCourse().equals(that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeed(), getRating(), getUser().getId(), getCourse().getId());
    }

    @Override
    public String toString() {
        return "FeedbackCourse{" +
                "id='" + getId() + '\'' +
                ", version='" + getVersion() + '\'' +
                ", feed='" + feed + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
