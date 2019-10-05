package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findCarsByBrand(String brand);

}
