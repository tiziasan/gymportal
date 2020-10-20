package it.univaq.disim.mwt.gymportal.domain;

import it.univaq.disim.mwt.gymportal.configuration.LessEgualsThen;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "courseschedules")
public class CourseSchedules extends BaseEntity implements Serializable {

    @NotBlank
    @Column(name = "DAY")
    private DayOfWeek day;

    @NotBlank
    @Column(name = "START")
    private LocalTime start;

    @NotBlank
    @Column(name = "END")
    private LocalTime end;

    @ManyToOne
    private Course course;

    public CourseSchedules() {
    }

    public CourseSchedules(String day, LocalTime start, LocalTime end, Course course) {
        this.day = DayOfWeek.valueOf(day);
        this.start = start;
        this.end = end;
        this.course = course;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseSchedules)) return false;
        CourseSchedules that = (CourseSchedules) o;
        return getDay() == that.getDay() &&
                getStart().equals(that.getStart()) &&
                getEnd().equals(that.getEnd()) &&
                getCourse().equals(that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getStart(), getEnd(), getCourse().getId());
    }

    @Override
    public String toString() {
        return "CourseSchedules{" +
                "day='" + day + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", course=" + course +
                '}';
    }

}
