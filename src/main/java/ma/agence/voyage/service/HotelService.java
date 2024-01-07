package ma.agence.voyage.service;

import ma.agence.voyage.entity.Client;
import ma.agence.voyage.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {
    public Hotel ajouterHotel(Hotel hotel);

    public Hotel modifierHotel(Hotel hotel);

    public boolean supprimerHotel(int id);

    public List<Hotel> listHotel();

    public Page<Hotel> AllHotelsPaginations(int pagenumber, int pagesize  );
}
