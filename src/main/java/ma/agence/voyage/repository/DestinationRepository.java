package ma.agence.voyage.repository;

import jakarta.persistence.Entity;
import ma.agence.voyage.entity.Destination;
import ma.agence.voyage.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
