package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Destination;
import ma.agence.voyage.entity.Facture;
import ma.agence.voyage.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
@CrossOrigin(origins = "*")
public class FactureController {
    @Autowired
    FactureService factureService;

    @PostMapping("/ajouter")
    public ResponseEntity<Facture> ajouterFacture(@RequestBody Facture client)
    {
        Facture enregistrerFacture = factureService.ajouterFacture(client);
        return ResponseEntity.ok(enregistrerFacture);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Facture> modifierFacture(@RequestBody Facture Facture)
    {
        Facture enregistrerFacture = factureService.modifierFacture(Facture);
        if (enregistrerFacture != null)
        {
            return ResponseEntity.ok(enregistrerFacture);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerFacture(@PathVariable("id") int id)
    {
        boolean client = factureService.supprimerFacture(id);
        if (client)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Facture> listeEncadrant()
    {
        return factureService.listFacture();
    }

    @GetMapping("/all/{pagenumber}/{pagesize}")
    public Page<Facture> clientsPages(@PathVariable int pagenumber, @PathVariable int pagesize)
    {
        return factureService.allFacturePages(pagenumber, pagesize);

    }

}
