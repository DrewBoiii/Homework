package example.drew.homework.constraint.validator;

import example.drew.homework.constraint.anotation.ValidDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    private final static String DATE_FORMAT_PATTERN = "^(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9]).[0-9]{4}$";

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(DATE_FORMAT_PATTERN);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {

    }
}
