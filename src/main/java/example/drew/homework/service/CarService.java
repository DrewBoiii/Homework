package example.drew.homework.service;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.util.RoleConstants;
import example.drew.homework.web.dto.CarDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getCars();
    List<Car> getCarsBySearchCriteria(String searchCriteria);
    List<Car> getCarsBySpecification(Specification<Car> specification);
    List<Car> getCarsByUsername(String username);

    Optional<Car> getCarById(Long id) throws CarNotFoundException;

    void saveCar(CarDto car);

    @Secured(RoleConstants.ROLE_MODERATOR)
    void deleteCarById(Long id);

    @Secured(RoleConstants.ROLE_MODERATOR)
    void updateCar(CarDto car);

}
