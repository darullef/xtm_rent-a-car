package pl.darullef.xtm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.darullef.xtm.Model.Client;
import pl.darullef.xtm.Repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        if(clientRepository.findAll().isEmpty()) {
            throw new NoSuchElementException();
        }
        else return clientRepository.findAll();
    }

    public Client getClientById(UUID uuid) {
        return clientRepository.findById(uuid).get();
    }

    public void updateClient(Client oldClient, Client newClient) {
        oldClient.setName(newClient.getName());
        clientRepository.save(oldClient);
    }

    public void deleteClient(UUID uuid) {
        clientRepository.delete(clientRepository.findById(uuid).get());
    }
}
