package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FactureService {
    public Facture ajouterFacture(Facture facture);

    public Facture modifierFacture(Facture facture);

    public boolean supprimerFacture(int id);

    public List<Facture> listFacture();

    public Facture findFacture(int id);
    public Page<Facture> allFacturePages(int pagenumber, int pagesize  );

    public double totalFactureBrut();
    public double totalFactureNet();

}
