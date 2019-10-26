package example.drew.homework.web.dto;

import example.drew.homework.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;

    @Size(min = 2, max = 50, message = "the brand length should be between 2 and 50")
    @NotBlank(message = "should be blanked")
    private String brand;

    @Size(min = 2, max = 30, message = "the model length should be between 2 and 30")
    @NotBlank(message = "should be blanked")
    private String model;

    @Positive(message = "should be a positive number")
    @NotBlank(message = "should be blanked")
    private Long kilometers;

    @PastOrPresent
    @NotBlank(message = "should be blanked")
    private Date build;

    private User person;

}
