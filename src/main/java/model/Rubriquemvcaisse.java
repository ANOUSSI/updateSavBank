package model;

public class Rubriquemvcaisse {
	public long idrubriquemvcaisse ;
	public int   idagence ;
	public String   code ;
	public String   libelle ;
	public String   label ;
	public String   type ;
	public String   description ;
	public String   descriptionen ;
	public String   etat ;
	public String   date_enregistre;
	public String   derniere_modif;
	public String   debiorcredit;
	
	
	public String getDebiorcredit() {
		return debiorcredit;
	}
	public void setDebiorcredit(String debiorcredit) {
		this.debiorcredit = debiorcredit;
	}
	public Rubriquemvcaisse() {
		super();
	}
	public long getIdrubriquemvcaisse() {
		return idrubriquemvcaisse;
	}
	public void setIdrubriquemvcaisse(long idrubriquemvcaisse) {
		this.idrubriquemvcaisse = idrubriquemvcaisse;
	}
	public int getIdagence() {
		return idagence;
	}
	public void setIdagence(int idagence) {
		this.idagence = idagence;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionen() {
		return descriptionen;
	}
	public void setDescriptionen(String descriptionen) {
		this.descriptionen = descriptionen;
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
	

}
