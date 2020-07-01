package pl.darullef.xtm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.darullef.xtm.Model.Car;
import pl.darullef.xtm.Model.Client;
import pl.darullef.xtm.Model.Rent;
import pl.darullef.xtm.Repository.CarRepository;
import pl.darullef.xtm.Repository.ClientRepository;
import pl.darullef.xtm.Repository.RentRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CarRepository carRepository;

    public Rent createRent(Date stat_rent, Date end_rent, UUID client_id, UUID car_id) {
        if (checkRentIsPossible(car_id, stat_rent, end_rent)) {
            Rent rent = new Rent();
            Client client = clientRepository.findById(client_id).get();
            Car car = carRepository.findById(car_id).get();
            rent.setStart(stat_rent);
            rent.setEnd(end_rent);
            rent.setClient(client);
            rent.setCar(car);
            rentRepository.save(rent);
            return rent;
        } else throw new NullPointerException();
    }

    public List<Rent> getAllRentsForCar(UUID car_id) {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getCar().getCar_id().equals(car_id))
                .collect(Collectors.toList());
    }

    public Boolean checkRentIsPossible(UUID car_id, Date start_rent, Date end_rent) {
        List<Rent> rents = getAllRentsForCar(car_id);
        if (!rents.isEmpty()) {
            for (Rent r : rents) {
                if (isOverlapping(start_rent, end_rent, r.getStart(), r.getEnd())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean isOverlapping(Date start_1, Date end_1, Date start_2, Date end_2) {
        return !start_1.after(end_2) && !start_2.after(end_1);
    }

    public List<Rent> getAllRents() {
        if (rentRepository.findAll().isEmpty()) {
            throw new NoSuchElementException();
        } else return rentRepository.findAll();
    }

    public Rent getRentById(UUID uuid) {
        return rentRepository.findById(uuid).get();
    }

    public void updateRent(Rent oldRent, Date start, Date end, UUID client_id, UUID car_id) {
        Client client = clientRepository.findById(client_id).get();
        Car car = carRepository.findById(car_id).get();
        oldRent.setStart(start);
        oldRent.setEnd(end);
        oldRent.setClient(client);
        oldRent.setCar(car);
        rentRepository.save(oldRent);
    }

    public void deleteRent(UUID uuid) {
        rentRepository.delete(rentRepository.findById(uuid).get());
    }
}
