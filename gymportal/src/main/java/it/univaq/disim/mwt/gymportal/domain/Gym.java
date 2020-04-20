package it.univaq.disim.mwt.gymportal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "gym")
public class Gym {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=50)
	@Column(name="NAME")
	private String name;
	
	@NotBlank
	@Size(max=200)
	@Column(name="ADDRESS")
	private String address;
	
	@NotBlank
	@Size(max=50)
	@Column(name="PROVINCE")
	private String province;
	
	@NotBlank
	@Size(max=50)
	@Column(name="REGION")
	private String region;
	
	@OneToMany(mappedBy="gym")
	private List<FeedbackGym> feedbackGym;
	
	@OneToMany(mappedBy="gym")
	private List<FavoriteGym> favoriteGym;
	
	@OneToMany(mappedBy="gym")
	private List<Course> course;


	@ManyToOne
	private User user;
}
