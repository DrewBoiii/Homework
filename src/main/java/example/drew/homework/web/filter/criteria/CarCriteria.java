package example.drew.homework.web.filter.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarCriteria {

    public static final String DEFAULT_SORTING_CRITERIA = "createdAt";

    private String brand;

    private String model;

    private String sort = DEFAULT_SORTING_CRITERIA;

}
