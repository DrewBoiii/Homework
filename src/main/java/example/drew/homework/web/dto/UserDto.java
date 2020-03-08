package example.drew.homework.web.dto;

import example.drew.homework.constraint.anotation.FieldMatch;
import example.drew.homework.constraint.anotation.ValidEmail;
import example.drew.homework.constraint.anotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "password fields must be matched")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Size(min = 4, max = 30, message = "username length should be between 5 and 30")
    @NotBlank(message = "should be blanked")
    private String username;

    @ValidEmail(message = "email is not valid")
    @NotBlank(message = "should be blanked")
    private String email;

    @ValidPassword(message = "the password should have at least " +
            "one upper case, one lower case, one special char and length should be 10 characters long")
    @NotBlank(message = "should be blanked")
    private String password;

    @ValidPassword(message = "the password should have at least " +
            "one upper case, one lower case, one special char and length should be 10 characters long")
    @NotBlank(message = "should be blanked")
    private String confirmPassword;

}
