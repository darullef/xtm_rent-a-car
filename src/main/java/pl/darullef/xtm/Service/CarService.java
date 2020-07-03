package pl.darullef.xtm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.darullef.xtm.Model.Car;
import pl.darullef.xtm.Repository.CarRepository;
import pl.darullef.xtm.Repository.RentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentRepository rentRepository;

    public void createCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        if (carRepository.findAll().isEmpty()) {
            throw new NoSuchElementException();
        } else return carRepository.findAll();
    }

    public Car getCarById(UUID uuid) {
        return carRepository.findById(uuid).get();
    }

    public void updateCar(Car oldCar, Car newCar) {
        oldCar.setMade(newCar.getMade());
        oldCar.setModel(newCar.getModel());
        carRepository.save(oldCar);
    }

    public int deleteCar(UUID uuid) {
        Car car = carRepository.findById(uuid).get();
        int rents = rentRepository.findAllByCar(car).size();
        carRepository.delete(car);
        return rents;
    }
}
