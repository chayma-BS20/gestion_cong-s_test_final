package tn.esprit.templatespring.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.DemandeCongeEnAttente;

public interface DemandeCongeEnAttenteService {
	     DemandeCongeEnAttente soumettreDemandeCongeEnAttente(DemandeCongeEnAttente demande);
	    List<DemandeCongeEnAttente> listerDemandesEnAttente();
	    void approuverDemandeCongeEnAttente(Long demandeId);
	    void refuserDemandeCongeEnAttente(Long demandeId);
	    List<DemandeCongeEnAttente> getCongesByUserId(Long userId);
	    void removedemande(Long demandeId);
	   Optional<DemandeCongeEnAttente> finddemandesbyId (Long demandeId);

}
