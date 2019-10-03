package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {



}
