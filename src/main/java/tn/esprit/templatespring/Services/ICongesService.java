package tn.esprit.templatespring.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.templatespring.Entities.Conges;

public interface ICongesService {
	    List<Conges> findAllConges();
	   
	    Conges updateConges (Conges conge);
	    Conges findCongesbyId (long idConges);
	    void removeConges(long idConges);
	    Conges addConges (Conges conge);
	    void validerConges(Conges conge );
	    List<Conges> getCongesByUserId(Long userId);

}
