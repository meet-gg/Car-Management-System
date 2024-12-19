package net.car_management.Car.Management.System.Annonation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class ValidYearValidator implements ConstraintValidator<ValidYear, Year> {
    @Override
    public boolean isValid(Year value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        int currentYear = Year.now().getValue();

        if (value.getValue() > currentYear) {
            return false;
        }
        return true;
    }
}
