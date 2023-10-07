package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Global.GlobalVar;
import Global.MyConnexion;
import model.Agence;
import model.Mvtcaisse;
import model.Rubriquemvcaisse;
import model.Client;
import model.User;
import model.Ville;;

public class UserService implements InterService {
	MyConnexion connexion;

	PreparedStatement statement;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
	SimpleDateFormat sdff = new SimpleDateFormat("yyyy MMMM dd");
	// Formatter pour le format de date "DD/MM/YYYY"
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	// Comparator personnalisé pour trier en fonction de mvt_datemvt
	Comparator<Mvtcaisse> comparator = new Comparator<Mvtcaisse>() {
		@Override
		public int compare(Mvtcaisse m1, Mvtcaisse m2) {
			try {
				java.util.Date date2 = (java.util.Date) dateFormat.parse(m1.getMvt_datemvt());
				java.util.Date date1 = (java.util.Date) dateFormat.parse(m2.getMvt_datemvt());
				return date2.compareTo(date1);
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		}
	};

	Comparator<Client> comparatorclient = new Comparator<Client>() {
		@Override
		public int compare(Client m1, Client m2) {
			try {
				java.util.Date date1 = (java.util.Date) dateFormat.parse(m1.getDatecreation());
				java.util.Date date2 = (java.util.Date) dateFormat.parse(m2.getDatecreation());
				return date2.compareTo(date1);
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		}
	};

	public UserService() {
		super();
		connexion = new MyConnexion();
	}

	@Override
	public User get(String login, String password) {
		String query = "SELECT * FROM public.user WHERE login=? and password=?";
		// System.out.println("requette execute");
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion == null) {
				return null;
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				statement.setString(1, login);
				statement.setString(2, password);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					user.setActif( rs.getBoolean("actif"));
					user.setName(rs.getString("name"));
					user.setName(rs.getString("prenom"));
					user.setSolde(rs.getInt("role"));
					user.setName(rs.getString("createur"));
					user.setName(rs.getString("datecreation"));
					user.setName(rs.getString("datemodification"));
				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
//
			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}
		System.out.println("Je me connect dejag " + user.getName());

		return user;

	}
	
	public User getActi(String login, String password) {
	
		User user = null;
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			boolean  con = connexion.authenticate(login,  password);
		     if(con) {
		    	  user = new User();
		    	  user.setLogin(login);
		    	  user.setName(password);	  
		     }

		

		return user;

	}
	
	
	
	
	@Override
	public List<User> getListUser() {
		List<User> listUsers = new ArrayList<>();
		String query = "SELECT * FROM user order by name;";

		// System.out.println(query);
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setLogin(rs.getString("login"));
					user.setRole(rs.getString("role"));
					user.setActif( rs.getBoolean("actif"));
					user.setName(rs.getString("nom"));
					user.setPrenom(rs.getString("createur"));
					user.setDatecreation(rs.getString("datecreation"));
					user.setDatemodification(rs.getString("datemodification"));
					listUsers.add(user);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		// tris des mouvent
		//Collections.sort(listMovementCaisse, comparator);

		return listUsers;

	}
	
	
	
	
	
	
	
	// Requete très importante 
	
	
	// Requete qui ferme toute les connexion
	/*SELECT pg_terminate_backend (pg_stat_activity.pid)
	FROM pg_stat_activity
	WHERE pg_stat_activity.datname = 'padlock_db';
	
	*/
	

	@Override
	public List<Agence> getListAgence() {
		List<Agence> listMovementCaisse = new ArrayList<>();
		String query = "SELECT * FROM public.agence\r\n" + "ORDER BY raisonsociale ASC ";

		// System.out.println(query);
		Agence agence = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					agence = new Agence();
					agence.setIdagence(rs.getInt("idagence"));
					agence.setCode(rs.getString("code"));
					agence.setContact(rs.getString("contact"));
					agence.setFax(rs.getString("fax"));
					agence.setBp(rs.getString("bp"));
					agence.setEmail(rs.getString("email"));
					agence.setSite_web(rs.getString("site_web"));
					agence.setDomaine(rs.getString("domaine"));
					agence.setGeolong(rs.getInt("geolong"));
					agence.setGeolat(rs.getInt("geolat"));
					agence.setIdrue(rs.getInt("idrue"));
					agence.setIdarrondissement(rs.getInt("idarrondissement"));
					agence.setId_quartier(rs.getInt("id_quartier"));
					agence.setId_ville(rs.getInt("id_ville"));
					agence.setLibelle(rs.getString("libelle"));
					agence.setLabel(rs.getString("label"));
					agence.setQualite(rs.getString("qualite"));
					agence.setQuality(rs.getString("quality"));
					agence.setRaisonsociale(rs.getString("raisonsociale"));
					agence.setNumcobtribuable(rs.getString("numcobtribuable"));
					agence.setCapitalsocial(rs.getInt("capitalsocial"));
					agence.setDatecreation(rs.getString("datecreation"));
					agence.setStatut(rs.getString("statut"));
					agence.setSlogan(rs.getString("slogan"));
					agence.setSloganeng(rs.getString("sloganeng"));
					agence.setLogo(rs.getString("logo"));
					agence.setLogorelatif(rs.getString("logorelatif"));
					agence.setResponsable(rs.getString("responsable"));
					agence.setEtat(rs.getString("etat"));
					agence.setDate_enregistre(rs.getString("date_enregistre"));
					agence.setDerniere_modif(rs.getString("derniere_modif"));
					listMovementCaisse.add(agence);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listMovementCaisse;
	}

	@Override
	public List<Ville> getVille() {
		List<Ville> listVilles = new ArrayList<>();
		String query = "SELECT * FROM public.ville\r\n" + "	ORDER BY id_ville ASC ";

		// System.out.println(query);
		Ville ville = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					ville = new Ville();
					ville.setId_ville(rs.getInt("id_ville"));
					ville.setId_pays(rs.getInt("id_pays"));
					ville.setLibelle(rs.getString("libelle"));
					ville.setGeolong(rs.getDouble("geolong"));
					ville.setGeolat(rs.getDouble("geolat"));
					ville.setEtat(rs.getString("etat"));
					ville.setDate_enregistre(rs.getString("date_enregistre"));
					ville.setDerniere_modif(rs.getString("derniere_modif"));
					listVilles.add(ville);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listVilles;
	}

	@Override
	public List<Agence> getListAgence(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agence> getListAgence(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agence getAgence(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agence getAgence(String Numero_Bordereau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agence saveAgence(Agence Agence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAgence(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAgence(String Numero_Bordereau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Client> getListClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getListClient(String valeurEntree) {

		List<Client> listClients = new ArrayList<>();

		String query = "select ag.code as codeagence, ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide, clt.nom,clt.lieunaissance, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere \r\n"
				+ "mvt.idclient,to_char(min(mvt.datemvt),'DD-MM-YYYY') MIN_DATE,\r\n"
				+ "to_char( max(mvt.datemvt),'DD-MM-YYYY') MAX_DATE,solde solde_client ,\r\n"
				+ "sum(case when mvt.idrubriquemvcaisse in (21,22,25) then  mvt.montant else -mvt.montant end)\r\n"
				+ "Solde_Mouvement, actif from  agence ag,mvtcaisse mvt, client clt  \r\n"
				+ "where clt.idagence = ag.idagence and \r\n" + "clt.idclient = mvt.idclient and\r\n"
				+ "mvt.etat in( 'Actif','ACTIF') and \r\n" + "clt.etat in( 'Actif','ACTIF') and  \r\n"
				+ "clt.idclient in (select idclient from client  where  actif = 't' )and \r\n" + "(clt.nom like   '"
				+ valeurEntree + "' or\r\n" + "clt.prenom like '" + valeurEntree + "' or\r\n" + "clt.pernompere like '"
				+ valeurEntree + "' or\r\n" + "clt.nompernompere like '" + valeurEntree + "' or\r\n"
				+ "clt.prenommere like '" + valeurEntree + "' or\r\n" + "clt.nomprenommere like '" + valeurEntree
				+ "')\r\n" + "group by mvt.idclient,clt.ipost,clt.code,clt.date_naissance,clt.coden,\r\n"
				+ "clt.pernompere,clt.prenommere,clt.nompernompere,clt.nomprenommere,clt.nature,solde, \r\n"
				+ "clt.nom,clt.valide, clt.prenom,actif,ag.code, ag.raisonsociale,clt.idclient;";
		// System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}

			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}
				while (rs.next()) {
					client = new Client();
					client.setIdclient(rs.getLong("idclient"));
					client.setRaisonsociale(rs.getString("raisonsociale"));

					client.setContact(rs.getString("contact"));
					client.setFax(rs.getString("fax"));
					client.setBp(rs.getString("bp"));
					client.setEmail(rs.getString("email"));
					client.setSite_web(rs.getString("site_web"));
					client.setDomaine(rs.getString("domaine")); // Assurez-vous que "domaine" est correct
					// Continuer en ajoutant les autres attributs de la classe "Client"
					client.setGeolong(rs.getLong("geolong"));
					client.setGeolat(rs.getLong("geolat"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setLieunaissance(rs.getString("lieunaissance"));
					client.setNevers(rs.getBoolean("nevers"));
					client.setSexe(rs.getString("sexe"));
					client.setCni(rs.getString("cni"));
					// client.setPhoto(rs.getBlob("photo")); // Si le type est BLOB, sinon ajustez
					// en conséquence
					client.setPhoto_relatif(rs.getString("photo_relatif"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setEtat(rs.getString("etat"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setDerniere_modif(rs.getString("derniere_modif"));
					client.setIdrue(rs.getLong("idrue"));
					client.setIdarrondissement(rs.getInt("idarrondissement"));
					client.setIdtypeclient(rs.getInt("idtypeclient"));
					client.setId_quartier(rs.getInt("id_quartier"));
					client.setArr_idarrondissement(rs.getInt("arr_idarrondissement"));
					client.setId_ville(rs.getInt("id_ville"));
					client.setIdagence(rs.getInt("idagence"));
					// client.setId_statut_matrimonial(rs.getLong("id_statut_matrimonial"));
					/// client.setId_pays(rs.getLong("id_pays"));
					client.setCode(rs.getString("code"));
					// client.setCoden(rs.getString("coden"));
					client.setCite(rs.getString("cite"));
					client.setIpost(rs.getString("ipost"));
					client.setActive(rs.getBoolean("activite"));
					client.setNomcomplet(rs.getString("nomcomplet"));
					client.setNumero(rs.getString("numero"));
					client.setNature(rs.getString("nature"));
					client.setImatriculation(rs.getString("imatriculation"));
					client.setPresentation(rs.getString("presentation"));
					client.setPresentationen(rs.getString("presentationen"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setCapitalsocial(rs.getDouble("capitalsocial"));
					client.setDatecreation(rs.getString("datecreation"));
					client.setStatut(rs.getString("statut"));
					client.setSlogan(rs.getString("slogan"));
					client.setSloganeng(rs.getString("sloganeng"));
					client.setResponsable(rs.getString("responsable"));
					client.setLogin(rs.getString("login"));
					client.setMdp(rs.getString("mdp"));
					client.setDroits(rs.getString("droits"));
					client.setActif(rs.getBoolean("actif"));
					client.setCloture(rs.getBoolean("cloture"));
					client.setLangue(rs.getString("langue"));
					client.setDefaut(rs.getBoolean("defaut"));
					client.setSolde(rs.getDouble("solde"));
					client.setSoldestring(rs.getString("soldestring"));
					client.setSoldeinit(rs.getDouble("soldeinit"));
					client.setInteretan(rs.getDouble("interetan"));
					client.setInteretnet(rs.getDouble("interetnet"));
					client.setValide(rs.getBoolean("valide"));
					client.setActif( rs.getBoolean("actif"));
					listClients.add(client);
				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);

		}
		Collections.sort(listClients, comparatorclient);

		return listClients;
	}

	@Override
	public List<Client> getListClient(long idclient) {
		List<Client> listClients = new ArrayList<>(); 
		String query = "select clt.nature, clt.idclient ,ag.idagence,ag.code as codeagence,  ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide,clt.lieunaissance, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where\r\n" + "clt.idagence = ag.idagence and \r\n"
				+ "clt.idclient = '" + idclient + "'";

		System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setLieunaissance(rs.getString("lieunaissance"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));
				//	System.out.println("slfsqlfjlqjflq-------------------------------ddd---------- "+rs.getBoolean("actif"));
					System.out.println("slfsqlfjlqjflq-------------------------------ddd---------- "+client.isActif());

				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}
		List<Mvtcaisse> listMvts = getAllMvtForClient(idclient);
		if (listMvts.size() > 0) {
			double soldeMouvement = calcutSoldeMvt(client.getIdclient(), listMvts);
			client.setSolde_mouvement(soldeMouvement);

		}
		if (client != null)
			listClients.add(client);
		return listClients;
	}

	@Override
	public List<Client> getListClientByNmame(String name) {
		List<Client> listClients = new ArrayList<>();
		String nom = "";
		String prenom = "";
		String[] tbNomPrenom = name.split(" ");
		String nompropre = GlobalVar.concatenateNonEmptyElements(tbNomPrenom);
		nompropre=nompropre.toLowerCase();
		String query = "select  clt.nature,clt.idclient,clt.lieunaissance ,ag.idagence,ag.code as codeagence , ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where\r\n" + "clt.idagence = ag.idagence and \r\n"
				+ "(         LOWER(CONCAT(TRIM(BOTH ' ' FROM clt.nom), ' ', TRIM(BOTH ' ' FROM clt.prenom))) like   '%" + nompropre + "%') order by clt.nom ;";
		
System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));

					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setLieunaissance(rs.getString("lieunaissance"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));
					listClients.add(client);
				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}
		return listClients;
	}

	List<Mvtcaisse> listMvtsupression = new ArrayList<Mvtcaisse>();

	public double calcutSoldeMvt(long idclient, List<Mvtcaisse> listMvts) {

		double MONTANT_POSITIF = 0; // (21,22,25)
		double MONTANT_NEGATIF = 0; // (23,24)
		double SOLDE_MOUVEMENT = 0;

		for (Mvtcaisse mvt : listMvts) {

			if (mvt.getIdclient() == idclient) {
				if (mvt.getIdrubriquemvcaisse() == 21 || mvt.getIdrubriquemvcaisse() == 22
						|| mvt.getIdrubriquemvcaisse() == 25) {
					MONTANT_POSITIF += mvt.getMvt_montant();
				} else {
					if (mvt.getIdrubriquemvcaisse() == 23 || mvt.getIdrubriquemvcaisse() == 24) {
						MONTANT_NEGATIF += mvt.getMvt_montant();
					}
				}
				// le mouvement appartien au client // ajout mvt dans la liste des mvt à
				// supprimer
				// listMvtsupression.add(mvt);
			}

		}

		SOLDE_MOUVEMENT = MONTANT_POSITIF - MONTANT_NEGATIF;

		return SOLDE_MOUVEMENT;
	}

	public List<Mvtcaisse> getAllMvtForClient(long idclient) {
		String query = "select  mvt.idmvtcaisse,  mvt.idclient,  mvt.idrubriquemvcaisse,\r\n"
				+ " mvt.idcaisse,  mvt.idguichet,  mvt.id_compte,  mvt.code,  mvt.typemvt,  mvt.montant,\r\n" + "\r\n"
				+ "  mvt.datemvt,  mvt.valide,\r\n" + " mvt.etat,  mvt.date_enregistre,  mvt.derniere_modif \r\n"
				+ "from mvtcaisse as mvt , agence as ag, client as clt where\r\n" + "mvt.idclient=clt.idclient and \r\n"
				+ "clt.idagence=ag.idagence and\r\n" + "clt.idclient='" + idclient + "' ";

		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();

		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setMvt_montant(rs.getInt("montant"));
					listMovementCaisse.add(mvt);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listMovementCaisse;

	}

	@Override
	public List<Client> getAllClientsAgence(String agence, int PAGE) {
       
		System.out.println(
				".................................JE PASSE ICI LA MAINTENANT ET TRES VITE.......................1");
		List<Client> listClient = new ArrayList<>();
		if (PAGE == 0) {

			GlobalVar.listMvts = getAllMvtForAllClient(agence);
			System.out.println(
					".................................JE PASSE ICI LA MAINTENANT ET TRES VITE.......................2-- "
							+ GlobalVar.listMvts.size());
			GlobalVar.listClients = getAllClientsNotComplete(agence);
		}
		
		
		
		
		int nombreElement=1000;
		int indexdpart = PAGE;
		int indexfin = PAGE + nombreElement;
		List<Client> listClients;
		if(GlobalVar.listClients.size()<nombreElement) {
			listClients = GlobalVar.listClients;
		}else {
			if(indexfin>GlobalVar.listClients.size()) {
				indexfin=GlobalVar.listClients.size();
			}
			listClients = GlobalVar.listClients.subList(indexdpart, indexfin);
		}
		
		
		// recuperation d'une partie du tableau des clients
		System.out.println("GlobalVar.listClients   "+GlobalVar.listClients.size());
		System.out.println("GlobalVar.listClients indexdpart  "+indexdpart);
		System.out.println("GlobalVar.listClients indexfin  "+indexfin);

		
		System.out.println("-------------------------GlobalVar.listClients  de substant ----------------------------------");
		
		System.out.println("GlobalVar.listClients  de substant "+listClients.size());
		
		for (Client client : listClients) {
			double soldeMouvement = calcutSoldeMvt(client.getIdclient(), GlobalVar.listMvts);

			client.setSolde_mouvement(soldeMouvement);
			listClient.add(client);
			/*System.out.println(
					".................................JE PASSE ICI LA MAINTENANT ET TRES VITE ddd.......................6---- "
							+ GlobalVar.listMvts.size());*/
			// GlobalVar.listMvts.removeAll(listMvtsupression);

			// listMvtsupression.clear();
			GlobalVar.PAGE++;
		}

		return listClient;
	}

	@Override
	public List<Client> getAllClientsByName(String name) {
		List<Client> listClient = new ArrayList<>();
		List<Client> listClients = getListClientByNmame(name);

		String arrayOfIdUser = GlobalVar.getArrayIdClient(listClients);
		if (arrayOfIdUser.length() > 2) {
			List<Mvtcaisse> listMvts = getAllMvtByArrayIdClient(arrayOfIdUser);

			for (Client client : listClients) {
				double soldeMouvement = calcutSoldeMvt(client.getIdclient(), listMvts);

				client.setSolde_mouvement(soldeMouvement);
				listClient.add(client);
			}
		}

		return listClient;
	}

	public List<Client> getAllClientsNotComplete(String agence) {
		List<Client> listClients = new ArrayList<>();

		String query = "select  clt.nature,clt.idclient ,ag.idagence,ag.code as codeagence, ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where \r\n" + "ag.idagence=clt.idagence and \r\n"
				+ "ag.raisonsociale like '" + agence + "' order by clt.nom asc ";

		// System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));

					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));
					listClients.add(client);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listClients;
	}

	public List<Mvtcaisse> getAllMvtByArrayIdClient(String arrayIdclient) {
		String query = "select ag.raisonsociale, mvt.idmvtcaisse,  clt.idclient,  mvt.idrubriquemvcaisse,\r\n"
				+ " mvt.idcaisse,  mvt.idguichet,  mvt.id_compte,  mvt.code,  mvt.typemvt,  mvt.montant,\r\n" + "\r\n"
				+ "  mvt.datemvt,  mvt.valide,\r\n" + " mvt.etat,  mvt.date_enregistre,  mvt.derniere_modif \r\n"
				+ "from mvtcaisse as mvt , agence as ag, client as clt where\r\n" + "mvt.idclient=clt.idclient and \r\n"
				+ "clt.idagence=ag.idagence and\r\n" + "clt.idclient IN " + arrayIdclient + ";";
		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();

		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setMvt_montant(rs.getInt("montant"));
					listMovementCaisse.add(mvt);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listMovementCaisse;

	}

	public List<Mvtcaisse> getAllMvtForAllClient(String raisonsocial) {
		String query = "select ag.raisonsociale, mvt.idmvtcaisse,  clt.idclient,  mvt.idrubriquemvcaisse,\r\n"
				+ " mvt.idcaisse,  mvt.idguichet,  mvt.id_compte,  mvt.code,  mvt.typemvt,  mvt.montant,\r\n" + "\r\n"
				+ "  mvt.datemvt,  mvt.valide,\r\n" + " mvt.etat,  mvt.date_enregistre,  mvt.derniere_modif \r\n"
				+ "from mvtcaisse as mvt , agence as ag, client as clt where\r\n" + "mvt.idclient=clt.idclient and \r\n"
				+ "clt.idagence=ag.idagence and\r\n" + "ag.raisonsociale like '" + raisonsocial
				+ "' order by clt.idclient";// remplacement du like de la requette par egal pour gagner quelque second
		System.out.println(query);

		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();

		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setMvt_montant(rs.getInt("montant"));
					listMovementCaisse.add(mvt);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listMovementCaisse;

	}

	@Override
	public Client getClient(long id) {
		// TODO Auto-generated method stub
		String query = "select clt.nature, clt.idclient ,ag.idagence,ag.code as codeagence, ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide,clt.lieunaissance, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where\r\n" + "clt.idagence = ag.idagence and \r\n"
				+ "clt.idclient = '" + id + "'";

		System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}

			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setLieunaissance(rs.getString("lieunaissance"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}
		return client;
	}

	@Override
	public Client getClient(String codeuser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client saveClient(Client Client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteClient(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClient(String Numero_Bordereau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Mvtcaisse> getListMvtcaisse(int offset) {
		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();
		String query = "SELECT \r\n" + "idmvtcaisse,  client.idclient,\r\n"
				+ "  client.nom, mvt.valide as mvtvalide, \r\n" + "  client.prenom, \r\n"
				+ "  to_char(client.date_naissance,'DD/MM/YYYY')date_naissance, to_char(mvt.date_enregistre,'DD/MM/YYYY')date_enregis,\r\n"
				+ "  client.solde, \r\n" + "  client.cloture, \r\n" + "  client.actif, \r\n"
				+ "  client.responsable, \r\n" + "  agence.code,mvt.code as codemouv, mvt.idrubriquemvcaisse,\r\n"
				+ "  client.ipost, \r\n" + "  client.coden, \r\n" + "  client.code, \r\n"
				+ "  agence.raisonsociale Agence,\r\n" + "   to_char(mvt.datemvt,'DD/MM/YYYY')date_datemvt,\r\n"
				+ "   mvt.montant,\r\n" + "   rubriquemvcaisse.code caisecode\r\n" + "   \r\n" + "FROM \r\n"
				+ "  public.client, \r\n" + "  public.agence,\r\n" + "  mvtcaisse mvt,\r\n" + "  rubriquemvcaisse\r\n"
				+ "WHERE \r\n" + "  client.idagence = agence.idagence \r\n"
				+ "  and mvt.idrubriquemvcaisse = rubriquemvcaisse.idrubriquemvcaisse\r\n"
				+ "  and client.idclient = mvt.idclient\r\n" + "ORDER BY client.idclient asc\r\n" + "\r\n"
				+ "-- Pagination : sauter les premières lignes et en retourner un certain nombre\r\n" + "OFFSET '"
				+ offset + "'\r\n" + "LIMIT 100;";

		// System.out.println(query);
		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));

					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setNom(rs.getString("nom") + " " + rs.getString("prenom"));
					mvt.setPrenom(rs.getString("prenom"));
					mvt.setDate_naissance(rs.getString("date_naissance"));
					mvt.setSolde(rs.getInt("solde"));
					mvt.setCloture(rs.getString("cloture"));
					mvt.setActif(rs.getString("actif"));
					mvt.setResponsable(rs.getString("responsable"));
					mvt.setAgenceCode(rs.getString("code"));
					mvt.setIpost(rs.getString("ipost"));
					mvt.setCoden(rs.getString("coden"));
					mvt.setAgence(rs.getString("Agence"));
					mvt.setValide(rs.getBoolean("mvtvalide"));
					mvt.setCodemouv(rs.getString("codemouv"));
					mvt.setDate_enregis(rs.getString("date_enregis"));
					// (rs.getString("date_naissance"));
					//
					/*
					 * System.out.println("mvt.getAgenceCode() "+mvt.getAgenceCode()) ;
					 * System.out.println("mvt.getCoden() "+mvt.getCoden()) ;
					 * System.out.println("mvt.getAgence() "+mvt.getAgence()) ;
					 */

					/// client.setIdclient(rs.getString("name"));
					// client.setAgence(rs.getString("Agence"));
					mvt.setMvt_datemvt(rs.getString("date_datemvt"));
					mvt.setMvt_montant(rs.getInt("montant"));
					mvt.setRubriquemvcaisse_code(rs.getString("caisecode"));
					listMovementCaisse.add(mvt);

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		// tris des mouvent
		Collections.sort(listMovementCaisse, comparator);

		return listMovementCaisse;

	}

	@Override
	public List<Mvtcaisse> getListMvtcaisse(String codeClient) {
		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();
		String query = "";

		if (codeClient != null && !codeClient.isEmpty()) {
			// System.out.println("je ne suis pas null "
			// + "codeClient "+codeClient);
			query = "SELECT \r\n" + "idmvtcaisse,  client.idclient,\r\n" + "  client.nom, mvt.valide as mvtvalide, \r\n"
					+ "  client.prenom, \r\n"
					+ "  to_char(client.date_naissance,'DD/MM/YYYY')date_naissance, to_char(mvt.date_enregistre,'DD/MM/YYYY')date_enregis,\r\n"
					+ "  client.solde, \r\n" + "  client.cloture, \r\n" + "  client.actif, \r\n"
					+ "  client.responsable, \r\n" + "  agence.code,mvt.code as codemouv, mvt.idrubriquemvcaisse, \r\n"
					+ "  client.ipost, \r\n" + "  client.coden, \r\n" + "  client.code, \r\n"
					+ "  agence.raisonsociale Agence,\r\n" + "   to_char(mvt.datemvt,'DD/MM/YYYY')date_datemvt,\r\n"
					+ "   mvt.montant,\r\n" + "   rubriquemvcaisse.code caisecode\r\n" + "   \r\n" + "FROM \r\n"
					+ "  public.client, \r\n" + "  public.agence,\r\n" + "  mvtcaisse mvt,\r\n"
					+ "  rubriquemvcaisse\r\n" + "WHERE \r\n" + "  client.idagence = agence.idagence \r\n"
					+ "  and mvt.idclient = '" + codeClient + "'\r\n"
					+ "  and mvt.idrubriquemvcaisse = rubriquemvcaisse.idrubriquemvcaisse\r\n"
					+ "  and client.idclient = mvt.idclient\r\n" + "ORDER BY client.idclient asc\r\n" + "\r\n;";
		} else {
			int offset = 0;
			query = "SELECT \r\n" + "idmvtcaisse,  client.idclient,\r\n" + "  client.nom, mvt.valide as mvtvalide, \r\n"
					+ "  client.prenom, \r\n"
					+ "  to_char(client.date_naissance,'DD/MM/YYYY')date_naissance, to_char(mvt.date_enregistre,'DD/MM/YYYY')date_enregis,\r\n"
					+ "  client.solde, \r\n" + "  client.cloture, \r\n" + "  client.actif, \r\n"
					+ "  client.responsable, \r\n" + "  agence.code,mvt.code as codemouv, \r\n" + "  client.ipost, \r\n"
					+ "  client.coden, \r\n" + "  client.code, \r\n" + "  agence.raisonsociale Agence,\r\n"
					+ "   to_char(mvt.datemvt,'DD/MM/YYYY')date_datemvt,\r\n" + "   mvt.montant,\r\n"
					+ "   rubriquemvcaisse.code caisecode\r\n" + "   \r\n" + "FROM \r\n" + "  public.client, \r\n"
					+ "  public.agence,\r\n" + "  mvtcaisse mvt,\r\n" + "  rubriquemvcaisse\r\n" + "WHERE \r\n"
					+ "  client.idagence = agence.idagence \r\n"
					+ "  and mvt.idrubriquemvcaisse = rubriquemvcaisse.idrubriquemvcaisse\r\n"
					+ "  and client.idclient = mvt.idclient\r\n" + "ORDER BY client.idclient asc\r\n" + "\r\n"
					+ "-- Pagination : sauter les premières lignes et en retourner un certain nombre\r\n" + "OFFSET '"
					+ offset + "'\r\n" + "LIMIT 100;";
		}

		System.out.println(query);
		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setNom(rs.getString("nom") + " " + rs.getString("prenom"));
					mvt.setPrenom(rs.getString("prenom"));
					mvt.setDate_naissance(rs.getString("date_naissance"));
					mvt.setSolde(rs.getInt("solde"));
					mvt.setCloture(rs.getString("cloture"));
					mvt.setActif(rs.getString("actif"));
					mvt.setResponsable(rs.getString("responsable"));
					mvt.setAgenceCode(rs.getString("code"));
					mvt.setIpost(rs.getString("ipost"));
					mvt.setCoden(rs.getString("coden"));
					mvt.setAgence(rs.getString("Agence"));
					mvt.setValide(rs.getBoolean("mvtvalide"));
					mvt.setCodemouv(rs.getString("codemouv"));
					mvt.setDate_enregis(rs.getString("date_enregis"));
					// (rs.getString("date_naissance"));
					//
					/*
					 * System.out.println("mvt.getAgenceCode() "+mvt.getAgenceCode()) ;
					 * System.out.println("mvt.getCoden() "+mvt.getCoden()) ;
					 * System.out.println("mvt.getAgence() "+mvt.getAgence()) ;
					 */
					/// client.setIdclient(rs.getString("name"));
					// client.setAgence(rs.getString("Agence"));
					mvt.setMvt_datemvt(rs.getString("date_datemvt"));
					mvt.setMvt_montant(rs.getInt("montant"));
					mvt.setRubriquemvcaisse_code(rs.getString("caisecode"));
					listMovementCaisse.add(mvt);
				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);
		}

		// tris des mouvent
		Collections.sort(listMovementCaisse, comparator);
		return listMovementCaisse;
	}

	@Override
	public Mvtcaisse getMvtcaisse(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mvtcaisse getMvtcaisse(String Numero_Bordereau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mvtcaisse saveMvtcaisse(Mvtcaisse Mvtcaisse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteMvtcaisse(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClients(String value) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM client WHERE idclient IN " + value;
		System.out.println(query);
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				int rowsDeleted = statement.executeUpdate(); // Execute the delete statement

				if (rowsDeleted > 0) {
					return true;
				}
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteMvtcaisse(String value) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM mvtcaisse WHERE idmvtcaisse IN " + value;
		// System.out.println(query);
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				int rowsDeleted = statement.executeUpdate(); // Execute the delete statement

				if (rowsDeleted > 0) {
					return true;
				}
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getListUser(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<User> getListUser(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean updateAgenceClient(long idclient, int idagence) {
		String query = "UPDATE public.client\r\n" + "	SET idagence=?\r\n" + "where idclient =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				if (connexion != null) {
					statement = con.prepareStatement(query);
					statement.setInt(1, idagence);
					statement.setLong(2, idclient);
					statement.executeUpdate();
					statement.close();
					con.close();
					return true;
				}
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateAllAtributClient(Client client) {
		int indice = 0;
		java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
		String query = "UPDATE public.client\r\n" + "	SET  nom=?, prenom=?, date_naissance=?, lieunaissance=?,"
				+ " nompernompere=?, nomprenommere=?, pernompere=?, prenommere=?," + " derniere_modif=?, "
				+ " id_ville=?, idagence=?, " + " code=?, coden=?,  ipost=?,  nature=?," + " actif=?, "
				+ "  solde=?, soldestring=?,  valide=?\r\n" + "	WHERE  idclient =?;";
		System.out.println("------------------------------------------------------------------------");
		System.out.println("(client.getDate_naissance() " + client.getDatenaissance());
		System.out.println("(client.getDate_naissance() " + client.getPrenom());
		System.out.println("(client.SOLDE() " + client.getSolde());
		System.out.println("(client.getIdclient() " + client.getIdclient());
		System.out.println(query);
		System.out.println("------------------------------------------------------------------------");

		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				Connection con = connexion.connect();
				if (con != null) {
					statement = con.prepareStatement(query);
					statement.setString(1, client.getNom());
					statement.setString(2, client.getPrenom());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.sql.Date sqlDate = new java.sql.Date(client.getDatenaissance().getTime());
					System.out.println(
							"---------------------------------------------DDDDDDDD-------------------------------------------");
					System.out.println(sqlDate);
					System.out.println(
							"---------------------------------------------DDDDDDDD-------------------------------------------");
					statement.setDate(3, sqlDate);
					statement.setString(4, client.getLieunaissance());
					statement.setString(5, client.getNompernompere());
					statement.setString(6, client.getNomprenommere());
					statement.setString(7, client.getPernompere());
					statement.setString(8, client.getPrenommere());
					// Paramètres de la mise à jour
					statement.setDate(9, currentDate); // Ici, le champ de date que vous mettez à jour
					statement.setInt(10, client.getId_ville());
					statement.setInt(11, client.getIdagence());

					statement.setString(12, client.getCode());
					statement.setLong(13, client.getCoden());

					statement.setString(14, client.getIpost());
					statement.setString(15, client.getNature());
					System.out.println("---------- La est la is aceit---------------- "+client.isActive());
					statement.setBoolean(16, client.isActive());
					statement.setDouble(17, client.getSolde());
					statement.setString(18, client.getSoldestring());
					statement.setBoolean(19, client.isValide());
					statement.setLong(20, client.getIdclient());
					statement.executeUpdate();
					statement.close();
					con.close();
					return true;

				} else {

					JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}

		return false;
	}

	@Override
	public boolean updateClient(Client client) {
		String query = "UPDATE public.mvtcaisse\r\n"
				+ "	SET code=?, typemvt=?, montant=?, date_enregistre=?, observation=?, idrubriquemvcaisse=? "
				+ "where idmvtcaisse =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}

			if (connexion != null) {
				Connection con = connexion.connect();
				if (con != null) {
					statement = con.prepareStatement(query);
					// statement.setBoolean(1, mvtcaisse.getActif());
					// statement.setLong(2, mvtcaisse.getActif());
					///// statement.setString(1, client.getCoden());
					statement.setString(2, client.getTypemvt());
					///// statement.setInt(3, client.getMvt_montant());
					// System.out.println("updatemvtcaisse.getDatemvt à modifier en sql "+
					///// mvtcaisse.getDatemvt());

					//// java.sql.Date sqlDate = new java.sql.Date(client.getDatemvt().getTime());
					//// statement.setDate(4, sqlDate);
					statement.setString(5, client.getObservation());
					statement.setLong(6, client.getIdrubriquemvcaisse());
					///// statement.setLong(7, client.getIdmvtcaisse());
					statement.executeUpdate();
					statement.close();
					con.close();
					if (updateAgenceClient(client.getIdclient(), client.getIdagence())) {// modification de l'agence
						return true;
					}

				} else {
				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
				}
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateMvtcaisse(Mvtcaisse mvtcaisse) {
		String query = "UPDATE public.mvtcaisse\r\n"
				+ "	SET code=?, typemvt=?, montant=?, datemvt=?, observation=?, idrubriquemvcaisse=? , valide=?"
				+ "where idmvtcaisse =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {

				Connection con = connexion.connect();
				if (con != null) {

					statement = con.prepareStatement(query);
					// statement.setBoolean(1, mvtcaisse.getActif());
					// statement.setLong(2, mvtcaisse.getActif());
					statement.setString(1, mvtcaisse.getCoden());
					statement.setString(2, mvtcaisse.getTypemvt());
					statement.setInt(3, mvtcaisse.getMvt_montant());
					// System.out.println("updatemvtcaisse.getDatemvt à modifier en sql "+
					// mvtcaisse.getDatemvt());

					java.sql.Date sqlDate = new java.sql.Date(mvtcaisse.getDatemvt().getTime());
					statement.setDate(4, sqlDate);
					statement.setString(5, mvtcaisse.getObservation());
					statement.setLong(6, mvtcaisse.getIdrubriquemvcaisse());
					statement.setBoolean(7, mvtcaisse.isValide());
					statement.setLong(8, mvtcaisse.getIdmvtcaisse());
					statement.executeUpdate();
					statement.close();
					con.close();
					if (updateAgenceClient(mvtcaisse.getIdclient(), mvtcaisse.getIdagence())) {// modification de
																								// l'agence
						return true;
					}
				} else {
				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Rubriquemvcaisse> getListRubrique() {
		List<Rubriquemvcaisse> listMovementCaisse = new ArrayList<>();
		String query = "SELECT * FROM public.rubriquemvcaisse\r\n" + "ORDER BY idagence ASC ";

		// System.out.println(query);
		Rubriquemvcaisse rubrique = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {

					rubrique = new Rubriquemvcaisse();
					rubrique.setIdagence(rs.getInt("idagence"));
					rubrique.setCode(rs.getString("code"));

					rubrique.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));

					rubrique.setLibelle(rs.getString("libelle"));
					rubrique.setLabel(rs.getString("label"));

					rubrique.setType(rs.getString("type"));

					rubrique.setDescription(rs.getString("description"));
					rubrique.setDescriptionen(rs.getString("descriptionen"));
					rubrique.setEtat(rs.getString("etat"));
					rubrique.setDate_enregistre(rs.getString("date_enregistre"));
					rubrique.setDerniere_modif(rs.getString("derniere_modif"));
					if (rubrique.getIdrubriquemvcaisse() == 23 || rubrique.getIdrubriquemvcaisse() == 24) {
						rubrique.setDebiorcredit("debit");
					} else {
						rubrique.setDebiorcredit("Credit");
					}
					listMovementCaisse.add(rubrique);

				}
				rs.close();
				statement.close();
				con.close();
			} else {
			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listMovementCaisse;
	}

	@Override
	public boolean transferMvtToClient(String listMvt, String idclient) {
		// TODO Auto-generated method stub
		String query = "UPDATE mvtcaisse SET idclient = " + idclient + " WHERE idmvtcaisse IN " + listMvt;
		// System.out.println(query);
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}

			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				int rowsDeleted = statement.executeUpdate(); // Execute the delete statement

				if (rowsDeleted > 0) {
					return true;
				}
			} else {
			JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Client> getListClientByIdAgence(long idagence) {
		List<Client> listClients = new ArrayList<>();

		String query = "select  clt.nature,clt.idclient ,ag.idagence,ag.code, ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where \r\n" + "ag.idagence=clt.idagence and \r\n"
				+ "ag.idagence= '" + idagence + "'";

		// System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = connexion.connect().prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));
					listClients.add(client);

				}
				rs.close();
				statement.close();
				con.close();
			} else {
			//	JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");
			}
		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return listClients;
	}

	@Override
	public List<Client> getAllClientByIdAgenceAndIdclient(int idAgence, long idClient) {
		List<Client> listClients = new ArrayList<>();
		String query = "select clt.nature, clt.idclient ,ag.idagence,ag.code, ag.raisonsociale,to_char(clt.date_naissance ,'DD-MM-YYYY')date_naissance,\r\n"
				+ "clt.code,clt.coden,clt.ipost,clt.nature,clt.valide,clt.lieunaissance, clt.nom, clt.solde, clt.prenom,clt.pernompere,clt.prenommere,\r\n"
				+ "clt.nompernompere,clt.nomprenommere,clt.actif ,to_char(clt.date_enregistre ,'DD-MM-YYYY')date_enregistre\r\n"
				+ "from  agence ag, client clt  \r\n" + "where\r\n" + "clt.idagence = ag.idagence and \r\n"
				+ "ag.idagence =  '" + idAgence + "' and \r\n" + "clt.idclient = '" + idClient + "'";

		System.out.println(query);
		Client client = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {
				statement = con.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				// new String[] {"", "N.Num", "N ALpha", "N IPOST", "Livret", "Solde", "Solde
				// Mvt", "Enregistrement", "Validé"}

				while (rs.next()) {

					client = new Client();
					client.setIdclient(rs.getLong("idclient"));
					client.setCode(rs.getString("code"));
					client.setCoden(rs.getLong("coden"));
					client.setIdagence(rs.getInt("idagence"));
					client.setRaisonsociale(rs.getString("raisonsociale"));
					client.setDate_naissance(rs.getString("date_naissance"));
					client.setIpost(rs.getString("ipost"));
					client.setNature(rs.getString("nature"));
					client.setValide(rs.getBoolean("valide"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setNompernompere(rs.getString("nompernompere"));
					client.setNomprenommere(rs.getString("nomprenommere"));
					client.setPernompere(rs.getString("pernompere"));
					client.setPrenommere(rs.getString("prenommere"));
					client.setLieunaissance(rs.getString("lieunaissance"));
					client.setDate_enregistre(rs.getString("date_enregistre"));
					client.setSolde(rs.getDouble("solde"));
					client.setNature(rs.getString("nature"));
					client.setActif( rs.getBoolean("actif"));

				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}
		List<Mvtcaisse> listMvts = getAllMvtForClient(idClient);
		if (listMvts.size() > 0) {
			double soldeMouvement = calcutSoldeMvt(client.getIdclient(), listMvts);
			client.setSolde_mouvement(soldeMouvement);

		}
		if (client != null)
			listClients.add(client);
		return listClients;
	}

	@Override
	public List<Mvtcaisse> getListMvtcaisse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mvtcaisse> getListMvtcaisse(java.util.Date datedepart, java.util.Date datefin ,String codeClient) {
		Date sqlDatedepart = new java.sql.Date(datedepart.getTime());
		Date sqlDatefin = new java.sql.Date(datefin.getTime());
		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();
		String query = "SELECT \r\n" + "idmvtcaisse,  client.idclient,\r\n" + "  client.nom, mvt.valide as mvtvalide, \r\n"
					+ "  client.prenom, \r\n"
					+ "  to_char(client.date_naissance,'DD/MM/YYYY')date_naissance, to_char(mvt.date_enregistre,'DD/MM/YYYY')date_enregis,\r\n"
					+ "  client.solde, \r\n" + "  client.cloture, \r\n" + "  client.actif, \r\n"
					+ "  client.responsable, \r\n" + "  agence.code,mvt.code as codemouv, mvt.idrubriquemvcaisse, \r\n"
					+ "  client.ipost, \r\n" + "  client.coden, \r\n" + "  client.code, \r\n"
					+ "  agence.raisonsociale Agence,\r\n" + "   to_char(mvt.datemvt,'DD/MM/YYYY')date_datemvt,\r\n"
					+ "   mvt.montant,\r\n" + "   rubriquemvcaisse.code caisecode\r\n" + "   \r\n" + "FROM \r\n"
					+ "  public.client, \r\n" + "  public.agence,\r\n" + "  mvtcaisse mvt,\r\n"
					+ "  rubriquemvcaisse\r\n" + "WHERE \r\n" + "  client.idagence = agence.idagence \r\n"
					+ "  and mvt.idclient = '" + codeClient + "'\r\n"
					+ "  and mvt.datemvt BETWEEN  ? AND ? \r\n"
					+ "  and mvt.idrubriquemvcaisse = rubriquemvcaisse.idrubriquemvcaisse\r\n"
					+ "  and client.idclient = mvt.idclient\r\n" + "ORDER BY client.idclient asc\r\n" + "\r\n;";
	

		//System.out.println(query);
		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				statement.setDate(1, sqlDatedepart);
				statement.setDate(2, sqlDatefin);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setNom(rs.getString("nom") + " " + rs.getString("prenom"));
					mvt.setPrenom(rs.getString("prenom"));
					mvt.setDate_naissance(rs.getString("date_naissance"));
					mvt.setSolde(rs.getInt("solde"));
					mvt.setCloture(rs.getString("cloture"));
					mvt.setActif(rs.getString("actif"));
					mvt.setResponsable(rs.getString("responsable"));
					mvt.setAgenceCode(rs.getString("code"));
					mvt.setIpost(rs.getString("ipost"));
					mvt.setCoden(rs.getString("coden"));
					mvt.setAgence(rs.getString("Agence"));
					mvt.setValide(rs.getBoolean("mvtvalide"));
					mvt.setCodemouv(rs.getString("codemouv"));
					mvt.setDate_enregis(rs.getString("date_enregis"));
					// (rs.getString("date_naissance"));
					//
					/*
					 * System.out.println("mvt.getAgenceCode() "+mvt.getAgenceCode()) ;
					 * System.out.println("mvt.getCoden() "+mvt.getCoden()) ;
					 * System.out.println("mvt.getAgence() "+mvt.getAgence()) ;
					 */
					/// client.setIdclient(rs.getString("name"));
					// client.setAgence(rs.getString("Agence"));
					mvt.setMvt_datemvt(rs.getString("date_datemvt"));
					mvt.setMvt_montant(rs.getInt("montant"));
					mvt.setRubriquemvcaisse_code(rs.getString("caisecode"));
					listMovementCaisse.add(mvt);
				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);
		}

		// tris des mouvent
		Collections.sort(listMovementCaisse, comparator);
		return listMovementCaisse;
	}

	
	
	@Override
	public List<Mvtcaisse> getListMvtcaisse(java.util.Date datedepart, java.util.Date datefin ) {
		Date sqlDatedepart = new java.sql.Date(datedepart.getTime());
		Date sqlDatefin = new java.sql.Date(datefin.getTime());
		List<Mvtcaisse> listMovementCaisse = new ArrayList<>();
		String query = "SELECT \r\n" + "idmvtcaisse,  client.idclient,\r\n" + "  client.nom, mvt.valide as mvtvalide, \r\n"
					+ "  client.prenom, \r\n"
					+ "  to_char(client.date_naissance,'DD/MM/YYYY')date_naissance, to_char(mvt.date_enregistre,'DD/MM/YYYY')date_enregis,\r\n"
					+ "  client.solde, \r\n" + "  client.cloture, \r\n" + "  client.actif, \r\n"
					+ "  client.responsable, \r\n" + "  agence.code,mvt.code as codemouv, mvt.idrubriquemvcaisse, \r\n"
					+ "  client.ipost, \r\n" + "  client.coden, \r\n" + "  client.code, \r\n"
					+ "  agence.raisonsociale Agence,\r\n" + "   to_char(mvt.datemvt,'DD/MM/YYYY')date_datemvt,\r\n"
					+ "   mvt.montant,\r\n" + "   rubriquemvcaisse.code caisecode\r\n" + "   \r\n" + "FROM \r\n"
					+ "  public.client, \r\n" + "  public.agence,\r\n" + "  mvtcaisse mvt,\r\n"
					+ "  rubriquemvcaisse\r\n" + "WHERE \r\n" + "  client.idagence = agence.idagence \r\n"
					+ "  and mvt.datemvt BETWEEN  ? AND ? \r\n"
					+ "  and mvt.idrubriquemvcaisse = rubriquemvcaisse.idrubriquemvcaisse\r\n"
					+ "  and client.idclient = mvt.idclient\r\n" + "ORDER BY client.idclient asc\r\n" + "\r\n;";
	

		//System.out.println(query);
		Mvtcaisse mvt = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			Connection con = connexion.connect();
			if (con != null) {

				statement = con.prepareStatement(query);
				statement.setDate(1, sqlDatedepart);
				statement.setDate(2, sqlDatefin);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					mvt = new Mvtcaisse();
					mvt.setIdrubriquemvcaisse(rs.getLong("idrubriquemvcaisse"));
					mvt.setIdmvtcaisse(rs.getLong("idmvtcaisse"));
					mvt.setIdclient(rs.getLong("idclient"));
					mvt.setNom(rs.getString("nom") + " " + rs.getString("prenom"));
					mvt.setPrenom(rs.getString("prenom"));
					mvt.setDate_naissance(rs.getString("date_naissance"));
					mvt.setSolde(rs.getInt("solde"));
					mvt.setCloture(rs.getString("cloture"));
					mvt.setActif(rs.getString("actif"));
					mvt.setResponsable(rs.getString("responsable"));
					mvt.setAgenceCode(rs.getString("code"));
					mvt.setIpost(rs.getString("ipost"));
					mvt.setCoden(rs.getString("coden"));
					mvt.setAgence(rs.getString("Agence"));
					mvt.setValide(rs.getBoolean("mvtvalide"));
					mvt.setCodemouv(rs.getString("codemouv"));
					mvt.setDate_enregis(rs.getString("date_enregis"));
					// (rs.getString("date_naissance"));
					//
					/*
					 * System.out.println("mvt.getAgenceCode() "+mvt.getAgenceCode()) ;
					 * System.out.println("mvt.getCoden() "+mvt.getCoden()) ;
					 * System.out.println("mvt.getAgence() "+mvt.getAgence()) ;
					 */
					/// client.setIdclient(rs.getString("name"));
					// client.setAgence(rs.getString("Agence"));
					mvt.setMvt_datemvt(rs.getString("date_datemvt"));
					mvt.setMvt_montant(rs.getInt("montant"));
					mvt.setRubriquemvcaisse_code(rs.getString("caisecode"));
					listMovementCaisse.add(mvt);
				}
				rs.close();
				statement.close();
				con.close();
			} else {

				JOptionPane.showMessageDialog(null, " Vous n'etes pas Connecté");

			}

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);
		}

		// tris des mouvent
		Collections.sort(listMovementCaisse, comparator);
		return listMovementCaisse;
	}
	
	
}
