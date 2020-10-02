package it.univaq.disim.mwt.gymportal.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "gym")
public class Gym extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @NotBlank
    @Size(max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @NotBlank
    @Size(max = 50)
    @Column(name = "PROVINCE")
    private String province;
    @NotBlank
    @Size(max = 50)
    @Column(name = "REGION")
    private String region;
    @OneToMany(mappedBy = "gym")
    private Set<FeedbackGym> feedbackGyms;
    @OneToMany(mappedBy = "gym")
    private Set<FavoriteGym> favoriteGyms;
    @OneToMany(mappedBy = "gym")
    private Set<Course> courses;
    @ManyToOne
    private Manager user;


    public Gym() {
    }

    public Gym(String name, String address, String province, String region, Manager user) {
        this.name = name;
        this.address = address;
        this.province = province;
        this.region = region;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getUser() {
        return user;
    }

    public void setUser(Manager user) {
        this.user = user;
    }

    public Set<FeedbackGym> getFeedbackGyms() {
        return feedbackGyms;
    }

    public Set<FavoriteGym> getFavoriteGyms() {
        return favoriteGyms;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gym)) return false;
        Gym gym = (Gym) o;
        return getName().equals(gym.getName()) &&
                getAddress().equals(gym.getAddress()) &&
                getProvince().equals(gym.getProvince()) &&
                getRegion().equals(gym.getRegion()) &&
                getUser().equals(gym.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getProvince(), getRegion());
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id='" + getId() + '\'' +
                ", version='" + getVersion() + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", region='" + region + '\'' +
                ", feedbackGym=" + feedbackGyms +
                ", favoriteGym=" + favoriteGyms +
                ", course=" + courses +
                ", user=" + user +
                '}';
    }
}
