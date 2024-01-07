package ma.agence.voyage.service;

import ma.agence.voyage.entity.Client;
import ma.agence.voyage.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
public interface DestinationService {
    public Destination ajouterDestination(Destination destination);

    public Destination modifierDestination(Destination destination);

    public boolean supprimerDestination(int id);

    public List<Destination> listDestination();

    public Page<Destination> allDestinationsPaginations(int pagenumber, int pagesize  );

}
