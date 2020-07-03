package pl.darullef.xtm.Controller;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.darullef.xtm.Model.Car;
import pl.darullef.xtm.Service.CarService;

import javax.validation.Valid;
import javax.xml.crypto.NoSuchMechanismException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<?> addCar(@Valid @RequestBody Car car) {
        carService.createCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        try {
            List<Car> carsList = carService.getAllCars();
            return new ResponseEntity<>(carsList, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<?> getCarById(@PathVariable UUID uuid) {
        try {
            Car car = carService.getCarById(uuid);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> updateCar(@PathVariable("uuid") UUID uuid, @RequestBody Car newCar) {
        try {
            Car oldCar = carService.getCarById(uuid);
            carService.updateCar(oldCar, newCar);
            return new ResponseEntity<>(oldCar, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteCar(@PathVariable("uuid") UUID uuid) {
        try {
            carService.deleteCar(uuid);
            return new ResponseEntity<>("Car with id " + uuid + " has been deleted", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
