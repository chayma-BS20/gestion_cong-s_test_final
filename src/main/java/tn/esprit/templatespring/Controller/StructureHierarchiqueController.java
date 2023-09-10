package tn.esprit.templatespring.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.templatespring.Entities.StructureHierarchique;
//import tn.esprit.templatespring.Entities.responsables;
import tn.esprit.templatespring.Entities.User;
//import tn.esprit.templatespring.Services.ResponsablesServices;
import tn.esprit.templatespring.Services.SHService;
import tn.esprit.templatespring.Services.UserService;
@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular

@RestController
@RequestMapping("/SH")
@ComponentScan("Services")

public class StructureHierarchiqueController {

	@Autowired
	UserService us ;
	@Autowired
	SHService shs ;
//	@Autowired
//	ResponsablesServices rs ;

	List<StructureHierarchique> structures = new ArrayList();
	 //responsables r = new responsables ();
	 User responsableTrouvé = new User ();
    public List<StructureHierarchique> ConsulllterSH() {
        return shs.ConsulterSH();
    }
	
//	@PostMapping("/add")
//	public StructureHierarchique AjouterSH( @RequestBody StructureHierarchique sh) {
//		////j'ai essayé de récuperer l'utilisateur dont l'id est passé dans la presence en paramétre
//		Long ShId = sh.getShparent().getIdsh();
//	    StructureHierarchique shTrouvé = shs.findSH(ShId);
//	    sh.setShparent(shTrouvé);
//    
//		Long ResponsableId = sh.getResponsable().getIdUser();
//        User responsableTrouvé = us.findUser(ResponsableId);
//        sh.setResponsable((List<User>) responsableTrouvé);
//        
//        if (shTrouvé != null) {
//            shTrouvé.getResponsable().getDepartementsDontResponsable().add(sh);
//            shTrouvé.getEmployés().add(responsableTrouvé) ;
//        }
//        
//        if (sh.getShenfants() != null) {
//            for (StructureHierarchique enfant : sh.getShenfants()) {
//                // Récupérer l'utilisateur responsable du service enfant à partir de son ID
//                Long ResponsableEnfantId = enfant.getResponsable().getIdUser();
//                User responsableEnfant = us.findUser(ResponsableEnfantId);
//
//                // Ajouter le nouvel utilisateur en tant que responsable du service enfant
//                enfant.setResponsable((List<User>) responsableTrouvé);
//
//                // Ajouter le nouvel utilisateur à la liste des responsables du service enfant
//                responsableTrouvé.getDepartementsDontResponsable().add(enfant);
//
//                // Ajouter le nouvel utilisateur à la liste des employés du service enfant
//                enfant.getEmployés().add(responsableEnfant);
//            }
//        }
//
//
//
//        //retour a la table user pour remplir le champs de id_structure hierarchique qui était nulle vu qu'on ajoute le user avant d'ajouter la structure hierarchique 
//     responsableTrouvé.getDepartementsDontResponsable().add(sh);
//     responsableTrouvé.setDepartementsAuxQuelsIlAppartient(sh);
//     System.out.println("dddddddddddddddddddddddddddddddddddddddddddd"+responsableTrouvé.getDepartementsDontResponsable());
//     System.out.println("dddddddddddddddddddddddddddddddddddddddddddd"+responsableTrouvé.getDepartementsAuxQuelsIlAppartient());
//
//     return shs.addSH(sh);
//		
//
	
	@PostMapping("/add")
	public StructureHierarchique AjouterSH(@RequestBody StructureHierarchique sh) {
	    System.out.println("jjjjjjjjjjjjjjjjj" + sh.toString());

	    if (sh.getShparent() != null) {
	        Long ShId = sh.getShparent().getIdsh();
	        StructureHierarchique shTrouvé = shs.findSH(ShId);
	        sh.setShparent(shTrouvé);
	    } else {
	        // La structure parente n'est pas définie, assurez-vous de gérer ce cas
	        // Vous pouvez définir une action par défaut ou générer une erreur selon vos besoins
	    	System.out.println("Shparent est nulle ");
	    }

	    return shs.addSH(sh);
	}


