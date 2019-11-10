package example.drew.homework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResponseDto<T> {

    private String status;
    // TODO: 10.11.2019 add error message to controllers
//    private String message;
    private T data;

}
