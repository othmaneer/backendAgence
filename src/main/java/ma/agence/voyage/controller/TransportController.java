package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Hotel;
import ma.agence.voyage.entity.Transport;
import ma.agence.voyage.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transport")
@CrossOrigin(origins = "http://localhost:4200/")
public class TransportController {
    @Autowired
    TransportService transportService;

    @PostMapping("/ajouter")
    public ResponseEntity<Transport> ajouterTransport(@RequestBody Transport transport)
    {
        Transport enregistrerTransport = transportService.ajouterTransport(transport);
        return ResponseEntity.ok(enregistrerTransport);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Transport> modifierTransport(@RequestBody Transport Transport)
    {
        Transport enregistrerTransport = transportService.modifierTransport(Transport);
        if (enregistrerTransport != null)
        {
            return ResponseEntity.ok(enregistrerTransport);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerTransport(@PathVariable("id") int id)
    {
        boolean transport = transportService.supprimerTransport(id);
        if (transport)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Transport> listeEncadrant()
    {
        return transportService.listTransport();
    }

    @GetMapping("/all/{pagenumber}/{pagesize}")
    public Page<Transport> transportPage(@PathVariable int pagenumber, @PathVariable int pagesize)
    {
        return transportService.allTransportsPaginations(pagenumber, pagesize);

    }

}
