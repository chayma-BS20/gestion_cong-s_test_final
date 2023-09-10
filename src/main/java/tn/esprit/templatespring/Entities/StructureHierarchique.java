package tn.esprit.templatespring.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "StructureHierarchique")
//une annotation necessaire pour une relation reflexive
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idsh")

public class StructureHierarchique implements Serializable {
	private static final long serialVersionUID = 5697742276555103648L;	//La clé primaire
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_sh")
	    private Long idsh ;
	   
	    private String libelle ;
	 
	    
	 //relation reflexive sur id_parent
	    @ManyToOne
	    @ JoinColumn(name = "id_parent")
	    StructureHierarchique shparent;
	    
	    @OneToMany (mappedBy ="shparent")
	    //@JsonIgnore

	    private List<StructureHierarchique>  shenfants;
	    
	    
	    @OneToMany(mappedBy = "DepartementsAuxQuelsIlAppartient") // Nom de l'attribut dans l'entité User
	    @JsonIgnore
	    private List<User> employés;
	    
	    
	    @OneToMany(mappedBy = "structure")
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    @JsonIgnore
	    private List<responsables> responsablesList;
	    
	    public List<responsables> getResponsablesList() {
			return responsablesList;
		}
		public List<User> getEmployés() {
			return employés;
		}
		public void setEmployés(List<User> employés) {
			this.employés = employés;
		}
		public void setResponsablesList(List<responsables> responsablesList) {
			this.responsablesList = responsablesList;
		}
		
		public StructureHierarchique () {}
	    public StructureHierarchique(Long idsh, String libelle, StructureHierarchique shparent,
				List<StructureHierarchique> shenfants, List<User> employés, User responsable) {
			super();
			this.idsh = idsh;
			this.libelle = libelle;
			this.shparent = shparent;
			this.shenfants = shenfants;
			
		}

	
	

	    @Override
	    public String toString() {
	        return "La structure" + libelle ;
	    }
		public Long getIdsh() {
			return idsh;
		}

		public void setIdsh(Long idsh) {
			this.idsh = idsh;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		public StructureHierarchique getShparent() {
			return shparent;
		}

		public void setShparent(StructureHierarchique shparent) {
			this.shparent = shparent;
		}

		public List<StructureHierarchique> getShenfants() {
			return shenfants;
		}

		public void setShenfants(List<StructureHierarchique> shenfants) {
			this.shenfants = shenfants;
		}

		
		
		
		 
		
	
	
	    
	   
}
