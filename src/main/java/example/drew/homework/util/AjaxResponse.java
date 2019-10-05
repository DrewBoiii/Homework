package example.drew.homework.util;

import example.drew.homework.persistence.model.Car;
import lombok.Data;

import java.util.List;

@Data
public class AjaxResponse {

    private String message;
    private List<Car> result;

}
