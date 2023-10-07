package Global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Client;
import model.Mvtcaisse;
import model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GlobalVar {
	public static int IDUSER=0;
	public static List<Client> listClients = new ArrayList<>();
	public static List<Mvtcaisse>listMvts=new ArrayList<>();
	
	public static int PAGE=0;
	public static String NOMUSER;
	public static User CurentClient;
	//String documentsPaths = System.getProperty("user.home");
	public static final String documentsPaths = "c:"+File.separator+"SAVE_BANCK"+File.separator;
	public static final String filePathConfig = documentsPaths + File.separator+"save_banck"+File.separator+"config.txt"; 
	public static boolean isNumber(String input) {
	    return input.matches("-?\\d+"); // or "-?\\d+(\\.\\d+)?" for decimal numbers
	}
	/*
	 * public static String toLetter(int value){ RuleBasedNumberFormat rbnf = new
	 * RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT); if
	 * (value == 0) return ""; return rbnf.format(value).toUpperCase() +
	 * " FRANCS CFA"; }
	 */
	
	public static String getSelectItemForJtable(JTable myTable,List<Mvtcaisse> listMtv) {
		List<String> valuesList = new ArrayList<>();
		for (int row = 0; row < myTable.getRowCount(); row++) {
			Boolean isChecked = (Boolean) myTable.getValueAt(row, 0);
			if (isChecked) {
				Mvtcaisse caiseselect = listMtv.get(row);
				
				valuesList.add(String.valueOf(caiseselect.getIdmvtcaisse()));
			}
		}
		// Construire la chaîne (value1, value2, ...)
		StringBuilder valuesStringBuilder = new StringBuilder("(");
		for (int i = 0; i < valuesList.size(); i++) {
			valuesStringBuilder.append(valuesList.get(i));
			if (i < valuesList.size() - 1) {
				valuesStringBuilder.append(", ");
			}
		}
		valuesStringBuilder.append(")");
		String valuesString = valuesStringBuilder.toString();
		return valuesString;
	}
	
	public static void checkAllCheckboxInTable(JTable myTable ) {
		int checkBoxColumn = 0;

		for (int row = 0; row < myTable.getRowCount(); row++) {
			myTable.setValueAt(true, row, checkBoxColumn);
		}
	}

	
	
	// seletc client
	
	public static String getSelectItemForJtableClient(JTable myTable,List<Client> listClient) {
		List<String> valuesList = new ArrayList<>();
		for (int row = 0; row < myTable.getRowCount(); row++) {
			Boolean isChecked = (Boolean) myTable.getValueAt(row, 0);
			if (isChecked) {
				Client clientselect = listClient.get(row);
				
				valuesList.add(String.valueOf(clientselect.getIdclient()));
			}
		}
		// Construire la chaîne (value1, value2, ...)
		StringBuilder valuesStringBuilder = new StringBuilder("(");
		for (int i = 0; i < valuesList.size(); i++) {
			valuesStringBuilder.append(valuesList.get(i));
			if (i < valuesList.size() - 1) {
				valuesStringBuilder.append(", ");
			}
		}
		valuesStringBuilder.append(")");
		String valuesString = valuesStringBuilder.toString();
		return valuesString;
	}
	
	public static String getArrayIdClient(List<Client> listClient) {
		List<String> valuesList = new ArrayList<>();
		
		for( Client client:listClient) {
			valuesList.add(String.valueOf(client.getIdclient()));
			
		}
		
		// Construire la chaîne (value1, value2, ...)
		StringBuilder valuesStringBuilder = new StringBuilder("(");
		for (int i = 0; i < valuesList.size(); i++) {
			valuesStringBuilder.append(valuesList.get(i));
			if (i < valuesList.size() - 1) {
				valuesStringBuilder.append(", ");
			}
		}
		valuesStringBuilder.append(")");
		String valuesString = valuesStringBuilder.toString();
		return valuesString;
	}
	
	
	
	 public static String[] searchUser(String fileName, String loginToFind, String passwordToFind) {
	        try {
	            // Obtenez le chemin absolu du fichier
	            Path filePath = Paths.get(fileName).toAbsolutePath();

	            File file = new File(filePath.toString());

	            if (!file.exists()) {
	                // Si le fichier n'existe pas, créez-le avec un exemple de contenu
	                createFileWithExampleContent(file);
	            }

	            BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
	            String line;

	            while ((line = reader.readLine()) != null) {
	                String[] nonEmptyElements = line.split(" "); // Supposons que les éléments sont séparés par des espaces
	                String[] elements =removeEmptyElements(nonEmptyElements);
	                if(elements.length>=3) {
	                	String id = elements[0];
		                String login = elements[1];
		                String password = elements[2];
		                if (login.equals(loginToFind) && password.equals(passwordToFind)) {
		                    reader.close();
		                    return new String[]{id, login};
		                }
	                }else {
	                	  JOptionPane.showMessageDialog(null, "Consulter l'administrateur Svp");
	                }
	                
	            }

	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	 
	 
	  public static String[] removeEmptyElements(String[] inputArray) {
	        List<String> nonEmptyList = new ArrayList<>();
	        
	        for (String element : inputArray) {
	            if (!element.isEmpty()) {
	                nonEmptyList.add(element);
	            }
	        }

	        return nonEmptyList.toArray(new String[0]);
	    }
	 
	  public static String concatenateNonEmptyElements(String[] inputArray) {
		    StringBuilder concatenatedString = new StringBuilder();

		    for (String element : inputArray) {
		        if (!element.isEmpty()) {
		            concatenatedString.append(element).append(" ");
		        }
		    }

		    // Supprime l'espace final ajouté en trop
		    if (concatenatedString.length() > 0) {
		        concatenatedString.setLength(concatenatedString.length() - 1);
		    }

		    return concatenatedString.toString();
		}
	 public static void createFileWithExampleContent(File file) throws IOException {
	        file.getParentFile().mkdirs(); // Crée le répertoire s'il n'existe pas
	        file.createNewFile(); // Crée le fichier s'il n'existe pas

	        // Ajoute un exemple de contenu
	        FileWriter writer = new FileWriter(file);
	        writer.write("1 username password\n");
	        writer.close();
	    }
	 
	 
	 
	 public static void restrictToNumbers(JTextField textField) {
	        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                StringBuilder builder = new StringBuilder(string);
	                for (int i = builder.length() - 1; i >= 0; i--) {
	                    char ch = builder.charAt(i);
	                    if (!Character.isDigit(ch)) {
	                        builder.deleteCharAt(i);
	                    }
	                }
	                super.insertString(fb, offset, builder.toString(), attr);
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                if (text != null) {
	                    StringBuilder builder = new StringBuilder(text);
	                    for (int i = builder.length() - 1; i >= 0; i--) {
	                        char ch = builder.charAt(i);
	                        if (!Character.isDigit(ch)) {
	                            builder.deleteCharAt(i);
	                        }
	                    }
	                    text = builder.toString();
	                }
	                super.replace(fb, offset, length, text, attrs);
	            }
	        });
	    }
	 
	 
	 
	/* public static void createConfigFile(String filePath, String address, String username, String password) {
	        File configFile = new File(filePath);

	        if (!configFile.exists()) {
	            try {
	                FileWriter writer = new FileWriter(configFile);
	                writer.write("1 " + address + "\n");
	                writer.write("2 " + username + "\n");
	                writer.write("3 " + password + "\n");
	                writer.close();
	                System.out.println("Fichier créé avec succès.");
	            } catch (IOException e) {
	                System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
	            }
	        } else {
	            System.out.println("Le fichier existe déjà.");
	        }
	    }
	 */
	 
	 
	 public static boolean createConfigFile(String filePath, String address, String username, String password) {
	        File configFile = new File(filePath);
	        File parentFolder = configFile.getParentFile();
	        
	        if (!parentFolder.exists()) {
	            boolean foldersCreated = parentFolder.mkdirs();
	            if (!foldersCreated) {
	                System.err.println("Erreur lors de la création des dossiers parents.");
	                return false;
	            }
	        }

	        if (!configFile.exists()) {
	            try {
	                FileWriter writer = new FileWriter(configFile);
	                writer.write("1 " + address + "\n");
	                writer.write("2 " + username + "\n");
	                writer.write("3 " + password + "\n");
	                writer.close();
	                System.out.println("Fichier créé avec succès.");
	                return true;
	            } catch (IOException e) {
	                System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
	            }
	        } else {
	            System.out.println("Le fichier existe déjà.");
	            return true;
	        }
	        return false;
	    }

	 
	 
	 public static  void setPlaceholder(final JTextField textField, final String placeholder) {
	        textField.setText(placeholder);
	        textField.setForeground(Color.GRAY);

	        textField.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (textField.getText().equals(placeholder)) {
	                    textField.setText("");
	                    textField.setForeground(Color.BLACK);
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField.getText().isEmpty()) {
	                    textField.setText(placeholder);
	                    textField.setForeground(Color.GRAY);
	                }
	            }
	        });

	        textField.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (textField.getText().equals(placeholder)) {
	                    textField.setCaretPosition(0);
	                }
	            }
	        });
	    }
	 
	 
}
