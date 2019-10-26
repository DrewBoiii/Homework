package example.drew.homework.web.dto;

import example.drew.homework.constraint.anotation.FieldMatch;
import example.drew.homework.constraint.anotation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must be matched")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Size(min = 2, max = 30, message = "the username length should be between 2 and 30")
    @NotBlank(message = "should be blanked")
    private String username;

    @ValidEmail
    @NotBlank(message = "should be blanked")
    private String email;

    @Size(min = 6, max = 20, message = "the password length should be between 6 and 20")
    @NotBlank(message = "should be blanked")
    private String password;

    @Size(min = 6, max = 20, message = "the password length should be between 6 and 20")
    @NotBlank(message = "should be blanked")
    private String confirmPassword;

}
