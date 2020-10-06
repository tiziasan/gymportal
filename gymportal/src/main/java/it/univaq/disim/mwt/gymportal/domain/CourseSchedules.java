package it.univaq.disim.mwt.gymportal.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "courseschedules")
public class CourseSchedules extends BaseEntity implements Serializable {

    @NotBlank
    @Size(max = 15)
    @Column(name = "DAY")
    private String day;

    @Column(name = "START")
    private LocalTime start;

    @Column(name = "END")
    private LocalTime end;

    @ManyToOne
    private Course course;

    public CourseSchedules() {
    }

    public CourseSchedules(@NotBlank @Size(max = 15) String day, @NotBlank LocalTime start, @NotBlank LocalTime end, Course course) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.course = course;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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
        if (o == null || getClass() != o.getClass()) return false;
        CourseSchedules that = (CourseSchedules) o;
        return day.equals(that.day) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                course.equals(that.course);
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
