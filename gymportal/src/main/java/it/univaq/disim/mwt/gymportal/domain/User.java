package it.univaq.disim.mwt.gymportal.domain;

import java.util.Date;
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
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=50)
	@Column(name="NAME")
	private String name;
	
	@NotBlank
	@Size(max=50)
	@Column(name="SURNAME")
	private String surname;
	
	@NotBlank
	@Size(max=100)
	@Column(name="EMAIL")
	private String email;
	
	@NotBlank
	@Column(name="BIRTHDAY")
	private Date birthday;
	
	@NotBlank
	@Size(max=20)
	@Column(name="CODEFISCALE")
	private String codeFiscale;
	
	@NotBlank
	@Size(max=20)
	@Column(name="PHONE")
	private String phone;
	
	@NotBlank
	@Size(max=50)
	@Column(name="USERNAME")
	private String username;
	
	@NotBlank
	@Size(max=50)
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Feedback> feedback;
	
	@OneToMany(mappedBy="user")
	private List<FavoriteGym> favoriteGym;
	
	@OneToMany(mappedBy="user")
	private List<FavoriteCourse> favoriteCourse;
	
}