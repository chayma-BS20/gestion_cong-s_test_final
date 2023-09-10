package tn.esprit.templatespring.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap ;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Services.CongesService;
import tn.esprit.templatespring.Services.UserService;



//import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular


@RestController
@RequestMapping("/conges")
@ComponentScan("Services")
public class CongesController {
	@Autowired
	CongesService cs ;
	@Autowired
	UserService us ;
	private int jours_conges;
	
	
    private List<String> etats = Arrays.asList("En attente", "En cours", "Approuvé", "Rejeté");

	
    public List<Conges> ConsulllterConges() {
        return cs.findAllConges();
    }

	@SuppressWarnings("deprecation")
	@PostMapping("/add")
	public Conges AjouterConges(@RequestBody Conges conge) throws ParseException {
	    Long UserId = conge.getUser().getIdUser();
	    User utrouvé = us.findUser((long) 12);

	    conge.setUser(utrouvé);
	    conge.setEtat("En attente");

	    int jours_conges = conge.getDateFin().getDate() - conge.getDateDebut().getDate() + 1;
	    
	    if (utrouvé.getNb_solde() + jours_conges > 26) {
	        System.out.println("Le cumul des jours de congé dépasse le solde annuel.");
	        return null; 
	    }

	    boolean chevauchement = cs.checkChevauchementConges(conge.getDateDebut(), conge.getDateFin());
	    if (chevauchement) {
	        return null; 
	    }
	   
	    Conges addedConge = cs.addConges(conge);
	   

	    return addedConge;
	    
	}


	
	
    @DeleteMapping("/delete/{idConges}")

	public void SupprimerConges( @PathVariable Long idConges) {
		
	            cs.removeConges(idConges);
	            System.out.println("delete c'est bon ")  ;        
	}
	

    @GetMapping("/getAll")
    public List<Conges> ConsulterConges() {
        ArrayList<Conges> conges = new ArrayList<>(); 
        conges = (ArrayList<Conges>)cs.findAllConges() ;
        return conges;
    }
   
    
    
    @PutMapping("/update/{idConges}")
    public Conges ModifierConge(@Valid @RequestBody Conges conge, @PathVariable("idConges") Long idConges) {
    	//La ligne de set est necessaire dans le service et dans le controlleur 
    	conge.setIdConges(idConges);
        Conges updatedConge = cs.updateConges(conge);
        System.out.println(updatedConge.toString());

        return conge ;
        
    }
    @PutMapping("/valider/{idConges}")
    public void validerConges(@PathVariable Long idConges,@RequestBody String etat) {

        cs.validerConges(idConges, etat);            
        
    }
    
    @PostMapping("/checkChevauchement")
    public ResponseEntity<Boolean> checkChevauchementConges(@RequestBody Conges conge) throws ParseException {
        // Appelez votre service pour vérifier le chevauchement des congés ici
        boolean chevauchement = cs.checkChevauchementConges(conge.getDateDebut(), conge.getDateFin());

        return ResponseEntity.ok(chevauchement);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Conges>> getCongesByUserId(@PathVariable Long userId) {
        List<Conges> congelist = cs.getCongesByUserId(userId);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+congelist.toString());
        return new ResponseEntity<>(congelist, HttpStatus.OK);
    }
    
   
}
