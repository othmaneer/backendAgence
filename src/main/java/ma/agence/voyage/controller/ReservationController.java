package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Reservation;
import ma.agence.voyage.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping("/ajouter")
    public ResponseEntity<Reservation> ajouterReservation(@RequestBody Reservation reservation)
    {
        Reservation enregistrerReservation = reservationService.ajouterReservation(reservation);
        return ResponseEntity.ok(enregistrerReservation);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Reservation> modifierReservation(@RequestBody Reservation Reservation)
    {
        Reservation enregistrerReservation = reservationService.modifierReservation(Reservation);
        if (enregistrerReservation != null)
        {
            return ResponseEntity.ok(enregistrerReservation);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerReservation(@PathVariable("id") int id)
    {
        boolean reservation = reservationService.supprimerReservation(id);
        if (reservation)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Reservation> listeEncadrant()
    {
        return reservationService.listReservation();
    }

}
