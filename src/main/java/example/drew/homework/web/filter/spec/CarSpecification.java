package example.drew.homework.web.filter.spec;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.web.filter.criteria.CarCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSpecification implements Specification<Car> {

    private CarCriteria carCriteria;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (carCriteria.getBrand() != null && !carCriteria.getBrand().isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.upper(root.get("brand")),
                            carCriteria.getBrand().toUpperCase()
                    )
            );
        }

        if (carCriteria.getModel() != null && !carCriteria.getModel().isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.upper(root.get("model")),
                            carCriteria.getModel().toUpperCase()
                    )
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
