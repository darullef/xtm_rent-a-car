package pl.darullef.xtm.Controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.darullef.xtm.Model.Rent;
import pl.darullef.xtm.Service.RentService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("api/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping
    public ResponseEntity<?> addRent(HttpEntity<String> httpEntity) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(httpEntity.getBody());
        Date rent_start, rent_end;
        UUID client_id, car_id;

        try {
            java.util.Date rent_start_temp = new SimpleDateFormat("dd/MM/yyyy").parse(json.get("rent_start").toString());
            java.util.Date rent_end_temp = new SimpleDateFormat("dd/MM/yyyy").parse(json.get("rent_end").toString());
            rent_start = new Date(rent_start_temp.getTime());
            rent_end = new Date(rent_end_temp.getTime());
        } catch (ParseException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Start & end date required"
            );
        }

        try {
            client_id = UUID.fromString(json.get("client_id").toString());
            car_id = UUID.fromString(json.get("car_id").toString());
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad UUID format"
            );
        }
        // TODO: NoSuchElementException nie jest łapany, gdy uuid jest za krótkie

        try {
            Rent rent = rentService.createRent(rent_start, rent_end, client_id, car_id);
            return new ResponseEntity<>(rent, HttpStatus.CREATED);
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Can not create a rent. Car is taken in this period of time"
            );
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRents() {
        try {
            List<Rent> rentList = rentService.getAllRents();
            return new ResponseEntity<>(rentList, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<?> getRentById(@PathVariable("uuid") UUID uuid) {
        try {
            Rent rent = rentService.getRentById(uuid);
            return new ResponseEntity<>(rent, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> updateRent(@PathVariable("uuid") UUID uuid, @RequestBody Rent newRent) {
        try {
            Rent oldRent = rentService.getRentById(uuid);
            rentService.updateRent(oldRent, newRent);
            return new ResponseEntity<>(oldRent, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteRent(@PathVariable("uuid") UUID uuid) {
        try {
            rentService.deleteRent(uuid);
            return new ResponseEntity<>("Rent with id " + uuid + " has been deleted", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
