package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByPerson_Username(String username);

    @Query("select c from Car c order by c.createdAT desc")
    List<Car> findCarsOrderByCreatedAT();

}
