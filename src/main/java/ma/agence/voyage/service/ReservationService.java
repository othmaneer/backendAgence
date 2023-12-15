package ma.agence.voyage.service;

import ma.agence.voyage.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReservationService {
    public Reservation ajouterReservation(Reservation reservation);

    public Reservation modifierReservation(Reservation reservation);

    public boolean supprimerReservation(int id);

    public List<Reservation> listReservation();
}