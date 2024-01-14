package ma.agence.voyage.repository;

import ma.agence.voyage.entity.Facture;
import ma.agence.voyage.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {
    @Query("SELECT f FROM Facture f WHERE f.reservation.idReservation = :id")
    Optional<Facture> findFactureByReservationId(@Param("id") int id);

    @Query
      ("SELECT SUM(f.total) FROM Facture f JOIN Reservation r ON f.reservation.idReservation = r.idReservation")
    Double sumTotalByReservation();

    @Query
            ("SELECT SUM(f.total) FROM Facture f JOIN Reservation r ON f.reservation.idReservation = r.idReservation WHERE r.status = 'Payée'")
    Double sumTotalByReservationAndStatus();

}
