package ma.agence.voyage.service;

import ma.agence.voyage.entity.Destination;
import ma.agence.voyage.entity.Facture;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FactureService {
    public Facture ajouterFacture(Facture facture);

    public Facture modifierFacture(Facture facture);

    public boolean supprimerFacture(int id);

    public List<Facture> listFacture();
    public Page<Facture> allFacturePages(int pagenumber, int pagesize  );

}
