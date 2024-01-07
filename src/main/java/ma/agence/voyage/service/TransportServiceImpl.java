package ma.agence.voyage.service;

import ma.agence.voyage.entity.Hotel;
import ma.agence.voyage.entity.Transport;
import ma.agence.voyage.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TransportServiceImpl implements TransportService{
    
    @Autowired
    TransportRepository transportRepository;
    @Override
    public Transport ajouterTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    public Transport modifierTransport(Transport transport) {
        Optional<Transport> resultat= transportRepository.findById(transport.getIdTransport());
        if (resultat.isPresent())
        {
            Transport transport1 = resultat.get();
            transport1.setNom(transport.getNom());
            transport1.setCapacite(transport.getCapacite());
            transport1.setType(transport.getType());

            return transportRepository.save(transport1);
        }

        return null;
    }

    @Override
    public boolean supprimerTransport(int id) {
        Optional<Transport> resultat= transportRepository.findById(id);
        if (resultat.isPresent())
        {
            transportRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Transport> listTransport() {
        return transportRepository.findAll();
    }

    @Override
    public Page<Transport> allTransportsPaginations(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return transportRepository.findAll(pageable);
    }
}
