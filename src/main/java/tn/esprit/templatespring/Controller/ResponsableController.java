package tn.esprit.templatespring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Entities.responsables;
import tn.esprit.templatespring.Services.ResponsablesServices;
import tn.esprit.templatespring.Services.SHService;
import tn.esprit.templatespring.Services.UserService;

@RestController
@RequestMapping("/responsable")
@ComponentScan("responsables")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular

public class ResponsableController {
	@Autowired
	ResponsablesServices rs ;

	@Autowired
	UserService us ;
	@Autowired
	SHService shs ;
	
	@GetMapping("/get")
   public List<responsables> Consulterresponsables() {
       return rs.Consulterresponsables();
    }
	
   @PostMapping("/add")
   public responsables ajouterResponsables(@RequestBody responsables r) {
	   System.out.println("jjjjjjjjjjjjjjjjj"+r.toString());
       Long rid = r.getStructure().getIdsh();
       StructureHierarchique rTrouvee = shs.findSH(rid);
       r.setStructure(rTrouvee);

       Long responsableId = r.getResponsable().getIdUser();
       User responsableTrouve = us.findUser(responsableId);
       r.setResponsable(responsableTrouve);
     // responsableTrouve.setSh(rTrouvee);
     // responsableTrouve.getResponsables().add(r);
       responsableTrouve.setDepartementsAuxQuelsIlAppartient(rTrouvee);

       return rs.addResponsables(r);
   }

}
