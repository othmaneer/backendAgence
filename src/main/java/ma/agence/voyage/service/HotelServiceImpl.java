package ma.agence.voyage.service;

import ma.agence.voyage.entity.Client;
import ma.agence.voyage.entity.Hotel;
import ma.agence.voyage.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class HotelServiceImpl implements HotelService{
    
    @Autowired
    HotelRepository hotelRepository;
    
    @Override
    public Hotel ajouterHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel modifierHotel(Hotel hotel) {
        Optional<Hotel> resultat= hotelRepository.findById(hotel.getIdHotel());
        if (resultat.isPresent())
        {
            Hotel hotel1 = resultat.get();
            hotel1.setNom(hotel.getNom());
            hotel1.setCaracteristique(hotel.getCaracteristique());
            hotel1.setLibelle(hotel.getLibelle());
            hotel1.setPrix(hotel.getPrix());
            return hotelRepository.save(hotel1);
        }

        return null;    }

    @Override
    public boolean supprimerHotel(int id) {
        Optional<Hotel> resultat= hotelRepository.findById(id);
        if (resultat.isPresent())
        {
            hotelRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Hotel> listHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Page<Hotel> AllHotelsPaginations(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return hotelRepository.findAll(pageable);
    }
}
