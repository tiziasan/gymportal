package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Entity
@Table(name = "favoritegym", uniqueConstraints = @UniqueConstraint(columnNames={"gym_gym_id","user_user_id"}))

public class FavoriteGym {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Gym gym;

}
