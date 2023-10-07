package model;

public class Ville {

	private  int  id_ville;
	private  int id_pays;
	private String libelle;
	private  double geolong;
	private  double geolat;
	private String etat;
	private String date_enregistre;
	private String derniere_modif;
	public int getId_ville() {
		return id_ville;
	}
	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}
	public int getId_pays() {
		return id_pays;
	}
	public void setId_pays(int id_pays) {
		this.id_pays = id_pays;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getGeolong() {
		return geolong;
	}
	public void setGeolong(double geolong) {
		this.geolong = geolong;
	}
	public double getGeolat() {
		return geolat;
	}
	public void setGeolat(double geolat) {
		this.geolat = geolat;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDate_enregistre() {
		return date_enregistre;
	}
	public void setDate_enregistre(String date_enregistre) {
		this.date_enregistre = date_enregistre;
	}
	public String getDerniere_modif() {
		return derniere_modif;
	}
	public void setDerniere_modif(String derniere_modif) {
		this.derniere_modif = derniere_modif;
	}
	public Ville() {
		super();
	}
	

	
	
	
	
	
	
	
}
