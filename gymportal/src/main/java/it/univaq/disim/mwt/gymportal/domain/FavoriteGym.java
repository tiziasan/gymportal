package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favoritegym", uniqueConstraints = @UniqueConstraint(columnNames = {"gym_id", "user_id"}))

public class FavoriteGym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer user;

    @ManyToOne
    private Gym gym;

    public FavoriteGym(long id, Customer user, Gym gym) {
        this.id = id;
        this.user = user;
        this.gym = gym;
    }

    public FavoriteGym() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (!(o instanceof FavoriteGym)) return false;
        FavoriteGym that = (FavoriteGym) o;
        return getId() == that.getId() &&
                getUser().equals(that.getUser()) &&
                getGym().equals(that.getGym());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getGym());
    }

    @Override
    public String toString() {
        return "FavoriteGym{" +
                "id=" + id +
                ", user=" + user +
                ", gym=" + gym +
                '}';
    }
}