	 static Map <Long,StructureHierarchiqueNode>nodes = new HashMap<>();
	   public static StructureHierarchiqueNode root ;
	   private List<StructureHierarchique> structuresWithoutParent = new ArrayList<>();
	   static int nb ;


	
	   public StructureHierarchiqueNode buildHierarchyTree() {
		   //J'ai recuperer la liste des structures
		    List<StructureHierarchique> structures = shs.ConsulterSH();
		    nb=structures.size();
               //je vais parcourir la liste des structures
		    for (StructureHierarchique structure : structures) {
		    	//a l'aide du constructeurde la classe StructureHierarchiqueNode je vais associer chaque structure a un node 
		        StructureHierarchiqueNode node = new StructureHierarchiqueNode(structure);
		        //je  vais remplir le hashmap nodes avec les nodes qui sont les structures tels que  la clé est l'id de la structure et la valeur est la node elle meme 
		        nodes.put(structure.getIdsh(), node);
              //je vais specifier le cas ou le node est le root 
		        if (structure.getShparent() == null || structure.getShparent().getIdsh() == null) {
		            root = node;

		            // Ajouter le nœud racine à la map 'nodes'
		            //nodes.put(structure.getIdsh(), root);
		            //****Si ce n'est pas le cas 
		        } else {
		        	//je vais recuperer l'id du parent de la structure en cours
		            Long parentId = structure.getShparent().getIdsh();
                     //si le hashmap contient l'id parent de la structure
		            if (nodes.containsKey(parentId)) {
		            	// je vais créer une StructureHierarchiqueNode appelé node dans laquelle je vais récuperer a partir du map le nodes ayant comme clé l'id du parent
		                StructureHierarchiqueNode parent = nodes.get(parentId);
		                //ainsi je vais ajouter ce node au children du parent 
		                parent.addChild(node);
			            //****Si ce n'est pas le cas 

		            } else {
		            	//cette structure n'a pas de parent
		                //System.out.println("Parent non trouvé pour la structure avec l'identifiant : " + structure.getIdsh());
		               //je vais l'ajouter a la liste des structures sans parenst
		                structuresWithoutParent.add(structure);
		            }
		        }
		    }

		    // Traiter les structures sans parent ultérieurement une fois que les parents ont été ajoutés à la map
		    for (StructureHierarchique structure : structuresWithoutParent) {
		        Long parentId = structure.getShparent().getIdsh();
		        if (nodes.containsKey(parentId)) {
		        	//chaque structure sans parent est associée à son parent correspondant en récupérant le nœud parent à partir de la map nodes en utilisant l'ID du parent.
		            StructureHierarchiqueNode parent = nodes.get(parentId);
		            //Le nœud actuel (structure sans parent) est ajouté comme enfant du nœud parent.
		            StructureHierarchiqueNode node = nodes.get(structure.getIdsh());
		            parent.addChild(node);
		        } else {
		            System.out.println("Impossible de trouver le parent pour la structure avec l'identifiant : " + structure.getIdsh());
		        }
		    }
		return root ;}
	   



	
	
	public void printHierarchyTree(StructureHierarchiqueNode node, int level) {
	    if (node == null) {
	        System.out.println("node est nulle");
	        return;
	    }

	    // Je vais parcourir le level qui représente le niveau de profondeur
	    for (int i = 0; i < level; i++) {
	        System.out.print("\t");
	    }

	    System.out.println(node.getStructure().getLibelle());

	    List<StructureHierarchiqueNode> children = node.getChildren();

	    // Parcourir la liste des enfants et effectuer l'impression récursive
	    for (StructureHierarchiqueNode child : children) {
	        printHierarchyTree(child, level + 1);
	    }
	      }
	
	public StructureHierarchiqueNode getRootNode() {
        return root;
    }
	@GetMapping("/get")

	    public List<StructureHierarchique> ConsulterrSh() {

	        structures = (ArrayList<StructureHierarchique>)shs.ConsulterSH() ;
	        // Ajoutez vos objets StructureHierarchique à la liste structures
	       //  BuildHierarchyTree tree = new BuildHierarchyTree();
	        this.buildHierarchyTree();
	        this.printHierarchyTree(this.root, 0);
            System.out.println(nb);
	        return structures;
	    }
	    
	   @GetMapping("/getAll")
	   public List<Map<String, Object>> ConsulterrrSh() {
	       List<StructureHierarchique> structures = shs.ConsulterSH();
	       // un map qui represente qui contient les departements avec leurs enfants 
	       List<Map<String, Object>> departmentsData = new ArrayList<>();
	       // j'ai parcourrus cette map et j'ajoute les les proprietés de chacun
	       for (StructureHierarchique structure : structures) {
	           Map<String, Object> departmentData = new HashMap<>();
	           departmentData.put("idsh", structure.getIdsh());
	           departmentData.put("libelle", structure.getLibelle());
	           departmentData.put("Le departement aprent", structure.getShparent());
	           departmentData.put("Liste des enfants", structure.getShenfants());
	           departmentData.put("Liste des employés", structure.getEmployés());

	           departmentData.put("les responsabilités ", structure.getResponsablesList());


// s'il tombe que cette structure a des enfants je vais aborder la liste des enfants et je vais les traitetr ctout comme des structures 
	           if (structure.getShenfants() != null && !structure.getShenfants().isEmpty()) {
	               List<Map<String, Object>> childrenData = new ArrayList<>();
	               for (StructureHierarchique child : structure.getShenfants()) {
	                   Map<String, Object> childData = new HashMap<>();

	                   childData.put("idsh", child.getIdsh());
	                   childData.put("libelle", child.getLibelle());
	                   childData.put("Le departement aprent", child.getShparent());
	                   childData.put("Liste des enfants", child.getShenfants());
	                   childData.put("Liste des employés", child.getEmployés());
	                   childData.put("les responsabilités ", child.getResponsablesList());
	                   childrenData.add(childData);
	               }
	               // j'ajoute les enfants comme des enfants aux structures de la map
	               departmentData.put("shenfants", childrenData);
	           }
	          // j'ajoute a la liste qui va retourner

	           departmentsData.add(departmentData);
	       }
	       
	       return departmentsData;
	   }

	  
	   @GetMapping("findById/{idSH}")
	   public StructureHierarchique findStructureById(@PathVariable Long idSH) {
	       return shs.findSH(idSH);
	   }


	   

	

}
