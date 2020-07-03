package pl.darullef.xtm.Service;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.darullef.xtm.Model.Car;
import pl.darullef.xtm.Repository.CarRepository;
import pl.darullef.xtm.Repository.RentRepository;

import javax.xml.crypto.NoSuchMechanismException;
import java.sql.SQLException;
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

    public void deleteCar(UUID uuid) {
        carRepository.delete(carRepository.findById(uuid).get());
    }
}
