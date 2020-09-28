package it.univaq.disim.mwt.gymportal.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


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

	@NotBlank
	@Column(name="DATE")
	private DateFormat date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);

	@NotBlank
	@Size(max=50)
	@Column(name="INSTRUCTOR")
	private String instructor;

	@OneToMany(mappedBy="course")
	private List<FeedbackCourse> feedbackCourse;

	@OneToMany(mappedBy="course")
	private List<FavoriteCourse> favoriteCourse;

	@ManyToOne
	private Gym gym;

	public String getName(){
		return name;
	}

	public void setName(String name){
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

	public List<FeedbackCourse> getFeedbackCourse() {
		return feedbackCourse;
	}

	public void setFeedbackCourse(List<FeedbackCourse> feedbackCourse) {
		this.feedbackCourse = feedbackCourse;
	}

	public List<FavoriteCourse> getFavoriteCourse() {
		return favoriteCourse;
	}

	public void setFavoriteCourse(List<FavoriteCourse> favoriteCourse) {
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
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return Objects.equals(name, course.name) &&
				Objects.equals(description, course.description) &&
				Objects.equals(code, course.code) &&
				Objects.equals(date, course.date) &&
				Objects.equals(instructor, course.instructor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, code, date, instructor);
	}
}
