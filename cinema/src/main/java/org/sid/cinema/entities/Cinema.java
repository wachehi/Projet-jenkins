package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Cinema  implements Serializable{
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 7307996561304857309L;
@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double longitude, latitude, altitude;
  private int nombreSalles;
  @OneToMany(mappedBy="cinema")
  @JsonProperty(access=Access.WRITE_ONLY)
  private Collection<Salle> salles;
  @ManyToOne
  private Ville ville;
  
public Cinema() {
	super();
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getLongitude() {
	return longitude;
}

public void setLongitude(double longitude) {
	this.longitude = longitude;
}

public double getLatitude() {
	return latitude;
}

public void setLatitude(double latitude) {
	this.latitude = latitude;
}

public double getAltitude() {
	return altitude;
}

public void setAltitude(double altitude) {
	this.altitude = altitude;
}

public int getNombreSalles() {
	return nombreSalles;
}

public void setNombreSalles(int nombreSalles) {
	this.nombreSalles = nombreSalles;
}

public Collection<Salle> getSalles() {
	return salles;
}

public void setSalles(Collection<Salle> salles) {
	this.salles = salles;
}

public Ville getVille() {
	return ville;
}

public void setVille(Ville ville) {
	this.ville = ville;
}
  
  

}
