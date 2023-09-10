package tn.esprit.templatespring.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "conges")
public class Conges implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conges")
    private Long idConges;
    
//	@JsonProperty("date_debut")
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
    
    
    
    @Column(name = "date_creation", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateCreation;

    @Column(name = "date_accord", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date AccordDate;

    @PrePersist
    protected void onCreate() {
        dateCreation = new Date();
        AccordDate = new Date();
    }

    public Date getAccordDate() {
		return AccordDate;
	}

	public void setAccordDate(Date accordDate) {
		AccordDate = accordDate;
	}

	@Column(name = "etat")
    private String etat;
    
    
    @Column(name = "type")
    private String type;
    
 


	@ManyToOne

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")


    private User user;

	public Long getIdConges() {
		return idConges;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setIdConges(Long idConges) {
		this.idConges = idConges;
	}
	public Conges() {
	}


	public Conges(Long idConges, Date dateDebut, Date dateFin, Date dateCreation, String etat, String type) {
		super();
		this.idConges = idConges;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.type = type;
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
		this.etat = "En attente";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
  
    
}

