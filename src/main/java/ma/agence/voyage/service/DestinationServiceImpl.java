package ma.agence.voyage.service;

import ma.agence.voyage.entity.Destination;
import ma.agence.voyage.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DestinationServiceImpl implements DestinationService{
    @Autowired
    DestinationRepository destinationRepository;
    @Override
    public Destination ajouterDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination modifierDestination(Destination destination) {
        Optional<Destination> resultat= destinationRepository.findById(destination.getIdDestination());
        if (resultat.isPresent())
        {
            Destination destination1 = resultat.get();
            destination1.setPays(destination.getPays());
            destination1.setVille(destination.getVille());

            return destinationRepository.save(destination1);
        }

        return null;
    }

    @Override
    public boolean supprimerDestination(int id) {
        Optional<Destination> resultat= destinationRepository.findById(id);
        if (resultat.isPresent())
        {
            destinationRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Destination> listDestination() {
        return destinationRepository.findAll();
    }

    @Override
    public Page<Destination> allDestinationsPaginations(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return destinationRepository.findAll(pageable);
    }
}
