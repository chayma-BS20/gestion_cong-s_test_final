package tn.esprit.templatespring.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "demandes_conge_en_attente")
public class DemandeCongeEnAttente {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_conges")
	    private Long idConges;
	    
//		@JsonProperty("date_debut")
	    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
	    @JsonIgnore
	    @Column(name = "date_debut")
	    @Temporal(TemporalType.DATE)
	  //  @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dateDebut;
	    
	    
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    @JsonIgnore
	    @Column(name = "date_fin")
	    @Temporal(TemporalType.DATE)
	    private Date dateFin;
	    
	    
	    
	    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
	    @Column(name = "date_creation", nullable = false, updatable = false)
	    @Temporal(TemporalType.DATE)
	    private Date dateCreation;
	    @PrePersist
	    protected void onCreate() {
	        dateCreation = new Date();
	    }
	    

	    @Column(name = "etat")
	    private String etat;
	    
	    
	    @Column(name = "type")
	    private String type;
	    
	 


		@ManyToOne
		@JsonIgnore
		@JoinColumn(name = "user_id")
	    private User user;
		
	   public	DemandeCongeEnAttente(){}
	   public DemandeCongeEnAttente(Long idConges, Date dateDebut, Date dateFin, Date dateCreation, String etat,
			String type, User user) {
		super();
		this.idConges = idConges;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.type = type;
		this.user = user;
	}

		@Override
		public String toString() {
			return "DemandeCongeEnAttente [idConges=" + idConges + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
					+ ", dateCreation=" + dateCreation + ", etat=" + etat + ", type=" + type + ", user=" + user + "]";
		}




		public Long getIdConges() {
			return idConges;
		}




		public void setIdConges(Long idConges) {
			this.idConges = idConges;
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




		public Date getDateCreation() {
			return dateCreation;
		}




		public void setDateCreation(Date dateCreation) {
			this.dateCreation = dateCreation;
		}




		public String getEtat() {
			return etat;
		}




		public void setEtat(String etat) {
			this.etat = etat;
		}




		public String getType() {
			return type;
		}




		public void setType(String type) {
			this.type = type;
		}




		public User getUser() {
			return user;
		}




		public void setUser(User user) {
			this.user = user;
		}
}
