package ma.agence.voyage.service;

import ma.agence.voyage.entity.Client;

import java.util.List;

public interface ClientService {
    public Client ajouterClient(Client client);

    public Client modifierClient(Client client);

    public boolean supprimerClient(int id);

    public List<Client> listClient();
}
