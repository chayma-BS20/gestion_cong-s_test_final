package tn.esprit.templatespring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "presences")
public class Presence implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presence")
    private int idPresence;
	
	@JsonFormat(pattern ="yyyy-MM-dd")
	@Column(name ="date_pointage")
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datePointage;

	
	   @JsonFormat(pattern = "HH:mm:ss")
	    @Temporal(TemporalType.TIME) // Use TIME instead of DATE
	    @Column(name = "heure_pointage")
	    private Date heurePointage;

     @ManyToOne
    // @JoinColumn(name = "user_id")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     private User user;
    
	public int getIdPresence() {
		return idPresence;
	}

	public void setIdPresence(int idPresence) {
		this.idPresence = idPresence;
	}

	public Date getDatePointage() {
		return datePointage;
	}

	public void setDatePointage(Date datePointage) {
		this.datePointage = datePointage;
	}

	public Date getHeurePointage() {
		return heurePointage;
	}

	public void setHeurePointage(Date heurePointage) {
		this.heurePointage = heurePointage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Presence [idPresence=" + idPresence + ", datePointage=" + datePointage + ""
				+ ", heurePointage="+ heurePointage
				+ ", user=" + user + "]";
	}

	public Presence(int idPresence, Date datePointage, Date heurePointage, User user) {
		super();
		this.idPresence = idPresence;
		this.datePointage = datePointage;
		//this.heurePointage = heurePointage;
		this.user = user;
	}
    public Presence () {}
}
