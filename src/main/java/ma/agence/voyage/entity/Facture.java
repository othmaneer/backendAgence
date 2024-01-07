package ma.agence.voyage.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFacture;

    private String nom;

    private double total;
    @ManyToOne
    private Reservation reservation;

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public String getNom() {
        return nom;
    }

    private String generateUniqueNom() {
        long timestamp = System.currentTimeMillis();
        int randomNumber = new Random().nextInt(100000);  // Adjust the range as needed
        return "FACTURE N° " + timestamp + randomNumber;
    }



    public void setNom() {
        nom = generateUniqueNom();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        LocalDate datedb= reservation.getDate_debut();

        LocalDate datedf= reservation.getDate_fin();



        long differenceInDays = ChronoUnit.DAYS.between(datedb, datedf);

        System.out.println("diff days: "+ differenceInDays +" date_db"+
                datedb +" date_df"+datedf + " client: "+ reservation.getClient().getNom()
                + "\n| hotel prix | "+ reservation.getHotel().getPrix()
                + "\n hotel nom "+ reservation.getHotel().getNom()
                + "\n | hotel prix |"+ reservation.getHotel().getPrix()
                + "\n | hotel id |"+ reservation.getHotel().getIdHotel()
                + "\n | hotel car |"+ reservation.getHotel().getCaracteristique()
                + "\n | hotel obj |"+ reservation.getHotel().toString()



        );

        if(differenceInDays ==0)
        {
            this.total = reservation.getHotel().getPrix();

            System.out.println("true total: "+ total);
        }
        else
        {
            this.total =differenceInDays * reservation.getHotel().getPrix();
            System.out.println("false total: "+ total);

        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
