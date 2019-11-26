package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.Favorites;
import example.drew.homework.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    void deleteByPersonAndCar(User person, Car car);

    Optional<Favorites> findByPersonAndCar(User person, Car car);

}
