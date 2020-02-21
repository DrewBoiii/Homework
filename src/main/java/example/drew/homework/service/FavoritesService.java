package example.drew.homework.service;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.Favorites;
import example.drew.homework.persistence.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FavoritesService {

    void save(Favorites favorites);

    @Transactional
    void deleteByPerson_IdAndCar_Id(Long personId, Long carId);

    Optional<Favorites> findByPersonAndCar(User person, Car car);

}
