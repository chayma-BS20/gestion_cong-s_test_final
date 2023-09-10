package tn.esprit.templatespring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.DemandeCongeEnAttente;
import tn.esprit.templatespring.Services.DemandeCongeEnAttenteService;
import tn.esprit.templatespring.Services.DemandeCongeEnAttenteServiceImpl;
import tn.esprit.templatespring.Services.ServiceMail;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/demandes-conge-en-attente")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular

public class DemandeCongeEnAttenteController {
    @Autowired
    private DemandeCongeEnAttenteServiceImpl demandeCongeEnAttenteServiceImpl;
    @Autowired
    private ServiceMail servicemail;
    String destinataire ;
    @PostMapping("/soumettre")
    public DemandeCongeEnAttente soumettreDemande(@RequestBody DemandeCongeEnAttente demande) {
        return demandeCongeEnAttenteServiceImpl.soumettreDemandeCongeEnAttente(demande);
    }

    @GetMapping("/lister-en-attente")
    public List<DemandeCongeEnAttente> listerDemandesEnAttente() {
        return demandeCongeEnAttenteServiceImpl.listerDemandesEnAttente();
    }

    @PutMapping("/approuver/{demandeId}")
    public ResponseEntity<Void> approuverDemande(@PathVariable Long demandeId) {
        Optional<DemandeCongeEnAttente> demandeOptional = demandeCongeEnAttenteServiceImpl.finddemandesbyId(demandeId);

        if (demandeOptional.isPresent()) {
            String destinataire = demandeOptional.get().getUser().getEmail();
            if (destinataire != null) {
                String sujet = "Demande de congé accepté";
                String nom = demandeOptional.get().getUser().getNom();
                Date dateDebut = demandeOptional.get().getDateDebut();
                Date dateFin = demandeOptional.get().getDateFin();
                String type = demandeOptional.get().getType();

                // Créez le contenu HTML de l'e-mail avec des couleurs et une taille de police personnalisées
                String corps = "<html><body>";
                corps += "<h1 style='color: blue; font-size: 24px;'>Demande de congé <span style='color: green;'>validé</span></h1>";
                corps += "<p style='font-size: 18px; color: blue;'>Bonjour Monsieur/Madame " + nom + "</p>";
                corps += "<p style='font-size: 18px; color: blue;'>Votre demande de congé du " + dateDebut + " jusqu'à " + dateFin + " de type " + type + " a été <span style='color: green;'>validé</span>.</p>";
                corps += "</body></html>";


                try {
                    servicemail.sendEmail(destinataire, sujet, corps);
                } catch (MessagingException e) {
                    // Gérez les exceptions liées à l'envoi d'e-mails
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                demandeCongeEnAttenteServiceImpl.approuverDemandeCongeEnAttente(demandeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // Gérez le cas où l'adresse e-mail du destinataire est nulle
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Gérez le cas où l'Optional est vide (demande introuvable)
            // Vous pouvez lever une exception, renvoyer une réponse d'erreur, ou faire autre chose en conséquence.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/refuser/{demandeId}")
    public ResponseEntity<Void> refuserDemande(@PathVariable Long demandeId) {
        Optional<DemandeCongeEnAttente> demandeOptional = demandeCongeEnAttenteServiceImpl.finddemandesbyId(demandeId);

        if (demandeOptional.isPresent()) {
            String destinataire = demandeOptional.get().getUser().getEmail();

            if (destinataire != null) {
                String sujet = "Demande de congé refusée";
                String nom = demandeOptional.get().getUser().getNom();
                Date dateDebut = demandeOptional.get().getDateDebut();
                Date dateFin = demandeOptional.get().getDateFin();
                String type = demandeOptional.get().getType();

                // Créez le contenu HTML de l'e-mail avec des couleurs et une taille de police personnalisées
                String corps = "<html><body>";
                corps += "<h1 style='color: blue; font-size: 24px;'>Demande de congé <span style='color: red;'>refusée</span></h1>";
                corps += "<p style='font-size: 18px; color: blue;'>Bonjour Monsieur/Madame " + nom + "</p>";
                corps += "<p style='font-size: 18px; color: blue;'>Votre demande de congé du " + dateDebut + " jusqu'à " + dateFin + " de type " + type + " a été <span style='color: red;'>refusée</span>.</p>";
                corps += "</body></html>";


                try {
                    servicemail.sendEmail(destinataire, sujet, corps);
                } catch (MessagingException e) {
                    // Gérez les exceptions liées à l'envoi d'e-mails
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                demandeCongeEnAttenteServiceImpl.refuserDemandeCongeEnAttente(demandeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // Gérez le cas où l'adresse e-mail du destinataire est nulle
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Gérez le cas où l'Optional est vide (demande introuvable)
            // Vous pouvez lever une exception, renvoyer une réponse d'erreur, ou faire autre chose en conséquence.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{demandeId}")

	public void SupprimerDemande( @PathVariable Long demandeId) {
		
    	demandeCongeEnAttenteServiceImpl.removedemande(demandeId);
	            System.out.println("delete c'est bon ")  ;        
	}
	
    @PostMapping("/checkChevauchement")
    public ResponseEntity<Boolean> checkChevauchementConges(@RequestBody DemandeCongeEnAttente demande) throws ParseException {
        // Appelez votre service pour vérifier le chevauchement des congés ici
        boolean chevauchement = demandeCongeEnAttenteServiceImpl.checkChevauchementConges(demande.getDateDebut(), demande.getDateFin());

        return ResponseEntity.ok(chevauchement);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DemandeCongeEnAttente>> getCongesByUserId(@PathVariable Long userId) {
        List<DemandeCongeEnAttente> congelist = demandeCongeEnAttenteServiceImpl.getCongesByUserId(userId);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+congelist.toString());
        return new ResponseEntity<>(congelist, HttpStatus.OK);
    }
    
}
