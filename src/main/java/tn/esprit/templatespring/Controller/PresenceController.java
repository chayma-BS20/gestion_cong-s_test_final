package tn.esprit.templatespring.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.templatespring.Entities.Presence;
import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Services.PresenceService;
import tn.esprit.templatespring.Services.UserService;
@RestController
@RequestMapping("/presences")
@ComponentScan("Services")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular

public class PresenceController {
	@Autowired
	UserService us ;
	@Autowired
	PresenceService ps ;

	@GetMapping
    public List<Presence> ConsulllterPresences() {
        return ps.ConsulterPresence();
    }
	
	@PostMapping("/add")
	public Presence AjouterPresence( @RequestBody Presence presence) {
//		   String heurePointageStr = presence.getHeurePointage().toString() ;
//
//		    // Convertissez la chaîne en objet Date en utilisant un SimpleDateFormat
//		    try {
//		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		        Date heurePointage = sdf.parse(heurePointageStr);
//		        presence.setHeurePointage(heurePointage);
//		    } catch (ParseException e) {
//		        e.printStackTrace();
//		    }
		////j'ai essayé de récuperer l'utilisateur dont l'id est passé dans la presence en paramétre
    Long UserId = presence.getUser().getIdUser();
    User utrouvé = us.findUser(UserId);
    presence.setUser(utrouvé);
	return ps.addPresence(presence);
		
	}
	
	   @GetMapping("/getAll")
	    public List<Presence> ConsulterPresence() {
	        ArrayList<Presence> presences = new ArrayList<>(); 
	        presences = (ArrayList<Presence>)ps.ConsulterPresence() ;
	        return presences;
	    }
	   

}
