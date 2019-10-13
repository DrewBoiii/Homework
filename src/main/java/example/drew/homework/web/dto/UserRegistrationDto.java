package example.drew.homework.web.dto;

import example.drew.homework.constraint.anotation.FieldMatch;
import example.drew.homework.constraint.anotation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must be matched")
})
@Data
public class UserRegistrationDto {

    @Size(min = 2, max = 30, message = "the username length should be between 2 and 30")
    @NotBlank(message = "should be blanked")
    private String username;

    @ValidEmail
    @Size(max = 50, message = "the username length should be between 2 and 50")
    @NotBlank(message = "should be blanked")
    private String email;

    @Size(min = 6, max = 20, message = "the password length should be between 6 and 20")
    @NotBlank(message = "should be blanked")
    private String password;

    @NotBlank(message = "should be blanked")
    private String confirmPassword;

}
