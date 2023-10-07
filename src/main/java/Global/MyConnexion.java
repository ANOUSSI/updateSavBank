package Global;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.JOptionPane;

public class MyConnexion {
	Connection connection = null;
	PreparedStatement pst;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPst() {
		return pst;
	}

	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}

	public Connection connect() {
		try {
			//String documentsPaths = System.getProperty("user.home");
			
			String filePath = GlobalVar.documentsPaths + "\\save_banck\\config.txt";
		//	String[] info = readInfoFromFile(filePath);
			String localhost = "10.14.2.13";
			//String localhost = "127.0.0.1";
			String username = "padlock";
			String password = "ad123!";
			
			/*if (info != null) {
				localhost = info[0];
				username = info[1];
				password = info[2];

				// System.out.println("Adresse IP : " + address);
				System.out.println("Login : " + username);
				System.out.println("Mot de passe : " + password);
			} else {
				System.out.println("Erreur lors de la lecture du fichier.");
			}*/

			// Class.forName("com.mysql.cj.jdbc.Driver");
			// String localhost="localhost";

			/// String localhost="192.168.137.80";
			String jdbcURL = "jdbc:postgresql://" + localhost + ":5432/padlock_db";

			connection = DriverManager.getConnection(jdbcURL, username, password);

			// connection = DriverManager.getConnection(
			// "jdbc:mysql://localhost/pharma1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root",
			// "123456");
			// connection = DriverManager.getConnection(
			// "jdbc:mysql://localhost:3306/bd_sable","root", "123456");
		} catch (Exception exception) {
			System.out.println("-------------------------------------");
			System.out.println(exception);
			System.out.println("-------------------------------------");
		}
		System.out.println("conoeinfoere");

		System.out.println(connection);
		return connection;

	}

	public static String[] readInfoFromFile(String filePath) {
		String[] info = new String[3];

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			for (int i = 0; i < 3; i++) {
				String line = reader.readLine();
				if (line == null) {
					System.out.println("Erreur : Le fichier ne contient pas assez de lignes.");
					return null;
				}
				info[i] = line.substring(line.indexOf(" ") + 1); // Supprime le numéro d'ordre
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
			return null;
		}

		return info;
	}
	
	
	 public boolean authenticate(String username, String password) {
	        try {
	            Hashtable<String, String> env = new Hashtable<>();
	            env.put(Context.SECURITY_AUTHENTICATION, "simple");
	            env.put(Context.SECURITY_PRINCIPAL, "CN=" + username); // User's full LDAP path
	            env.put(Context.SECURITY_CREDENTIALS, password);
	            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	            env.put(Context.PROVIDER_URL, "ldap://localhost:389"); // Replace with your AD server URL

	            // Try to create a directory context with the provided credentials
	            DirContext context = new InitialDirContext(env);
	            context.close();
	            return true; // Authentication successful
	        } catch (NamingException e) {
	            // Authentication failed
	            return false;
	        }
	    }
	

}
