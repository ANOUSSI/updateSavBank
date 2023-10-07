package model;

import java.util.Date;

public class Client {
	private String idmvtcaisse;
	private long idpersonnel;
	private long idclient;
	private long idrubriquemvcaisse;
	private long idcaisse;
	private long idguichet;
	private long id_compte;
	private String code;
	private String typemvt;
	private int montant;
	private String reference;
	private String observation;
	private String observationen;
	private String datevaleur;
	private long coef;
	private String datemvt;
	private boolean valide;
	private boolean paye;
	private String datepaye;
	private String etat;
	private String date_enregistre;
	private String derniere_modif;

	/*                             */

	private String contact;
	private String fax;
	private String bp;
	private String email;
	private String site_web;
	private String domaine;
	private long geolong;
	private long geolat;
	private String nom;
	private String prenom;
	private String date_naissance;
	private String lieunaissance;
	private boolean nevers;
	private String sexe;
	private String cni;
	private String photo;
	private String photo_relatif;
	private String nompernompere;
	private String nomprenommere;
	private String pernompere;
	private String prenommere;

	private long idrue;
	private int idarrondissement;
	private int idtypeclient;
	private int id_quartier;
	private int arr_idarrondissement;
	private int id_ville;
	private int idagence;
	private int id_statut_matrimonial;
	private int id_pays;

	private long coden;
	private String cite;
	private String ipost;
	private boolean active;
	private String nomcomplet;
	private String numero;
	private String nature;
	private String imatriculation;
	private String presentation;
	private String presentationen;
	private String raisonsociale;
	private double capitalsocial;
	private String datecreation;
	private Date datenaissance;
	private String statut;
	private String slogan;
	private String sloganeng;
	private String responsable;
	private String login;
	private double solde_mouvement;
	private double solde_client;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public double getSolde_client() {
		return solde_client;
	}

	public void setSolde_client(double solde_client) {
		this.solde_client = solde_client;
	}

	public double getSolde_mouvement() {
		return solde_mouvement;
	}

	public void setSolde_mouvement(double solde_mouvement) {
		this.solde_mouvement = solde_mouvement;
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

	public long getGeolong() {
		return geolong;
	}

	public void setGeolong(long geolong) {
		this.geolong = geolong;
	}

	public long getGeolat() {
		return geolat;
	}

	public void setGeolat(long geolat) {
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

	public long getIdrue() {
		return idrue;
	}

	public void setIdrue(long idrue) {
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

	public int getArr_idarrondissement() {
		return arr_idarrondissement;
	}

	public void setArr_idarrondissement(int arr_idarrondissement) {
		this.arr_idarrondissement = arr_idarrondissement;
	}

	public int getId_ville() {
		return id_ville;
	}

	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}

	public int getIdagence() {
		return idagence;
	}

	public void setIdagence(int idagence) {
		this.idagence = idagence;
	}

	public int getId_statut_matrimonial() {
		return id_statut_matrimonial;
	}

	public void setId_statut_matrimonial(int id_statut_matrimonial) {
		this.id_statut_matrimonial = id_statut_matrimonial;
	}

	public int getId_pays() {
		return id_pays;
	}

	public void setId_pays(int id_pays) {
		this.id_pays = id_pays;
	}

	public long getCoden() {
		return coden;
	}

	public void setCoden(long coden) {
		this.coden = coden;
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public String getIpost() {
		return ipost;
	}

	public void setIpost(String ipost) {
		this.ipost = ipost;
	}



	public String getNomcomplet() {
		return nomcomplet;
	}

	public void setNomcomplet(String nomcomplet) {
		this.nomcomplet = nomcomplet;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getImatriculation() {
		return imatriculation;
	}

	public void setImatriculation(String imatriculation) {
		this.imatriculation = imatriculation;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getPresentationen() {
		return presentationen;
	}

	public void setPresentationen(String presentationen) {
		this.presentationen = presentationen;
	}

	public String getRaisonsociale() {
		return raisonsociale;
	}

	public void setRaisonsociale(String raisonsociale) {
		this.raisonsociale = raisonsociale;
	}

	public double getCapitalsocial() {
		return capitalsocial;
	}

	public void setCapitalsocial(double capitalsocial) {
		this.capitalsocial = capitalsocial;
	}

	public String getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getSloganeng() {
		return sloganeng;
	}

	public void setSloganeng(String sloganeng) {
		this.sloganeng = sloganeng;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
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

	public String getDroits() {
		return droits;
	}

	public void setDroits(String droits) {
		this.droits = droits;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}

	public String getConnexion() {
		return connexion;
	}

	public void setConnexion(String connexion) {
		this.connexion = connexion;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public boolean isDefaut() {
		return defaut;
	}

	public void setDefaut(boolean defaut) {
		this.defaut = defaut;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getSoldestring() {
		return soldestring;
	}

	public void setSoldestring(String soldestring) {
		this.soldestring = soldestring;
	}

	public double getSoldeinit() {
		return soldeinit;
	}

	public void setSoldeinit(double soldeinit) {
		this.soldeinit = soldeinit;
	}

	public double getInteretan() {
		return interetan;
	}

	public void setInteretan(double interetan) {
		this.interetan = interetan;
	}

	public double getInteretnet() {
		return interetnet;
	}

	public void setInteretnet(double interetnet) {
		this.interetnet = interetnet;
	}

	private String mdp;
	private String droits;
	private boolean actif;
	private boolean cloture;
	private String connexion;
	private String langue;
	private boolean defaut;
	private double solde;
	private String soldestring;
	private double soldeinit;
	private double interetan;
	private double interetnet;

	/*                             */

	public String getIdmvtcaisse() {
		return idmvtcaisse;
	}

	public void setIdmvtcaisse(String idmvtcaisse) {
		this.idmvtcaisse = idmvtcaisse;
	}

	public long getIdpersonnel() {
		return idpersonnel;
	}

	public void setIdpersonnel(long idpersonnel) {
		this.idpersonnel = idpersonnel;
	}

	public long getIdclient() {
		return idclient;
	}

	public void setIdclient(long idclient) {
		this.idclient = idclient;
	}

	public long getIdrubriquemvcaisse() {
		return idrubriquemvcaisse;
	}

	public void setIdrubriquemvcaisse(long idrubriquemvcaisse) {
		this.idrubriquemvcaisse = idrubriquemvcaisse;
	}

	public long getIdcaisse() {
		return idcaisse;
	}

	public void setIdcaisse(long idcaisse) {
		this.idcaisse = idcaisse;
	}

	public long getIdguichet() {
		return idguichet;
	}

	public void setIdguichet(long idguichet) {
		this.idguichet = idguichet;
	}

	public long getId_compte() {
		return id_compte;
	}

	public void setId_compte(long id_compte) {
		this.id_compte = id_compte;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypemvt() {
		return typemvt;
	}

	public void setTypemvt(String typemvt) {
		this.typemvt = typemvt;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservationen() {
		return observationen;
	}

	public void setObservationen(String observationen) {
		this.observationen = observationen;
	}

	public String getDatevaleur() {
		return datevaleur;
	}

	public void setDatevaleur(String datevaleur) {
		this.datevaleur = datevaleur;
	}

	public long getCoef() {
		return coef;
	}

	public void setCoef(long coef) {
		this.coef = coef;
	}

	public String getDatemvt() {
		return datemvt;
	}

	public void setDatemvt(String datemvt) {
		this.datemvt = datemvt;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

	public String getDatepaye() {
		return datepaye;
	}

	public void setDatepaye(String datepaye) {
		this.datepaye = datepaye;
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

	public Client() {
		super();
	}

}
