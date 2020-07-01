package pl.darullef.xtm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.darullef.xtm.Model.Client;
import pl.darullef.xtm.Service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> addClient(@Valid @RequestBody Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clientList = clientService.getAllClients();
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<?> getClientById(@PathVariable("uuid") UUID uuid) {
        try {
            Client client = clientService.getClientById(uuid);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> updateClient(@PathVariable("uuid") UUID uuid, @RequestBody Client newClient) {
        try {
            Client oldClient = clientService.getClientById(uuid);
            clientService.updateClient(oldClient, newClient);
            return new ResponseEntity<>(oldClient, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteClient(@PathVariable("uuid") UUID uuid) {
        try {
            clientService.deleteClient(uuid);
            return new ResponseEntity<>("Client with id " + uuid + " has been deleted", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
