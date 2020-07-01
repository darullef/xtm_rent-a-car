package pl.darullef.xtm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.darullef.xtm.Model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findById(UUID id);

    List<Client> findAll();
}
