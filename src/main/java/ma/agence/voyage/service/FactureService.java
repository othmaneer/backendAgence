package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FactureService {
    public Facture ajouterFacture(Facture facture);

    public Facture modifierFacture(Facture facture);

    public boolean supprimerFacture(int id);

    public List<Facture> listFacture();
}
