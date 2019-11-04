package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            value = "SELECT * FROM car WHERE " +
                    "setweight(to_tsvector(brand),'A') || " +
                    "setweight(to_tsvector(model),'B') " +
                    "@@ plainto_tsquery( ?1 )" +
                    "ORDER BY ts_rank(" +
                    "setweight(to_tsvector(brand),'A') || " +
                    "setweight(to_tsvector(model),'B'), " +
                    "plainto_tsquery( ?1 )" +
                    ") DESC",
            nativeQuery = true
    )
    List<Car> findCarsBySearchCriteria(String searchCriteria);

    List<Car> findCarsByPerson_Username(String username);

    @Query("select c from Car c order by c.createdAT desc")
    List<Car> findCarsOrderByCreatedAT();

}
