package tn.esprit.templatespring.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import static tn.esprit.templatespring.Controller.StructureHierarchiqueController.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.templatespring.Services.SHService;
import tn.esprit.templatespring.Services.UserService;
import tn.esprit.templatespring.Entities.User;
@CrossOrigin(origins = "http://localhost:4200") // Remplacez avec votre domaine Angular

@RestController
@RequestMapping("/users")
@ComponentScan("Services")
@ComponentScan("Controller")

public class UserController {
	private static final User User = null;
	@Autowired
	UserService us ;
	@Autowired
	SHService shs ;
	@Autowired
	StructureHierarchiqueController shcont ;
   
	@GetMapping
    public List<User> getAllUsers() {
        return us.findAllUser();
    }

	
	@PostMapping("/add")

	public User crUser( @Valid @RequestBody User user) {
	//	System.out.println("dddddddddddddddddddddddddd"+user.toString());
		return us.addUser(user);
	}

	
	
	
    @DeleteMapping("/delete/{idUser}")

	public void deleteUser( @PathVariable Long idUser) {
		
	            us.removeUser(idUser);
	            System.out.println("delete c'est bon ")  ;
	           
	}
    

    static Map <Long,UserNode>nodes = new HashMap<>();
    public static UserNode root ;
    private List<User> structuresWithoutParent = new ArrayList<>();

//    public void buildHierarchyTree() {
//
//    	List<User> users = us.findAllUser();
//    	//System.out.println(users.toString());
//    	for (User u : users) {
//        	//System.out.println(users.toString());
//
//	         UserNode node = new UserNode(u);
//	         nodes.put(u.getIdUser(), node);
//	         //System.out.println(u.toString());
//
//	        if (u.getIdUser()==shcont.buildHierarchyTree().getStructure().getResponsable().getIdUser() ) {
//		            root = node;
//		          //  System.out.println("ddddddddddddddddddddddddd"+root.toString());
//		           // nodes.put(u.getIdUser(), root);      
//
//		        } else {
//		        	System.out.println("jjjdjjdjdjdjdjdjdjdjdjdjjdjdjjdjjdjdjdjdj"+u.toString());
//		        	Long parentID= u.getDepartementsAuxQuelsIlAppartient().getResponsable().getIdUser();
//		        	//Long parentID= u.;
//
//		        	if (nodes.containsKey(parentID)) {
//
//		        		 UserNode parent = nodes.get(parentID);
//		                parent.addChild(node);
//
//		            } else {
//                    System.out.println("Parent non trouvé pour la structure avec l'identifiant : " + u.getIdUser());
//                    System.out.println("111111111111111111111111111111111111111111111");
//		                structuresWithoutParent.add(u);
//     	}
//    	}
//    	}
//
//    	for (User u : structuresWithoutParent) {
//    		Long parentID= u.getDepartementsAuxQuelsIlAppartient().getResponsable().getIdUser();
//    		if (nodes.containsKey(parentID)) {
//    			UserNode parent = nodes.get(parentID);
//
//    			 UserNode node = nodes.get(u.getIdUser());
//    			 parent.addChild(node);
//		            System.out.println("Impossible de trouver le parent pour l'utilisateur  avec l'identifiant : " + u.getIdUser());
//
//    	}
//    	}
//
//
//
//    }
//	
    
	public void printHierarchyTree(UserNode node, int level) {
	    if (node == null) {
	        System.out.println("node est nulle");
	        return;
	    }

	    // Je vais parcourir le level qui représente le niveau de profondeur
	    for (int i = 0; i < level; i++) {
	        System.out.print("\t");
	    }

	    // Afficher le libellé de la structure hiérarchique
	    System.out.println(node.getUser().getNom());

	    // Récupération des enfants de la structure
	    List<UserNode> children = node.getChildren();

	    // Parcourir la liste des enfants et effectuer l'impression récursive
	    for (UserNode child : children) {
	        // Appel récursif avec un niveau accru
	        printHierarchyTree(child, level + 1);
	    }
	      }
	

    
    
    @GetMapping("/getAll")
    public List<User> findAllUser() {
        ArrayList<User> users = new ArrayList<>(); 
        users = (ArrayList<User>) us.findAllUser();
        //this.buildHierarchyTree();
       // this.printHierarchyTree(this.root, 0);
        
        return users;
    }
    
    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
    	System.out.println("hhhhhhhhhhhhhhhhhhhhh"+userId);
       return   us.findUserById(userId);
    }
    
    @PutMapping("/update/{idUser}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable("idUser") Long idUser) {
    //	System.out.println("dddddd"+user.getIdUser());
    	//La ligne de set est necessaire dans le service et dans le controlleur 
        user.setIdUser(idUser);

        User updatedUser = us.updateUser(user);
        System.out.println(updatedUser.toString());

        return user ;
        
    }
    @GetMapping("/userinfo/{idUser}")
    public Optional<User> getUserInfo(@PathVariable("idUser") Long idUser) {
        return us.getUserInfo(idUser);
    }


}
