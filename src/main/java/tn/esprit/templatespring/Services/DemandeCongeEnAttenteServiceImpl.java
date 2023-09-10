package tn.esprit.templatespring.Services;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.DemandeCongeEnAttente;
import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Repository.CongesCRUD;
import tn.esprit.templatespring.Repository.DemandeCongeEnAttenteRepository;
import tn.esprit.templatespring.Repository.UserCRUD;

@Service
public class DemandeCongeEnAttenteServiceImpl implements DemandeCongeEnAttenteService {
    @Autowired
    private DemandeCongeEnAttenteRepository demandeCongeEnAttenteRepository;
    @Autowired
    
   private UserCRUD us ;
    @Autowired
    private CongesCRUD congerepo;
    @Autowired
	UserService uss ;
    private List<Conges> congésApprouvés = new ArrayList<>();
    private List<Conges> congésRefusés = new ArrayList<>();
    @Override
    public DemandeCongeEnAttente soumettreDemandeCongeEnAttente(DemandeCongeEnAttente demande) {
	    User utrouvé = us.findById(12);

	    demande.setUser(utrouvé);
        demande.setEtat("en attente"); // Initialiser l'état comme "en attente"
        return demandeCongeEnAttenteRepository.save(demande);
    }

    @Override
    public List<DemandeCongeEnAttente> listerDemandesEnAttente() {
        return demandeCongeEnAttenteRepository.findByEtat("en attente");
    }

    @Override
    public void approuverDemandeCongeEnAttente(Long demandeId) {
        Optional<DemandeCongeEnAttente> demandeOptional = demandeCongeEnAttenteRepository.findById(demandeId);
        if (demandeOptional.isPresent()) {
            DemandeCongeEnAttente demande = demandeOptional.get();
            Conges congesApprouve = new Conges();
            congesApprouve.setDateDebut(demande.getDateDebut());
            congesApprouve.setDateFin(demande.getDateFin());
            congesApprouve.setUser(demande.getUser());
            congesApprouve.setEtat("approuvé");
            congesApprouve.setType(demande.getType()); // Remplacez "type_conge" par la valeur correcte du type de congé

            User utilisateur = demande.getUser();
            utilisateur.setNb_solde(utilisateur.getNb_solde() + (congesApprouve.getDateFin().getDay() - congesApprouve.getDateDebut().getDay()));
            uss.updateUser(utilisateur);
            congerepo.save(congesApprouve);
            congésApprouvés.add(congesApprouve);

            demandeCongeEnAttenteRepository.delete(demande);
        }
    }
    @Override
	public void removedemande(Long demandeId) {
    	demandeCongeEnAttenteRepository.deleteById(demandeId);		
	}

    @Override
    public void refuserDemandeCongeEnAttente(Long demandeId) {
        Optional<DemandeCongeEnAttente> demandeOptional = demandeCongeEnAttenteRepository.findById(demandeId);
        if (demandeOptional.isPresent()) {
            DemandeCongeEnAttente demande = demandeOptional.get();
            demande.setEtat("refusé");
            congésRefusés.add((Conges) congésRefusés);

            demandeCongeEnAttenteRepository.save(demande);
            //envoyer un mail
           // demandeCongeEnAttenteRepository.delete(demande);
        }
    }
    
    public boolean checkChevauchementConges(Date dateDebut, Date dateFin) {
        if (dateDebut == null || dateFin == null) {
            System.out.println("Les dates de début et de fin ne peuvent pas être nulles.");
            return false;
        }

        User utilisateur = us.findById(12);
        List<DemandeCongeEnAttente> congesUtilisateur = utilisateur.getDemandeCongeEnAttente();

        for (DemandeCongeEnAttente conge : congesUtilisateur) {
            Date congeDebut = conge.getDateDebut();
            Date congeFin = conge.getDateFin();

            if (congeDebut != null && congeFin != null && chevauche(congeDebut, congeFin, dateDebut, dateFin)) {
                System.out.println("Il y a un chevauchement avec le congé qui commence le " + congeDebut + " et se termine le " + congeFin);
                return true;
            }
        }

        return false;
    }




    	private boolean chevauche(Date debut1, Date fin1, Date debut2, Date fin2) {
    	    return debut1.before(fin2) && fin1.after(debut2);
    	}

    	@Override
	    public List<DemandeCongeEnAttente> getCongesByUserId(Long userId) {

	        return demandeCongeEnAttenteRepository.findByUser_IdUser(userId);
	    }

		@Override
		public Optional<DemandeCongeEnAttente> finddemandesbyId(Long demandeId) {
		return 	demandeCongeEnAttenteRepository.findById(demandeId);
		}

		

		


}