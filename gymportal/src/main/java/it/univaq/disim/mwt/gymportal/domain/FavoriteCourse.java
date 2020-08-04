package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "favoritecourse", uniqueConstraints = @UniqueConstraint(columnNames={"course_id","user_id"}))
public class FavoriteCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Course course;

}
