package example.drew.homework.constraint.anotation;

import example.drew.homework.constraint.validator.DateFormatValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface ValidDateFormat {

    String message() default "Invalid date format";

}
