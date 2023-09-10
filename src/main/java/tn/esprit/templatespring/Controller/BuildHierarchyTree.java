package tn.esprit.templatespring.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Repository.StructureHierarchiqueCRUD;
import tn.esprit.templatespring.Services.SHService;
import tn.esprit.templatespring.Services.UserService;
@ComponentScan("Services")
@Component
public class BuildHierarchyTree {
//	@Autowired
//	StructureHierarchiqueCRUD shss ;
//	
//      public BuildHierarchyTree () {}
//	    
// //  private SHService shss = new SHService ();
//
//	
//   static Map <Long,StructureHierarchiqueNode>nodes = new HashMap<>();
//   public static StructureHierarchiqueNode root ;
//
////List<StructureHierarchique> structures  on souhaite l'organiser selon un organigramme c'est pour cela elle est passée en paramlétre de la methode 
//  public void buildHierarchyTree() {
//      List<StructureHierarchique> structures = shss.findAll();
//
//	//On va parcourir chaque structure de la liste 
//	System.out.println("Nombre de structures : " + shss.findAll());
//
//    System.out.println("en dehoors du foreach du buildHirerarchy");
//    
//	for (StructureHierarchique structure : structures) {
//	    System.out.println("en dedans du foreach du buildHirerarchy");
//
//	
//    	//Création d'un noeud node pour chaque structure en utilisant le constructeur StructureHierarchiqueNode(structure)
//        StructureHierarchiqueNode node = new StructureHierarchiqueNode(structure);
//        //on ajoute chaque noeud a la map tels que l'identifiant de la structure est la clé et le nœud comme valeur. (vu que la structure de la map est clé valeur )
//
//        nodes.put(structure.getIdsh(), node);
//        //par suite on vérifie si la structur a un aprent ou non si elle n'a pas de parent cette noeud sera la racine de notre organigaramme
//        if (structure.getShparent() == null || structure.getShparent().getIdsh() == 1) {
//            root = node;
//            System.out.println("Le nœud est la racine : " + root.toString());
//        } else {
//            StructureHierarchiqueNode parent = nodes.get(structure.getShparent().getIdsh());
//            parent.addChild(node);
//            System.out.println("Le nœud est ajouté comme enfant du parent : " + parent.toString());
//        }
//
//    }
//
//}
//
//
//public void printHierarchyTree(StructureHierarchiqueNode node, int level) {
// if (node == null) {
//	System.out.println("node est nulle");
//
// return ;
//
// } 
//	//je vais parcourir le level qui presente le niveau de profondeur 
//    for (int i = 0; i < level; i++) {
//        System.out.print("\t");
//    }
//    //j'affiche le libellé de la structure hierarchique 
//    System.out.println(node.getStructure().getLibelle());
//	System.out.println("node est nulle");
//
//    //recuperation des enfants de la structure 
//    List<StructureHierarchiqueNode> children = node.getChildren();
//    //en parcourant la liste de senfants j'effectue le print pour chaque enfant sout structure 
//    for (StructureHierarchiqueNode child : children) {
//    	//=> Appel et Parcours récusive
//        printHierarchyTree(child, level + 1);
//    }
//}
//
//



}
