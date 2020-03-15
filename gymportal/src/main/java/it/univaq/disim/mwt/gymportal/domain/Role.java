package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=50)
	@Column(name="NAME")
	private String name;
	
	@NotBlank
	@Size(max=200)
	@Column(name="DESCRIPTION")
	private String description;
	
	@OneToOne
	private User user;

}

