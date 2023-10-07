package vue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import Global.GlobalVar;
import dao.UserService;
import model.Client;
import model.Mvtcaisse;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameCaisse extends javax.swing.JFrame implements DialogCallback {
	private JTextField textIdClient;
	private JTextField textField;
	JComboBox<String> comboBox;
	UserService service = new UserService();
	List<Mvtcaisse> listMtv = new ArrayList<Mvtcaisse>();
	private boolean isChecked = false;
	int offset = 0;
	JLabel lbnomreceveur;
	String textsearch;
	String idclient = "Id du Client";
	String filtrerpardate = "Filtrer par date et Utilisateur";
	String filtrerpardatetout = "Filtrer par date";
	private javax.swing.JTable myTable;
	JDateChooser datedepart;
	JDateChooser datefin;

	public FrameCaisse() {
		setTitle("GESTION DES MOUVEMENTS DE CAISSE");
		getContentPane().setBackground(Color.BLUE);
		setBackground(Color.BLUE);
		initComponents();
	}

	private void initComponents() {
		comboBox = new JComboBox<>();
		textIdClient = new JTextField();

		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(0, 0, 255));
		JScrollPane jScrollPane1 = new JScrollPane();
		textField = new JTextField();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JButton btnComptes = new JButton();
		btnComptes.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnComptes.setBackground(new Color(184, 134, 11));
		btnComptes.setForeground(new Color(255, 255, 255));

		btnComptes.setText("Gestion de Client");
		btnComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//        				GlobalVar.IDUSER = user.getId();
				dispose();
				// JOptionPane.showMessageDialog(null, " Bien Connecter");

				/*
				 * MenuClients frameSable = new MenuClients(); frameSable.init();
				 */
				FrameClient frameClient = new FrameClient();
				frameClient.init();
			}
		});
		JButton btnNewButtonValider = new JButton();
		btnNewButtonValider.setBackground(new Color(0, 100, 0));
		btnNewButtonValider.setForeground(new Color(255, 255, 255));
		btnNewButtonValider.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButtonValider.setText("Rafraichir");
		btnNewButtonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * comboBox.addItem(idclient);
				 * 
				 * comboBox.addItem(filtrerpardate);
				 */
				// searchUser
				String selecteurdetype = comboBox.getSelectedItem().toString();
				System.out.println("selecteurdetype  " + selecteurdetype);
				if (selecteurdetype.equalsIgnoreCase(idclient)) {
					textsearch = textField.getText().toString();
					if (textsearch != null && !textsearch.isEmpty())
						listMtv = service.getListMvtcaisse(textsearch);
					else
						JOptionPane.showMessageDialog(null, "Il manque l'identifiant du client", "Erreur",
								JOptionPane.ERROR_MESSAGE);

				} else {
					if (selecteurdetype.equalsIgnoreCase(filtrerpardate)) {
						// textsearch=textField.getText().toString();
						// listMtv = service.getListMvtcaisse(textsearch);

						if (isValidDateRange()) {
							System.out.println("dateChooser.getDate() " + datedepart.getDate());
							System.out.println("dateChooser.getDate() " + datefin.getDate());
							textsearch = textField.getText().toString();
							if (textsearch != null && !textsearch.isEmpty()) {
								listMtv = service.getListMvtcaisse(datedepart.getDate(), datefin.getDate(), textsearch);
							} else {
								JOptionPane.showMessageDialog(null, "Il manque l'identifiant du client", "Erreur",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"La date de départ doit être antérieure à la date de fin.", "Erreur",
									JOptionPane.ERROR_MESSAGE);
						}

					}else {
						if (selecteurdetype.equalsIgnoreCase(filtrerpardatetout)) {
							// textsearch=textField.getText().toString();
							// listMtv = service.getListMvtcaisse(textsearch);

							if (isValidDateRange()) {
								System.out.println("dateChooser.getDate() " + datedepart.getDate());
								System.out.println("dateChooser.getDate() " + datefin.getDate());
								listMtv = service.getListMvtcaisse(datedepart.getDate(), datefin.getDate());

							} else {
								JOptionPane.showMessageDialog(null,
										"La date de départ doit être antérieure à la date de fin.", "Erreur",
										JOptionPane.ERROR_MESSAGE);
							}

						}
						
						
						
						
						
					}
				}

				createMtable(listMtv);

			}
		});
		JLabel lblNewLabel_1 = new JLabel("Rechercher par:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));

		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JButton btnNewButton_1 = new JButton("Tout selectionner");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAllCheckboxInTable();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));

		JLabel lblNewLabel_3 = new JLabel("DU");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));

		datedepart = new JDateChooser();

		JLabel lblNewLabel_4 = new JLabel("AU");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));

		datefin = new JDateChooser();
		datedepart.setDate(new Date());
		datefin.setDate(new Date());
		JLabel lblNewLabel_1_1 = new JLabel("Filtage par Date de Mouvement");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));

		JLabel lblNewLabel_1_2 = new JLabel("Valeur");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_2.setBackground(Color.WHITE);

		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		myTable = new javax.swing.JTable();
		GlobalVar.restrictToNumbers(textIdClient);

		comboBox = new JComboBox<>();
		// Ajouter des éléments à la combobox
		// comboBox.addItem("Id du Client");
		comboBox.addItem(idclient);

		comboBox.addItem(filtrerpardate);
		comboBox.addItem(filtrerpardatetout);
		
		// comboBox_3.addItem("CLIENT");
		// comboBox_3.addItem("OPERATION");
		listMtv = service.getListMvtcaisse(offset);
		createMtable(listMtv);

		myTable.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Check for double-click event
				if (e.getClickCount() == 2) {
					// Get the selected row index
					// int rowIndex = myTable.getSelectedRow();
					int selectedRow = myTable.getSelectedRow();
					Mvtcaisse caiseselect = listMtv.get(selectedRow);
					listeBonsUse(e, caiseselect);

				}
			}

		});

		textIdClient.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (textIdClient.getText() != null && !textIdClient.getText().isEmpty()) {
					long idUser = Long.parseLong(textIdClient.getText());
					Client client = service.getClient(idUser);
					if (client != null) {
						String nom_prenom = "";
						if (client.getNom() != null) {
							nom_prenom += client.getNom();
						}
						if (client.getPrenom() != null) {
							nom_prenom += " " + client.getPrenom();
						}
						lbnomreceveur.setText(nom_prenom);
					} else {
						lbnomreceveur.setText("");
					}
				} else {
					lbnomreceveur.setText("");
				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (textIdClient.getText() != null && !textIdClient.getText().isEmpty()) {
					long idUser = Long.parseLong(textIdClient.getText());
					Client client = service.getClient(idUser);
					if (client != null) {
						String nom_prenom = "";
						if (client.getNom() != null) {
							nom_prenom += client.getNom();
						}
						if (client.getPrenom() != null) {
							nom_prenom += " " + client.getPrenom();
						}
						lbnomreceveur.setText(nom_prenom);
					} else {
						lbnomreceveur.setText("");
					}
				} else {
					lbnomreceveur.setText("");
				}

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Ce cas n'est généralement pas utilisé pour les JTextFields simples
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(6)
										.addComponent(comboBox, 0, 122, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(171)))
						.addGroup(
								jPanel1Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, 84,
														Short.MAX_VALUE)
												.addGap(30))
										.addGroup(
												Alignment.TRAILING,
												jPanel1Layout
														.createSequentialGroup().addComponent(textField)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 18,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)))
						.addGap(0)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
								.createSequentialGroup().addGap(10)
								.addComponent(datedepart, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE).addGap(8)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(datefin, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewButtonValider, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
								.addGap(39)
								.addComponent(btnComptes, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(61)
										.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
										.addGap(267)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout
								.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 15,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnComptes, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
										.addComponent(btnNewButtonValider, GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_1)
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
												.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addGap(13))
								.addComponent(datedepart, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(datefin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(0)));
		jPanel1.setLayout(jPanel1Layout);

		jScrollPane1.setViewportView(myTable);

		JPanel jPanel1_1 = new JPanel();
		jPanel1_1.setBackground(Color.ORANGE);

		JButton BoutonSuppr = new JButton();
		BoutonSuppr.setForeground(new Color(230, 230, 250));
		BoutonSuppr.setBackground(new Color(255, 0, 0));
		BoutonSuppr.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		BoutonSuppr.setText("Suprimer");
		BoutonSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valuesString = GlobalVar.getSelectItemForJtable(myTable, listMtv);
				if (valuesString.length() > 2) {
					int option = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de vouloir effectuer cette action ?", "Confirmation",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						System.out.println("Action confirmée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Oui".
						service.deleteMvtcaisse(valuesString);
						System.out.println("Données sélectionnées : " + valuesString);
						listMtv = service.getListMvtcaisse(offset);
						createMtable(listMtv);
					} else if (option == JOptionPane.NO_OPTION) {
						System.out.println("Action annulée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Non".
					} else if (option == JOptionPane.CLOSED_OPTION) {
						System.out.println("Boîte de dialogue fermée.");
						// Ajoutez ici le code à exécuter si l'utilisateur ferme la boîte de dialogue.
					}

				} else {
					JOptionPane.showMessageDialog(null, " Veillez Selectionner Un element.");
				}
			}

		});
		JButton jBtTransfertMvttoClient = new JButton();
		jBtTransfertMvttoClient.setBackground(new Color(0, 64, 0));
		jBtTransfertMvttoClient.setForeground(new Color(255, 255, 255));
		jBtTransfertMvttoClient.setText("Transferer");
		jBtTransfertMvttoClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Transférer Mvt To Client
				String idclient = textIdClient.getText();
				String listMvt = GlobalVar.getSelectItemForJtable(myTable, listMtv);
				if (idclient != null && !idclient.isEmpty()) {

					int option = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de vouloir effectuer cette action ?", "Confirmation",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						System.out.println("Action confirmée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Oui".
						// service.deleteMvtcaisse(valuesString);
						if (service.transferMvtToClient(listMvt, idclient)) {
							listMtv = service.getListMvtcaisse(offset);
							createMtable(listMtv);
						}

						JOptionPane.showMessageDialog(null, " Operation Terminé avec Succ");
					} else if (option == JOptionPane.NO_OPTION) {
						System.out.println("Action annulée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Non".
					} else if (option == JOptionPane.CLOSED_OPTION) {
						System.out.println("Boîte de dialogue fermée.");
						// Ajoutez ici le code à exécuter si l'utilisateur ferme la boîte de dialogue.
					}

				} else {
					JOptionPane.showMessageDialog(null, " Le champ id client est vide");
				}

			}
		});

		textIdClient.setHorizontalAlignment(SwingConstants.CENTER);
		textIdClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textIdClient.setColumns(10);

		lbnomreceveur = new JLabel("Client a transferer");
		lbnomreceveur.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lbnomreceveur.setForeground(new Color(0, 0, 0));
		lbnomreceveur.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_jPanel1_1 = new GroupLayout(jPanel1_1);
		gl_jPanel1_1.setHorizontalGroup(gl_jPanel1_1.createParallelGroup(Alignment.LEADING).addGroup(gl_jPanel1_1
				.createSequentialGroup().addContainerGap()
				.addComponent(BoutonSuppr, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE).addGap(110)
				.addComponent(jBtTransfertMvttoClient, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
				.addGap(121).addComponent(textIdClient, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE).addGap(55)
				.addComponent(lbnomreceveur, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE).addGap(94)));
		gl_jPanel1_1.setVerticalGroup(gl_jPanel1_1.createParallelGroup(Alignment.LEADING).addGroup(gl_jPanel1_1
				.createSequentialGroup()
				.addGroup(gl_jPanel1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textIdClient, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(jBtTransfertMvttoClient, GroupLayout.PREFERRED_SIZE, 28,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(BoutonSuppr)
						.addComponent(lbnomreceveur, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1_1.setLayout(gl_jPanel1_1);

		JButton btnPrev = new JButton("Prec");
		if (offset == 0) {
			btnPrev.enable(false);
		}
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textsearch != null && !textsearch.isEmpty()) {

				} else {
					if (offset > -1) {
						listMtv = service.getListMvtcaisse(offset--);
						createMtable(listMtv);
					}
				}

			}
		});

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, " Bien Connecter "+textsearch);
				if (textsearch != null && !textsearch.isEmpty()) {

				} else {
					listMtv = service.getListMvtcaisse(offset++);
					createMtable(listMtv);
				}

			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1257, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
										GroupLayout.DEFAULT_SIZE, 1247, Short.MAX_VALUE)))
						.addContainerGap())
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(608)
						.addComponent(btnPrev, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18).addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE).addGap(533))
				.addGroup(layout.createSequentialGroup().addGap(20).addComponent(jPanel1_1, GroupLayout.PREFERRED_SIZE,
						1247, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(33)
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel1_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout
								.createParallelGroup(Alignment.BASELINE).addComponent(btnNext).addComponent(btnPrev))
						.addContainerGap()));
		getContentPane().setLayout(layout);

		pack();
	}

	public static void main(String args[]) {
		init();
	}

	/*public static void init() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(() -> {
			
			
			new FrameCaisse().setVisible(true);
			
			
		});
	}*/
	
	public static void init() {
	    try {
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (ClassNotFoundException ex) {
	        java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(FrameCaisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }

	    SwingUtilities.invokeLater(() -> {
	        FrameCaisse frameCaisse = new FrameCaisse();
	        frameCaisse.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      //  frameCaisse.setUndecorated(true);
	        frameCaisse.setVisible(true);
	    });
	}
	
	
	

	public void createMtable(List<Mvtcaisse> listMtv) {
		Object[][] tableau = new Object[listMtv.size()][17];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listMtv.size());
		System.out.println("__________________________________");
		for (Mvtcaisse mvt : listMtv) {
			tableau[i][0] = false;
			tableau[i][1] = mvt.getIdclient();
			tableau[i][2] = mvt.getLibelleRubrique(mvt.getIdrubriquemvcaisse());
			tableau[i][3] = mvt.getNom() + " " + mvt.getPrenom();
			tableau[i][4] = mvt.getMvt_datemvt();
			tableau[i][5] = mvt.getMvt_montant();
			tableau[i][6] = mvt.getSolde();
			tableau[i][7] = mvt.getDate_enregis();
			tableau[i][8] = mvt.isValide();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau, new String[] { "", "Id Utilisateur", "Rubr.", "Livret",
				"Date", "Montant", "Solde", "Enregistrement", "Validé" })

		{
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 8) {
					return Boolean.class; // Colonne des cases � cocher
				} else {
					if (columnIndex == 0) {
						return Boolean.class; // Colonne des cases � cocher
					} else {
						return super.getColumnClass(columnIndex);
					}
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0; // Seule la colonne des cases � cocher est �ditable
			}
		};

		myTable.setModel(model);
		TableColumn column = myTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(2);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(3);
		column.setPreferredWidth(300);
		column = myTable.getColumnModel().getColumn(4);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(5);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(6);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(7);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(8);
		column.setPreferredWidth(1);
	}

	DialogMvt bonUse;

	private void listeBonsUse(MouseEvent evt, Mvtcaisse caiseselect) {

		bonUse = new DialogMvt(this, caiseselect);
		bonUse.setLocationRelativeTo(null);
		bonUse.setDialogCallback(this);
		bonUse.show();

	}

	@Override
	public void onButtonClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiser(long provenance) {
		// TODO Auto-generated method stub
		listMtv = service.getListMvtcaisse(offset);
		createMtable(listMtv);
	}

	private void checkAllCheckboxInTable() {
		int checkBoxColumn = 0; // Numéro de la colonne des cases à cocher

		for (int row = 0; row < myTable.getRowCount(); row++) {
			myTable.setValueAt(isChecked, row, checkBoxColumn);
		}

		isChecked = !isChecked; // Inverser l'état
	}

	private boolean isValidDateRange() {
		Date depart = datedepart.getDate();
		Date fin = datefin.getDate();

		if (depart == null || fin == null) {
			return false;
		}

		if (!depart.after(fin)) {
			return true;
		} else {
			if (!depart.after(fin)) {
				return true;
			}
		}

		return false;
	}

}
