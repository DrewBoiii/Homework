package example.drew.homework.constraint.validator;

import example.drew.homework.constraint.anotation.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstField;
    private String secondField;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object firstName = BeanUtils.getProperty(value, this.firstField);
            final Object secondName = BeanUtils.getProperty(value, this.secondField);

            return firstName == null && secondName == null || firstName != null && firstName.equals(secondName);
        } catch (final Exception ignore) {}

        return true;
    }

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstField = constraintAnnotation.first();
        this.secondField = constraintAnnotation.second();
    }
}
