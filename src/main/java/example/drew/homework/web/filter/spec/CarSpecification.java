package example.drew.homework.web.filter.spec;

import example.drew.homework.persistence.model.Car;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarSpecification implements Specification<Car> {

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
