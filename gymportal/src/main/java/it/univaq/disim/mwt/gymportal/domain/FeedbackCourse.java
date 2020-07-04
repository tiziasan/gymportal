package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "feedbackcourse", uniqueConstraints = @UniqueConstraint(columnNames={"course_id","user_user_id"}))
public class FeedbackCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=500)
	@Column(name="FEED")
	private String feed;
	
	@NotNull
	@Min(0)
	@Max(5)
	@Column(name="RATING")
	private int rating;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Course course;

}
