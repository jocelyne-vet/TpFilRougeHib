 package bo.cinemas;



import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="films")
public class Film {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="titre")
	private String nom;
	
	@OneToMany( fetch=FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "film")
//	private List<Seance> seances;
	private Set<Seance> seances;

	private String description;
	private int duree;
	@Transient
	private String fDuree;
	private String affiche;
	@Column(name="age_minimum")
	private int ageMinimum;
	
	
	
	public Film() {
		// TODO Auto-generated method stub

	}
	
	public Film(String nom, String description, int duree) {
		this.nom = nom;
		this.description = description;
		this.duree = duree;
	}
	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAffiche() {
		return affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public int getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(int ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	


//	public List<Seance> getSeances() {
//		return seances;
//	}
//
//	public void setSeances(List<Seance> seances) {
//		this.seances = seances;
//	}

	public String getfDuree() {
		if(fDuree==null) {
			Integer heure = duree/60;
			Integer min  = duree%60;
			String sMin = "";
			String sHeure = "";
			if(min<10) {
				sMin = "0"+String.valueOf(min);
			}else {
				sMin = String.valueOf(min);
			}
			sHeure = String.valueOf(heure);
			if(sHeure.length()<2) {
				sHeure = "0"+String.valueOf(heure); 
			}
			fDuree = sHeure+"h"+sMin;
		}
		return fDuree;
	}

	public void setfDuree(String fDuree) {
		this.fDuree = fDuree;
		if(fDuree!=null && !fDuree.isBlank()) {
			
			String heure = fDuree.substring(0,fDuree.indexOf(":") );
			String min = fDuree.substring(fDuree.indexOf("h"), fDuree.length());
			
			this.duree = Integer.parseInt(heure)*60+Integer.parseInt(min);
		}
	}

	
	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(LinkedHashSet<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}
	
	
}
