package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Client;
import ma.agence.voyage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/ajouter")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client)
    {
        Client enregistrerClient = clientService.ajouterClient(client);
        return ResponseEntity.ok(enregistrerClient);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Client> modifierClient(@RequestBody Client Client)
    {
        Client enregistrerClient = clientService.modifierClient(Client);
        if (enregistrerClient != null)
        {
            return ResponseEntity.ok(enregistrerClient);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerClient(@PathVariable("id") int id)
    {
        boolean client = clientService.supprimerClient(id);
        if (client)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Client> listeClient()
    {
        return clientService.listClient();
    }

    @GetMapping("/all/{pagenumber}/{pagesize}")
    public Page<Client> clientsPages(@PathVariable int pagenumber, @PathVariable int pagesize)
    {
        return clientService.AllClitentsPaginations(pagenumber, pagesize);

    }


}
