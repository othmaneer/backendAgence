package ma.agence.voyage.service;

import ma.agence.voyage.entity.Facture;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {


    public byte[] generatePdf(Facture facture) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        String filePath = System.getProperty("user.home") + "/Downloads/" + facture.getNom() + ".pdf";


        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14); // Increase font size for the title
            contentStream.setNonStrokingColor(0, 0, 255); // Set text color to blue
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Set starting position

            // Title
            contentStream.showText("Horizon Explorer Agency");
            contentStream.newLineAtOffset(0, -30); // Add space

            // Facture Details
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12); // Reset font size for the details
            contentStream.setNonStrokingColor(0, 0, 0); // Reset text color to black

            // Facture Information
            contentStream.showText("Facture Details:");
            contentStream.newLineAtOffset(0, -20);

            contentStream.showText("ID Réservation: " + facture.getIdFacture());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Ref Facture: " + facture.getNom());
            contentStream.newLineAtOffset(0, -15);

            // Client Information
            contentStream.showText("Client Details:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nom client: " + facture.getReservation().getClient().getNom());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Prénom client: " + facture.getReservation().getClient().getPrenom());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("@: " + facture.getReservation().getClient().getEmail());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Tél: " + facture.getReservation().getClient().getTel());

            // Reservation Information
            contentStream.showText("Reservation Details:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Hotel: " + facture.getReservation().getHotel().getNom());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Prix par nuitée: " + facture.getReservation().getHotel().getPrix());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Destination: " + facture.getReservation().getDestination().getVille());

            // Total
            contentStream.showText("Total: " + facture.getTotal());

            contentStream.endText();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }


}
