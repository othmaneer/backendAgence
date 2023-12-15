package ma.agence.voyage.service;

import ma.agence.voyage.entity.Transport;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransportService {
    public Transport ajouterTransport(Transport transport);

    public Transport modifierTransport(Transport transport);

    public boolean supprimerTransport(int id);

    public List<Transport> listTransport();
}
