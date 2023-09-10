package tn.esprit.templatespring.Services;

import java.text.ParseException;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Repository.CongesCRUD;
@Service

public class CongesService implements ICongesService {
	 @  Autowired
	 private   CongesCRUD congerepo ;
	 @Autowired
		UserService us ;
	@Override
	public List<Conges> findAllConges() {
     return congerepo.findAll();	}

	@Override
	public Conges updateConges(Conges conge) {
	    Conges conge1 = congerepo.findById(conge.getIdConges()).orElse(null);
	    conge1.setIdConges(conge.getIdConges());
	    //j'ai prix une boite vide je lui ai donné le meme identifiant que le congé que je veux modifier par suite j'ai specifier les champs que je souhaite changer du congé mais en personnalisant la boite 
	    // et puis je vais enregistrer la boite configuré en remplacant le congé que j'ai souhaité modifié 
	    conge1.setDateDebut(conge.getDateDebut());
	    conge1.setDateFin(conge.getDateFin());
	    return congerepo.save(conge1);
	}

	@Override
	public Conges findCongesbyId(long idConges) {
		return congerepo.getById(idConges);
	}

	@Override
	public void removeConges(long idConges) {
     congerepo.deleteById(idConges);		
	}

	@Override
	public Conges addConges(Conges conge) {
		System.out.println ("add service");
		System.out.println (conge.getDateDebut());
	

    return congerepo.save(conge);
	}

	@Override
	public void validerConges(Conges conge) {
		
	}


public boolean checkChevauchementConges(Date dateDebut, Date dateFin) {
    if (dateDebut == null || dateFin == null) {
        System.out.println("Les dates de début et de fin ne peuvent pas être nulles.");
        return false;
    }

    User utilisateur = us.findUser((long) 12);
    List<Conges> congesUtilisateur = utilisateur.getConges();

    for (Conges conge : congesUtilisateur) {
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
	    public List<Conges> getCongesByUserId(Long userId) {

	        return congerepo.findByUser_IdUser(userId);
	    }
	   public List<Conges> getAllConges() {
	        return congerepo.findAll();
	    }

	    public Optional<Conges> getCongesById(Long id) {
	        return congerepo.findById(id);
	    }

	  

	      

	        public void validerConges(long idConges, String etat) {
	            Optional<Conges> optionalConges = congerepo.findById(idConges);
	            System.out.println("conges"+idConges);
	            System.out.println("etat"+etat);

	            
	            if (optionalConges.isPresent()) {
	                Conges conges = optionalConges.get();
	                conges.setEtat(etat); // Mettez à jour l'état du congé (accepté ou refusé)
	                conges = congerepo.save(conges);

	                if ("approuvé".equals(etat)) {
	                    User user = conges.getUser();
	                    user.setNb_solde(user.getNb_solde() + (conges.getDateFin().getDay() - conges.getDateDebut().getDay()));
	                    
	                     us.updateUser(user); // Mettez à jour le solde de l'utilisateur
	                } else if ("refusé".equals(etat)) {
	                    congerepo.delete(conges);
	                }
	            }
	        }
}
	    

	    




