package ma.agence.voyage.repository;

import ma.agence.voyage.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    long countByStatus(String status);

    @Query("SELECT r.status, COUNT(r) FROM Reservation r GROUP BY r.status")
    List<Object[]> countByStatusGrouped();

    @Query(value = "SELECT MONTHNAME(p.date_debut) AS Mois, SUM(total) AS Total " +
            "FROM agence.facture f, agence.reservation p " +
            "WHERE f.reservation_id_reservation = p.id_reservation " +
            "GROUP BY Mois " +
            "ORDER BY FIELD(Mois, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December')",
            nativeQuery = true)
       List<Object[]> sumTotalByMonth();

    @Query( value = "SELECT MONTHNAME(p.date_debut) AS Mois, SUM(total) AS Total FROM agence.facture f, agence.reservation p " +
            "WHERE f.reservation_id_reservation = p.id_reservation and p.status='Payée' GROUP BY Mois " +
            "ORDER BY FIELD(Mois, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December')",
            nativeQuery = true)
    List<Object[]> sumTotalByMonthNet();


    @Query("SELECT r.status, count(r.status) FROM Reservation r GROUP BY r.status")
    List<Object[]> nombreResParType();



}
