package ma.agence.voyage.service;

import ma.agence.voyage.entity.Reservation;
import ma.agence.voyage.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReservationServiceImpl implements ReservationService{
    
    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        Optional<Reservation> resultat= reservationRepository.findById(reservation.getIdReservation());
        if (resultat.isPresent())
        {
            Reservation reservation1 = resultat.get();
            reservation1.setClient(reservation.getClient());
            reservation1.setDate_debut(reservation.getDate_debut());
            reservation1.setDate_fin(reservation.getDate_fin());
            reservation1.setDestination(reservation.getDestination());
            reservation1.setHotel(reservation.getHotel());
            reservation1.setTransports(reservation.getTransports());
            reservation1.setStatus(reservation.getStatus());
            reservation1.setFacture(reservation.getFacture());

            return reservationRepository.save(reservation1);
        }

        return null;
    }

    @Override
    public boolean supprimerReservation(int id) {
        Optional<Reservation> resultat= reservationRepository.findById(id);
        if (resultat.isPresent())
        {
            reservationRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Reservation> listReservation() {
        return reservationRepository.findAll();
    }
}
