package ma.agence.voyage.service;

import ma.agence.voyage.entity.Client;
import ma.agence.voyage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Client client) {
        Optional<Client> resultat= clientRepository.findById(client.getIdCient());
        if (resultat.isPresent())
        {
            Client client1 = resultat.get();
            client1.setNom(client.getNom());
            client1.setPrenom(client.getPrenom());
            client1.setTel(client.getTel());
            client1.setAdresse(client.getAdresse());
            client1.setEmail(client.getEmail());
            return clientRepository.save(client1);
        }

        return null;
    }

    @Override
    public boolean supprimerClient(int id) {
        Optional<Client> resultat= clientRepository.findById(id);
        if (resultat.isPresent())
        {
            clientRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Client> listClient() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> AllClitentsPaginations(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Long nbrClient() {
        return clientRepository.count();
    }
}
