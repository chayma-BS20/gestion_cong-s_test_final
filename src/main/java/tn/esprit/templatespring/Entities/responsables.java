package tn.esprit.templatespring.Entities;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// il s'agit d'une join table specifique vu que j'avais besoisn d'jouter un champs qui est la date de validité il m'a fallu construire
//une joine table comme une entitée et abandonner le'option de @jointable qui ne pme crée que 2 colonnes qui contiennent les clés primaires 
//des tables de la relation many to many en question ainsij'ai divisé la relation manytomany en 2 relations onetomany
@Transactional 
@Entity
@Table(name = "responsables")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id_responsable")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class responsables {
    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_responsable;

    @ManyToOne
    @JoinColumn(name = "id_user")
   // @JsonBackReference
    //@JsonIgnore
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private User responsable;
  
    @ManyToOne
    @JoinColumn(name = "id_sh")
  // @JsonBackReference
   //@JsonIgnore
   // @JsonIdentityReference(alwaysAsId = true) // Utilisation de @JsonIdentityReference avec alwaysAsId
  //  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StructureHierarchique structure;
    

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    // Default constructor
    public responsables() {}

    // Constructor with parameters
    public responsables(User responsable, StructureHierarchique structure, Date dateDebut, Date dateFin) {
        this.responsable = responsable;
        this.structure = structure;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public Long getId_responsable() {
		return id_responsable;
	}

	public void setId_responsable(Long id_responsable) {
		this.id_responsable = id_responsable;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}



	public StructureHierarchique getStructure() {
		return structure;
	}

	public void setStructure(StructureHierarchique structure) {
		this.structure = structure;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
    public String toString() {
        return "Responsables [id_responsable=" + id_responsable + ", responsable=" + responsable + ", structure=" +
                structure + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + "]";
    }

	
}
