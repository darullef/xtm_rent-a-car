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

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CarRepository carRepository;

    public Rent createRent(Date stat_rent, Date end_rent, UUID client_id, UUID car_id) {
        Rent rent = new Rent();
        Client client = clientRepository.findById(client_id).get();
        Car car = carRepository.findById(car_id).get();
        rent.setStart(stat_rent);
        rent.setEnd(end_rent);
        rent.setClient(client);
        rent.setCar(car);
        rentRepository.save(rent);
        return rent;
    }

    public List<Rent> getAllRents() {
        if(rentRepository.findAll().isEmpty()) {
            throw new NoSuchElementException();
        }
        else return rentRepository.findAll();
    }

    public Rent getRentById(UUID uuid) {
        return rentRepository.findById(uuid).get();
    }

    public void updateRent(Rent oldRent, Rent newRent) {
        oldRent.setStart(newRent.getStart());
        oldRent.setEnd(newRent.getEnd());
        oldRent.setCar(newRent.getCar());
        oldRent.setClient(newRent.getClient());
        rentRepository.save(oldRent);
    }

    public void deleteRent(UUID uuid) {
        rentRepository.delete(rentRepository.findById(uuid).get());
    }
}
