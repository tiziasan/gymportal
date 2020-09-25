package it.univaq.disim.mwt.gymportal.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "username", nullable = false, length = 255, unique = true)
	@Length(min = 5, message = "*Your user name must have at least 5 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String username;
	
	@Column(name = "email", nullable = false, length = 255, unique = true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "lastname")
	@NotEmpty(message = "*Please provide your last name")
	private String lastname;

	@Column(name = "role", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;


 }
