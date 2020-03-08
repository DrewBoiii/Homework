package example.drew.homework.constraint.validator;

import example.drew.homework.constraint.anotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,128}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }
}
