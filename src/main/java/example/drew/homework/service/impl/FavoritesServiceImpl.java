package example.drew.homework.service.impl;

import example.drew.homework.persistence.dao.FavoritesRepository;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.Favorites;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.FavoritesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    private FavoritesRepository favoritesRepository;

    public FavoritesServiceImpl(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Override
    public void save(Favorites favorites) {
        favoritesRepository.save(favorites);
    }

    @Override
    public void deleteByPersonAndCar(User person, Car car) {
        favoritesRepository.deleteByPersonAndCar(person, car);
    }

    @Override
    public Optional<Favorites> findByPersonAndCar(User person, Car car) {
        return favoritesRepository.findByPersonAndCar(person, car);
    }
}
