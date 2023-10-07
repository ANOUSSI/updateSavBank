package model;

import java.util.Date;

public class Mvtcaisse {
	
	public long idmvtcaisse;
	public long idclient;
	public String contact;
	public String fax;
	public String bp;
	public String email;
	public String site_web;
	public String domaine;
	public double geolong;
	public double geolat;
	public String nom;
	public String prenom;
	public String date_naissance;
	public String lieunaissance;
	public boolean nevers;
	public String sexe;
	public String cni;
	public String photo;
	public String photo_relatif;
	public String nompernompere;
	public String nomprenommere;
	public String pernompere;
	public String prenommere;
	public String etat;
	public String date_enregistre;
	public String derniere_modif;
	public int idrue;
	public int idarrondissement;
	public int idtypeclient;
	public int id_quartier;
	public int solde;
	public String cloture;
	public String actif;
	public String responsable;
	public String agenceCode;
	public String ipost;
	public String coden;
	public int idagence;
	public String agence;
	public String mvt_datemvt;
	public int mvt_montant;
	public String rubriquemvcaisse_code;
	public String typemvt;
	public String typedebitcredit;
	public long idrubriquemvcaisse;
	public Date datemvt;
	public String observation;
	public boolean valide;
    public String date_enregis;
	public String codemouv;
	
	public String getCodemouv() {
		return codemouv;
	}

	public void setCodemouv(String codemouv) {
		this.codemouv = codemouv;
	}

	public String getDate_enregis() {
		return date_enregis;
	}

	public void setDate_enregis(String date_enregis) {
		this.date_enregis = date_enregis;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public int getIdagence() {
		return idagence;
	}

	public void setIdagence(int idagence) {
		this.idagence = idagence;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDatemvt() {
		return datemvt;
	}

	public void setDatemvt(Date datemvt) {
		this.datemvt = datemvt;
	}

	public long getIdrubriquemvcaisse() {
		return idrubriquemvcaisse;
	}

	public void setIdrubriquemvcaisse(long idrubriquemvcaisse) {
		this.idrubriquemvcaisse = idrubriquemvcaisse;
	}

	public String getTypedebitcredit() {
		return typedebitcredit;
	}

	public void setTypedebitcredit(String typedebitcredit) {
		this.typedebitcredit = typedebitcredit;
	}
	
	public String getLibelleRubrique( long rubrique) {
		if(rubrique==21)
			return  "INT";
		if(rubrique==22)
			return "PV";
		if(rubrique==23)
			return "RT";
		if(rubrique==24)
			return "FTC";
		if(rubrique==25)
			return "VU";
		return "";
					
	}

	public String getTypemvt() {
		return typemvt;
	}

	public void setTypemvt(String typemvt) {
		this.typemvt = typemvt;
	}

	public long getIdmvtcaisse() {
		return idmvtcaisse;
	}

	public void setIdmvtcaisse(long idmvtcaisse) {
		this.idmvtcaisse = idmvtcaisse;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public String getCloture() {
		return cloture;
	}

	public void setCloture(String cloture) {
		this.cloture = cloture;
	}

	public String getActif() {
		return actif;
	}

	public void setActif(String actif) {
		this.actif = actif;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getAgenceCode() {
		return agenceCode;
	}

	public void setAgenceCode(String agenceCode) {
		this.agenceCode = agenceCode;
	}

	public String getIpost() {
		return ipost;
	}

	public void setIpost(String ipost) {
		this.ipost = ipost;
	}

	public String getCoden() {
		return coden;
	}

	public void setCoden(String coden) {
		this.coden = coden;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public String getMvt_datemvt() {
		return mvt_datemvt;
	}

	public void setMvt_datemvt(String mvt_datemvt) {
		this.mvt_datemvt = mvt_datemvt;
	}

	public int getMvt_montant() {
		return mvt_montant;
	}

	public void setMvt_montant(int mvt_montant) {
		this.mvt_montant = mvt_montant;
	}

	public String getRubriquemvcaisse_code() {
		return rubriquemvcaisse_code;
	}

	public void setRubriquemvcaisse_code(String rubriquemvcaisse_code) {
		this.rubriquemvcaisse_code = rubriquemvcaisse_code;
	}

	public long getIdclient() {
		return idclient;
	}

	public void setIdclient(long idclient) {
		this.idclient = idclient;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite_web() {
		return site_web;
	}

	public void setSite_web(String site_web) {
		this.site_web = site_web;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getLieunaissance() {
		return lieunaissance;
	}

	public void setLieunaissance(String lieunaissance) {
		this.lieunaissance = lieunaissance;
	}

	public boolean isNevers() {
		return nevers;
	}

	public void setNevers(boolean nevers) {
		this.nevers = nevers;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto_relatif() {
		return photo_relatif;
	}

	public void setPhoto_relatif(String photo_relatif) {
		this.photo_relatif = photo_relatif;
	}

	public String getNompernompere() {
		return nompernompere;
	}

	public void setNompernompere(String nompernompere) {
		this.nompernompere = nompernompere;
	}

	public String getNomprenommere() {
		return nomprenommere;
	}

	public void setNomprenommere(String nomprenommere) {
		this.nomprenommere = nomprenommere;
	}

	public String getPernompere() {
		return pernompere;
	}

	public void setPernompere(String pernompere) {
		this.pernompere = pernompere;
	}

	public String getPrenommere() {
		return prenommere;
	}

	public void setPrenommere(String prenommere) {
		this.prenommere = prenommere;
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

	public int getIdrue() {
		return idrue;
	}

	public void setIdrue(int idrue) {
		this.idrue = idrue;
	}

	public int getIdarrondissement() {
		return idarrondissement;
	}

	public void setIdarrondissement(int idarrondissement) {
		this.idarrondissement = idarrondissement;
	}

	public int getIdtypeclient() {
		return idtypeclient;
	}

	public void setIdtypeclient(int idtypeclient) {
		this.idtypeclient = idtypeclient;
	}

	public int getId_quartier() {
		return id_quartier;
	}

	public void setId_quartier(int id_quartier) {
		this.id_quartier = id_quartier;
	}

	public Mvtcaisse() {
		super();
	}

}
