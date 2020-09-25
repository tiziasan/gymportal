package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "feedbackgym", uniqueConstraints = @UniqueConstraint(columnNames={"gym_id","user_id"}))
public class FeedbackGym extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(max=150)
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
	private Gym gym;
}
