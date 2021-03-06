package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")

public class Course extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Size(max = 400)
    @Column(name = "DESCRIPTION")
    private String description;

    @NotBlank
    @Size(max = 50)
    @Column(name = "CODE")
    private String code;

    @NotBlank
    @Size(max = 50)
    @Column(name = "INSTRUCTOR")
    private String instructor;

    @OneToMany(mappedBy = "course")
    private Set<FeedbackCourse> feedbackCourses;

    @OneToMany(mappedBy = "course")
    private Set<FavoriteCourse> favoriteCourses;

    @OneToMany(mappedBy = "course")
    private Set<CourseSchedules> courseSchedules;

    @ManyToOne
    private Gym gym;

    public Course(String name, String description, String code, String instructor, Gym gym) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.instructor = instructor;
        this.gym = gym;
    }

    public Course() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Set<FeedbackCourse> getFeedbackCourses() {
        return feedbackCourses;
    }

    public void setFeedbackCourses(Set<FeedbackCourse> feedbackCourses) {
        this.feedbackCourses = feedbackCourses;
    }

    public Set<FavoriteCourse> getFavoriteCourses() {
        return favoriteCourses;
    }

    public void setFavoriteCourses(Set<FavoriteCourse> favoriteCourses) {
        this.favoriteCourses = favoriteCourses;
    }

    public Set<CourseSchedules> getCourseSchedules() {
        return courseSchedules;
    }

    public void setCourseSchedules(Set<CourseSchedules> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getName().equals(course.getName()) &&
                getCode().equals(course.getCode()) &&
                getInstructor().equals(course.getInstructor()) &&
                getGym().equals(course.getGym());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getCode(), getInstructor() , getGym().getId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + getId() + '\'' +
                ", version='" + getVersion() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", instructor='" + instructor + '\'' +
                ", gym=" + gym +
                '}';
    }
}
