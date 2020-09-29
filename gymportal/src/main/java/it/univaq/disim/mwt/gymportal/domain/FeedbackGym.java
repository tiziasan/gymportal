package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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
	private Customer user;
	
	@ManyToOne
	private Gym gym;

	public FeedbackGym(@NotBlank @Size(max = 150) String feed, @NotNull @Min(0) @Max(5) int rating, Customer user, Gym gym) {
		this.feed = feed;
		this.rating = rating;
		this.user = user;
		this.gym = gym;
	}

	public FeedbackGym() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FeedbackGym)) return false;
		FeedbackGym that = (FeedbackGym) o;
		return getRating() == that.getRating() &&
				getFeed().equals(that.getFeed()) &&
				getUser().equals(that.getUser()) &&
				getGym().equals(that.getGym());
	}

	@Override
	public int hashCode() {
		return Objects.hash( getUser(), getGym());
	}

	@Override
	public String toString() {
		return "FeedbackGym{" +
				"feed='" + feed + '\'' +
				", rating=" + rating +
				", user=" + user +
				", gym=" + gym +
				'}';
	}
}
