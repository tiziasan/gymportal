package it.univaq.disim.mwt.gymportal.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

	public User() {
	}

	public User(String username, String email, String password, String name, String lastname, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
	}

	public User(long id, String username, String email, String password, String name, String lastname, Role role) {
		this.setId(id);
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getUsername().equals(user.getUsername()) &&
				getEmail().equals(user.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsername(), getEmail());
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + getId() + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", role=" + role +
				'}';
	}
}
