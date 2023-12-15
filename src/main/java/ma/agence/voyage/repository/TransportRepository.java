package ma.agence.voyage.repository;

import ma.agence.voyage.entity.Reservation;
import ma.agence.voyage.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {
}
