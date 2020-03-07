package it.univaq.disim.mwt.gymportal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "course")

public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private List<Feedback> feedback;
	
	@OneToMany(mappedBy="course")
	private List<FavoriteCourse> favoriteCourse;

}
