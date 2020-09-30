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
	@Size(max=50)
	@Column(name="NAME")
	private String name;
	
	@NotBlank
	@Size(max=400)
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotBlank
	@Size(max=50)
	@Column(name="CODE")
	private String code;
	
	@OneToMany(mappedBy="course")
	private Set<FeedbackCourse> feedbackCourse;
	
	@OneToMany(mappedBy="course")
	private Set<FavoriteCourse> favoriteCourse;
	
	@ManyToOne
	private Gym gym;

	public Course(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 400) String description, @NotBlank @Size(max = 50) String code, Set<FeedbackCourse> feedbackCourse, Set<FavoriteCourse> favoriteCourse, Gym gym) {
		this.name = name;
		this.description = description;
		this.code = code;
		this.feedbackCourse = feedbackCourse;
		this.favoriteCourse = favoriteCourse;
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

	public Set<FeedbackCourse> getFeedbackCourse() {
		return feedbackCourse;
	}

	public void setFeedbackCourse(Set<FeedbackCourse> feedbackCourse) {
		this.feedbackCourse = feedbackCourse;
	}

	public Set<FavoriteCourse> getFavoriteCourse() {
		return favoriteCourse;
	}

	public void setFavoriteCourse(Set<FavoriteCourse> favoriteCourse) {
		this.favoriteCourse = favoriteCourse;
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
				getDescription().equals(course.getDescription()) &&
				getCode().equals(course.getCode()) &&
				getFeedbackCourse().equals(course.getFeedbackCourse()) &&
				getFavoriteCourse().equals(course.getFavoriteCourse()) &&
				getGym().equals(course.getGym());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(),getCode());
	}

	@Override
	public String toString() {
		return "Course{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", code='" + code + '\'' +
				", feedbackCourse=" + feedbackCourse +
				", favoriteCourse=" + favoriteCourse +
				", gym=" + gym +
				'}';
	}
}
