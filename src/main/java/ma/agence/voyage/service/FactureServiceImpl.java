package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import ma.agence.voyage.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FactureServiceImpl implements FactureService{
    
    @Autowired
    FactureRepository factureRepository;
    @Override
    public Facture ajouterFacture(Facture facture) {
        System.out.println("save facture");
        facture.setTotal();
        return factureRepository.save(facture);
    }

    @Override
    public Facture modifierFacture(Facture facture) {
        Optional<Facture> resultat= factureRepository.findById(facture.getIdFacture());
        if (resultat.isPresent())
        {
            Facture facture1 = resultat.get();
            facture1.setReservation(facture.getReservation());
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

    @Override
    public Facture findFacture(int id) {
        return factureRepository.findById(id).get();
    }

    @Override
    public Page<Facture> allFacturePages(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return factureRepository.findAll(pageable);
    }

    @Override
    public double totalFactureBrut() {
        return factureRepository.sumTotalByReservation();
    }

    @Override
    public double totalFactureNet() {
        return factureRepository.sumTotalByReservationAndStatus();
    }
}
