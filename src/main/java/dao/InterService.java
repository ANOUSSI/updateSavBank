package dao;

import java.sql.Date;
import java.util.List;

import model.Agence;
import model.Mvtcaisse;
import model.Rubriquemvcaisse;
import model.Client;
import model.User;
import model.Ville;

public interface InterService {
	public List<Ville> getVille();
	// Rubrique
	public List<Rubriquemvcaisse> getListRubrique();
	// Agence
	public List<Agence> getListAgence();

	public List<Agence> getListAgence(int idUser);

	public List<Agence> getListAgence(String date);

	public Agence getAgence(int id);

	public Agence getAgence(String Numero_Bordereau);

	public Agence saveAgence(Agence Agence);

	public boolean deleteAgence(long id);

	public boolean deleteAgence(String Numero_Bordereau);
	
	public List<Client> getAllClientsAgence(String agence,int PAGE);
	public boolean updateClient(Client client);
	// Client
	public List<Client> getListClient();

	public List<Client> getListClient(long offset);
	public List<Client> getListClientByIdAgence(long idagence);
	public boolean updateAllAtributClient(Client client);
	public List<Client> getListClient(String valeurEntree);

	public Client getClient(long id);

	public Client getClient(String codeuser);

	public Client saveClient(Client Client);

	public boolean deleteClient(long id);

	public boolean deleteClient(String Numero_Bordereau);

	// Mvtcaisse
	public List<Mvtcaisse> getListMvtcaisse();

	public List<Mvtcaisse> getListMvtcaisse(int idUser);

	public List<Mvtcaisse> getListMvtcaisse(String codeClient);
	public boolean deleteMvtcaisse( String value) ;

	public Mvtcaisse getMvtcaisse(int id);

	public Mvtcaisse getMvtcaisse(String Numero_Bordereau);

	public Mvtcaisse saveMvtcaisse(Mvtcaisse Mvtcaisse);
	public boolean updateMvtcaisse(Mvtcaisse Mvtcaisse);

	public boolean deleteMvtcaisse(long id);
	public boolean transferMvtToClient(String listMvt, String idclient);
	
	public List<Client> getAllClientsByName(String name);
	public List<Client> getListClientByNmame(String name);

	/// USER
	public User get(String login, String password);
	public User getActi(String login, String password);
	
	public User saveUser(User user);
	public boolean updateUser(User user);

	public List<User> getListUser(String nom);

	public List<User> getListUser();

	public List<User> getListUser(int number);

	public User getUser(long idUser);
	
	public boolean deleteClients(String value) ;
	public List<Mvtcaisse> getListMvtcaisse(java.util.Date datedepart, java.util.Date datefin,String textsearch);
	public List<Mvtcaisse> getListMvtcaisse(java.util.Date datedepart, java.util.Date datefin) ;
	// client 
	public List<Client> getAllClientByIdAgenceAndIdclient(int idAgence, long idClient);
	public boolean updateAgenceClient(long idclient,int idagence);

}
