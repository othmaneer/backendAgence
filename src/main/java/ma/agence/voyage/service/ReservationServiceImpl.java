package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import ma.agence.voyage.entity.Reservation;
import ma.agence.voyage.repository.FactureRepository;
import ma.agence.voyage.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReservationServiceImpl implements ReservationService{
    
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    FactureRepository factureRepository;



    @Override
    public Reservation ajouterReservation(Reservation reservation) {
         reservationRepository.save(reservation);
        Facture facture = new Facture();
        System.out.println("save facture, id res: "+ reservation.getIdReservation());
         Reservation findRes= reservationRepository.findById(reservation.getIdReservation()).get();
        facture.setReservation(findRes);
        facture.setTotal();
        facture.setNom();
        factureRepository.save(facture);
        return reservation;
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        Optional<Reservation> resultat= reservationRepository.findById(reservation.getIdReservation());
        if (resultat.isPresent())
        {
            Reservation reservation1 = resultat.get();
            reservation1.setClient(reservation.getClient());
            reservation1.setDate_debut(reservation.getDate_debut());
            reservation1.setDate_fin(reservation.getDate_fin());
            reservation1.setDestination(reservation.getDestination());
            reservation1.setHotel(reservation.getHotel());
            reservation1.setTransports(reservation.getTransports());
            reservation1.setStatus(reservation.getStatus());

            return reservationRepository.save(reservation1);
        }

        return null;
    }

    @Override
    public boolean supprimerReservation(int id) {
        Optional<Reservation> resultat= reservationRepository.findById(id);

        Optional<Facture> facture1= factureRepository.findFactureByReservationId(id);
        if (resultat.isPresent() )
        { Facture facture2 = factureRepository.findFactureByReservationId(id).get();
            reservationRepository.deleteById(id);
            //factureRepository.deleteById(facture2.getIdFacture());
            return true;
        }

        return false;
    }

    @Override
    public List<Reservation> listReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Page<Reservation> allReservationPagePaginations(int pagenumber, int pagesize) {
        Pageable pageable = PageRequest.of(pagenumber, pagesize);
        return reservationRepository.findAll(pageable);
    }

    @Override
    public long resNonPayee() {
        return reservationRepository.countByStatus("Non payée");
    }

    @Override
    public long resPayee() {
        return reservationRepository.countByStatus("Payée");
    }

    @Override
    public  List<Object[]> resParStatus() {
        return reservationRepository.countByStatusGrouped();
    }

    @Override
    public List<Object[]> chiffreDaffParmois() {
        return reservationRepository.sumTotalByMonth();
    }

    @Override
    public List<Object[]> chiffreDaffParmoisNet() {
        return reservationRepository.sumTotalByMonthNet();
    }

    @Override
    public List<Object[]> nombreResParType() {
        return reservationRepository.nombreResParType();
    }
}
