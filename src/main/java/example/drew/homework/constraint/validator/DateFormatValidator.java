package example.drew.homework.constraint.validator;

import example.drew.homework.constraint.anotation.ValidDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    private final static String DATE_FORMAT_TEMPLATE = "yyyy/MM/dd";

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_TEMPLATE);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {

    }
}
