package tn.esprit.templatespring.Entities;

import javax.persistence.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users"
        ,uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable {
	StructureHierarchique sh = new StructureHierarchique () ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    @NotBlank(message= "Veuillez saisir le nom")
    @Column(name = "nom")
    private String nom;
    
    @NotBlank(message= "Veuillez saisir le prenom")
    @Column(name = "prenom")
    private String prenom;
    
    @NotBlank
//@Column(name="...") pour indiquer le nom de la colonne dans la base de données 
    @Column(name = "role")
    private String role;
    
    
    @NotBlank(message= "Veuillez saisir un cin")
    @Column(name = "cin")
    @Size(min = 8, max = 8, message = "Le cin doit contenir exactement 8 caractères")
    private String cin;
    
    @NotNull(message= "Veuillez saisir votre numero de telephone")
    @Digits(integer = 8, fraction = 0, message = "Le numéro de téléphone doit contenir exactement 8 chiffres")
    @Column(name = "numero_telephone")
    private Long numeroTelephone;
    
    @Email
   // @AssertFalse(message = "L'email doit être unique")
    @NotBlank(message= "Veuillez saisir un email")
    @Column(name = "email")
    private String email;
    
    @NotBlank(message= "Veuillez choisir un genre")
    @Column(name = "genre")
    private String genre;
    
//    @NotNull(message = "Veuillez choisir un responsable")
//    @Column(name = "id_responsable")
//    private int idResponsable;
     
//    @NotNull(message = "Veuillez choisir un departement")
//	@Column(name = "id_departement")
//    private int idDepartement;
    
    @NotNull(message = "La date de naissance ne doit pas être nulle")
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_de_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    
    
    @Column(name = "nb_solde")
    private Long nb_solde=(long) 26; // Initialisez avec 0 par défaut

    
    
    public Long getNb_solde() {
		return nb_solde;
	}

	public void setNb_solde(long nb_solde) {
		this.nb_solde = nb_solde;
	}
    
    @Override
	public String toString() {
		return "User [idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", cin=" + cin
				+ ", numeroTelephone=" + numeroTelephone + ", email=" + email + ", genre=" + genre + 
				//", idResponsable="+ idResponsable + 
				", dateDeNaissance=" + dateDeNaissance
				+ ", login=" + login + ", mdp=" + mdp + ", conges=" + conges + "]";
	}



	@Size(max = 8, message = "Le login ne depasse pas 8 caractères")
    @NotBlank(message= "Veuillez saisir un login")
    //@AssertFalse(message = "Le login doit être unique")
    @Column(name = "login")
    private String login;
    
    public List<DemandeCongeEnAttente> getDemandeCongeEnAttente() {
		return DemandeCongeEnAttente;
	}

	public void setDemandeCongeEnAttente(List<DemandeCongeEnAttente> demandeCongeEnAttente) {
		DemandeCongeEnAttente = demandeCongeEnAttente;
	}



	@Size(min = 8 ,message = "Le mot de passe doit etre au minimum 8 caractéres")
    @NotBlank(message= "Veuillez saisir un mot de passe")
    @Column(name = "mdp")
    private String mdp;
    
    @OneToMany( mappedBy = "user")
    @JsonIgnore
    private List<Conges> conges;
    
    @OneToMany( mappedBy = "user")
    private List<DemandeCongeEnAttente> DemandeCongeEnAttente;
    
    @OneToMany( mappedBy = "user")   
    @JsonIgnore
    private List<Presence> presences;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "departements_aux_quels_il_appartient") // Colonne de clé étrangère dans la table User
    private StructureHierarchique DepartementsAuxQuelsIlAppartient;
 
    public StructureHierarchique getDepartementsAuxQuelsIlAppartient() {
		return DepartementsAuxQuelsIlAppartient;
	}

	public void setDepartementsAuxQuelsIlAppartient(StructureHierarchique departementsAuxQuelsIlAppartient) {
		DepartementsAuxQuelsIlAppartient = departementsAuxQuelsIlAppartient;
	}



	@OneToMany(mappedBy = "responsable")
    @JsonIgnore
    private List<responsables> responsables;

	public List<responsables> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<responsables> responsables) {
		this.responsables = responsables;
	}

	public User() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Conges> getConges() {
		return conges;
	}

	public void setConges(List<Conges> conges) {
		this.conges = conges;
	}

	public List<Presence> getPresences() {
		return presences;
	}

	public void setPresences(List<Presence> presences) {
		this.presences = presences;
	}



	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Long getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(Long numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

//	public int getIdResponsable() {
//		return idResponsable;
//	}
//
//	public void setIdResponsable(int idResponsable) {
//		this.idResponsable = idResponsable;
//	}

//	public int getIdDepartement() {
//		return idDepartement;
//	}
//
//	public void setIdDepartement(int idDepartement) {
//		this.idDepartement = idDepartement;
//	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}



	public User(Long idUser, String nom, String prenom, String role, String cin, Long numeroTelephone, String email,
			String genre, int idResponsable, int idDepartement, Date dateDeNaissance, String login, String mdp) {
		super();
		this.idUser = idUser;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.cin = cin;
		this.numeroTelephone = numeroTelephone;
		this.email = email;
		this.genre = genre;
		//this.idResponsable = idResponsable;
	//	this.idDepartement = idDepartement;
		this.dateDeNaissance = dateDeNaissance;
		this.login = login;
		this.mdp = mdp;
	}


	



}
