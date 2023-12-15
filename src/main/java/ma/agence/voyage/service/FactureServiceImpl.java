package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import ma.agence.voyage.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FactureServiceImpl implements FactureService{
    
    @Autowired
    FactureRepository factureRepository;
    @Override
    public Facture ajouterFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture modifierFacture(Facture facture) {
        Optional<Facture> resultat= factureRepository.findById(facture.getIdFacture());
        if (resultat.isPresent())
        {
            Facture facture1 = resultat.get();
            facture1.setNom(facture.getNom());
            facture1.setClient(facture.getClient());
            facture1.setTotal(facture.getTotal());
            return factureRepository.save(facture1);
        }

        return null;
    }

    @Override
    public boolean supprimerFacture(int id) {
        Optional<Facture> resultat= factureRepository.findById(id);
        if (resultat.isPresent())
        {
            factureRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Facture> listFacture() {
        return factureRepository.findAll();
    }
}
