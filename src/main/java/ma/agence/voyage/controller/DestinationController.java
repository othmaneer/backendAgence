package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Destination;
import ma.agence.voyage.service.DestinationService;
import ma.agence.voyage.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
@CrossOrigin(origins = "*")
public class DestinationController {
    @Autowired
    DestinationService destinationService;

    @PostMapping("/ajouter")
    public ResponseEntity<Destination> ajouterDestination(@RequestBody Destination destination)
    {
        Destination enregistrerDestination = destinationService.ajouterDestination(destination);
        return ResponseEntity.ok(enregistrerDestination);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Destination> modifierDestination(@RequestBody Destination Destination)
    {
        Destination enregistrerDestination = destinationService.modifierDestination(Destination);
        if (enregistrerDestination != null)
        {
            return ResponseEntity.ok(enregistrerDestination);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerDestination(@PathVariable("id") int id)
    {
        boolean destination = destinationService.supprimerDestination(id);
        if (destination)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Destination> listeDestination()
    {
        return destinationService.listDestination();
    }

}
