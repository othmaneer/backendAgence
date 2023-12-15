package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Hotel;
import ma.agence.voyage.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "*")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @PostMapping("/ajouter")
    public ResponseEntity<Hotel> ajouterHotel(@RequestBody Hotel hotel)
    {
        Hotel enregistrerHotel = hotelService.ajouterHotel(hotel);
        return ResponseEntity.ok(enregistrerHotel);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Hotel> modifierHotel(@RequestBody Hotel Hotel)
    {
        Hotel enregistrerHotel = hotelService.modifierHotel(Hotel);
        if (enregistrerHotel != null)
        {
            return ResponseEntity.ok(enregistrerHotel);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerHotel(@PathVariable("id") int id)
    {
        boolean hotel = hotelService.supprimerHotel(id);
        if (hotel)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Hotel> listeEncadrant()
    {
        return hotelService.listHotel();
    }

}
