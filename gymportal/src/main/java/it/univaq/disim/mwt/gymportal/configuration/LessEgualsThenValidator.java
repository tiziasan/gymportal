package it.univaq.disim.mwt.gymportal.configuration;

import it.univaq.disim.mwt.gymportal.domain.CourseSchedules;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

@Component
public class LessEgualsThenValidator implements ConstraintValidator<LessEgualsThen, CourseSchedules> {

    private LocalTime date;

    @Override
    public void initialize(LessEgualsThen constraintAnnotation) {
        date = LocalTime.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(CourseSchedules value, ConstraintValidatorContext context) {
        boolean valid = true;
        if (value != null) {
            if (!value.getEnd().isAfter(date)) {
                valid = false;
            }
        }
        return valid;
    }

}
