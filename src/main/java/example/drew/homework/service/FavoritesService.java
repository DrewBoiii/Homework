package example.drew.homework.service;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.Favorites;
import example.drew.homework.persistence.model.User;

import java.util.Optional;

public interface FavoritesService {

    void save(Favorites favorites);

    void deleteByPersonAndCar(User person, Car car);

    Optional<Favorites> findByPersonAndCar(User person, Car car);

}
