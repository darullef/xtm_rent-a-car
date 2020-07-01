package pl.darullef.xtm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.darullef.xtm.Model.Rent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentRepository extends JpaRepository<Rent, UUID> {
    Optional<Rent> findById(UUID id);

    List<Rent> findAll();
}
