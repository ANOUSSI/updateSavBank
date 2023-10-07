package vue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

import Global.GlobalVar;
import dao.UserService;
import model.Agence;
import model.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrameClient extends javax.swing.JFrame implements DialogCallback{
	private javax.swing.JTable myTable;
	private JTextField textField;
	JComboBox<String> comboBox;
	JComboBox combolisclient;
	UserService service = new UserService();
	List<Client> listClients = new ArrayList<Client>();
	List<Client> listClientselected = new ArrayList<Client>();
	List<Agence> listAgence = new ArrayList<Agence>();
	private boolean isChecked = false;
	JComboBox comboBoxListAgence;
	int offset = 0;
	String id_client = "Id du Client";
	String nomouprenom = "Nom ou Prenom";
	String nomEtBureau = "Bureau de post - nom et prenom";
	String afficherTousClientAgence = "Afficher tous les Client Par Agence";
	FrameCaisse frameCaisse;
	
	
	// declaration des variable
	private JButton btnNewButtonValider;
	private javax.swing.JScrollPane jScrollPane1;
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public FrameClient() {
    	setFont(new Font("Times New Roman", Font.BOLD, 24));
    	setTitle("SAV BANK-GESTION DES CLIENTS");
    	getContentPane().setBackground(Color.BLUE);
    	setBackground(Color.BLUE);
    	listAgence = service.getListAgence();
        initComponents();
        for (Agence agence : listAgence) {
			comboBoxListAgence.addItem(agence.getRaisonsociale());
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
	public void createMtable(List<Client> listClient) {
		System.out.println("sizes " + listClient.size());
		Object[][] tableau = new Object[listClient.size()][11];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listClient.size());
		System.out.println("__________________________________");

		for (Client client : listClient) {
			tableau[i][0] = false;
			tableau[i][1] = client.getRaisonsociale();
			tableau[i][2] = client.getIdclient();
			tableau[i][3] = client.getCoden();
			tableau[i][4] = client.getCode();
			tableau[i][5] = client.getIpost();
			tableau[i][6] = client.getNom() + " " + client.getPrenom();
			tableau[i][7] = client.getSolde();
			tableau[i][8] = client.getSolde_mouvement();
			tableau[i][9] = client.getDate_enregistre();
			tableau[i][10] = client.isValide();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau, new String[] { "", "Raison sociale", "Id Utilisateur", "N.Num",
				"N ALpha", "N IPOST", "Livret", "Solde", "Solde Mvt", "Enregistrement", "Validé" })

		{
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 10) {
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
		column.setPreferredWidth(1);
		column = myTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(2);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(3);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(4);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(5);
		column.setPreferredWidth(20);
		column = myTable.getColumnModel().getColumn(6);
		column.setPreferredWidth(200);
		column = myTable.getColumnModel().getColumn(7);
		column.setPreferredWidth(100);
		column = myTable.getColumnModel().getColumn(8);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(9);
		column.setPreferredWidth(5);
		column = myTable.getColumnModel().getColumn(10);
		column.setPreferredWidth(1);

	}


    private void initComponents() {
    	
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 255));
        jScrollPane1 = new JScrollPane();
        comboBoxListAgence = new JComboBox();
    	combolisclient = new JComboBox();
      //  JTable myTable = new JTable();
    	 myTable = new javax.swing.JTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        comboBox = new JComboBox<>();
    	comboBox.addItem(id_client);
		comboBox.addItem(nomouprenom);
		comboBox.addItem(afficherTousClientAgence);
		comboBox.addItem(nomEtBureau);
		createMtable(listClients);
		// Add a MouseListener to the table
				myTable.addMouseListener(new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// Check for double-click event
						if (e.getClickCount() == 2) {
							// Get the selected row index
							// int rowIndex = myTable.getSelectedRow();
							int selectedRow = myTable.getSelectedRow();
							Client clientlect = listClients.get(selectedRow);
							System.out.println("-------------------is isActive----------------- ");
							System.out.println("is isActive "+clientlect.isActive());
							
							listeBonsUse(e, clientlect);

						}
					}

				});
		
		
		
        JButton btnComptes = new JButton("Menu Mouvements");
        btnComptes.setBackground(new Color(184, 134, 11));
        btnComptes.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GlobalVar.IDUSER = user.getId();
				dispose();
				// JOptionPane.showMessageDialog(null, " Bien Connecter");

				frameCaisse = new FrameCaisse();
				frameCaisse.init();
			}
		});
        
                btnComptes.setText("Gestion de Client");
        btnNewButtonValider = new JButton();
        btnNewButtonValider.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnNewButtonValider.setBackground(new Color(0, 128, 64));
        btnNewButtonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// searchUser
				String selecteurdetype = comboBox.getSelectedItem().toString();
				listClients.clear();
				createMtable(listClients);
				if (selecteurdetype.equalsIgnoreCase(id_client)) {
					String textsearch = textField.getText().toString();

					try {

						long idclient = Long.parseLong(textsearch);
						
						listClients = service.getListClient(idclient);
						createMtable(listClients);
					} catch (NumberFormatException ex) {
						// La conversion a échoué, la valeur n'est pas un nombre
						JOptionPane.showMessageDialog(null, "Cette valeur n'est pas un nombre.", "Alerte",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					if (selecteurdetype.equalsIgnoreCase(afficherTousClientAgence)) {
						
						
						

						
					
						
						final JDialog progressDialog = createProgressDialog(frameCaisse);

                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                            	System.out.println("-----------------------JE PASSSSSSSSSSSSSSSSSS");
								String uneAgence = comboBoxListAgence.getSelectedItem().toString();
								
								GlobalVar.PAGE = 0;
								listClients = service.getAllClientsAgence(uneAgence, GlobalVar.PAGE);
								createMtable(listClients);
                                return null;
                            }

                            @Override
                            protected void done() {
                                progressDialog.dispose();
                            }
                        };
                        worker.execute();

                        progressDialog.setVisible(true);
						
						
						
						
						
						
						
						
						
						
						

						// createMtable
					} else {

						if (selecteurdetype.equalsIgnoreCase(nomouprenom)) {
							
							
							final JDialog progressDialog = createProgressDialog(frameCaisse);

	                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                            @Override
	                            protected Void doInBackground() throws Exception {
	                            	
	    							String textsearch = textField.getText().toString().trim();
	    							listClients = service.getAllClientsByName(textsearch);
	    							
	    							createMtable(listClients);
	                                return null;
	                            }

	                            @Override
	                            protected void done() {
	                                progressDialog.dispose();
	                            }
	                        };
	                        worker.execute();

	                        progressDialog.setVisible(true);
							
							

							// createMtable
						} else {

							if (selecteurdetype.equalsIgnoreCase(nomEtBureau)) {
								

								int idAgence = listAgence.get(comboBoxListAgence.getSelectedIndex()).getIdagence();
								long idClient = listClientselected.get(combolisclient.getSelectedIndex()).getIdclient();

								listClients = service.getAllClientByIdAgenceAndIdclient(idAgence, idClient);
								createMtable(listClients);
								// createMtable
							} else {

							}

						}

					}

				}

				

			}
		});
        btnNewButtonValider.setText("Rafraichir");
        
        JLabel lblNewLabel_1 = new JLabel("Rechercher par:");
        
        
        JButton btnNewButton_1 = new JButton("Tout selectionner");
        btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAllCheckboxInTable();
			}
		});
        comboBoxListAgence.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) comboBoxListAgence.getSelectedItem();
				// String selectedOption = (String) comboBoxListAgence.getSelectedItem();

				System.out.println("selectedOption " + selectedOption);
				System.out.println(
						"selectedOption item " + listAgence.get(comboBoxListAgence.getSelectedIndex()).getIdagence());
				listClientselected = service
						.getListClientByIdAgence(listAgence.get(comboBoxListAgence.getSelectedIndex()).getIdagence());
				combolisclient.removeAllItems();
				for (Client client : listClientselected) {
					combolisclient.addItem(client.getNom() + " " + client.getPrenom());
				}

			}
		});
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Valeur");
        
     
        
        combolisclient = new JComboBox();
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Bureau de Poste");
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Nom du client");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(6)
        					.addComponent(comboBox, 0, 121, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNewButton_1))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        					.addGap(18)
        					.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        					.addGap(26)
        					.addComponent(lblNewLabel_1_1_2, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(comboBoxListAgence, 0, 100, Short.MAX_VALUE)
        					.addGap(18)
        					.addComponent(combolisclient, 0, 76, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnNewButtonValider, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnComptes, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_1_1)
        				.addComponent(lblNewLabel_1_1_1)
        				.addComponent(lblNewLabel_1_1_2)
        				.addComponent(lblNewLabel_1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnComptes, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(btnNewButton_1)
        					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(comboBoxListAgence, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(combolisclient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(btnNewButtonValider, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

       /* myTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));*/
        jScrollPane1.setViewportView(myTable);
        
        JPanel jPanel1_1 = new JPanel();
        jPanel1_1.setBackground(Color.ORANGE);
        
        JButton BoutonSuppr = new JButton();
        BoutonSuppr.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				String valuesString = GlobalVar.getSelectItemForJtableClient(myTable, listClients);
				if (valuesString.length() > 2) {
					int option = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de vouloir effectuer cette action ?", "Confirmation",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						System.out.println("Action confirmée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Oui".
						boolean isSupprimer = service.deleteClients(valuesString);
						if (isSupprimer) {

							/*System.out.println("Données sélectionnées : " + valuesString);
							String uneAgence = comboBoxListAgence.getSelectedItem().toString();
							listClients.clear();
							GlobalVar.PAGE = 0;
							listClients = service.getAllClientsAgence(uneAgence, GlobalVar.PAGE);
							createMtable(listClients);*/
							
							
							

							
							final JDialog progressDialog = createProgressDialog(frameCaisse);

	                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                            @Override
	                            protected Void doInBackground() throws Exception {
	                            	System.out.println("-----------------------JE PASSSSSSSSSSSSSSSSSS");
									String uneAgence = comboBoxListAgence.getSelectedItem().toString();
									listClients.clear();
									GlobalVar.PAGE = 0;
									listClients = service.getAllClientsAgence(uneAgence, GlobalVar.PAGE);
									createMtable(listClients);
	                                return null;
	                            }

	                            @Override
	                            protected void done() {
	                                progressDialog.dispose();
	                            }
	                        };
	                        worker.execute();

	                        progressDialog.setVisible(true);
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							

						} else {
							JOptionPane.showMessageDialog(null, " Operation Echoué");

						}

					} else if (option == JOptionPane.NO_OPTION) {
						System.out.println("Action annulée.");
						// Ajoutez ici le code à exécuter si l'utilisateur clique sur "Non".
					} else if (option == JOptionPane.CLOSED_OPTION) {
						System.out.println("Boîte de dialogue fermée.");
						// Ajoutez ici le code à exécuter si l'utilisateur ferme la boîte de dialogue.
					}
				}else {
					JOptionPane.showMessageDialog(null, " Selectionnez les elements à supprimer");
				}

			}
        	
        	
        	
        	
        });
        BoutonSuppr.setText("Suprimer");
        
        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if(offset < 0)
				// listClients = service.getListClient(offset++);
				// createMtable(listClients);

				// searchUser
				String selecteurdetype = comboBox.getSelectedItem().toString();
				if (selecteurdetype.equalsIgnoreCase(id_client)) {
					String textsearch = textField.getText().toString();

					try {

						long idclient = Long.parseLong(textsearch);
						listClients.clear();
						listClients = service.getListClient(idclient);
					} catch (NumberFormatException ex) {
						// La conversion a échoué, la valeur n'est pas un nombre
						JOptionPane.showMessageDialog(null, "Cette valeur n'est pas un nombre.", "Alerte",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					if (selecteurdetype.equalsIgnoreCase(afficherTousClientAgence)) {
						
						final JDialog progressDialog = createProgressDialog(frameCaisse);

                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                            	System.out.println("-----------------------JE PASSSSSSSSSSSSSSSSSS");
                            	String uneAgence = comboBoxListAgence.getSelectedItem().toString();
        						List<Client> listClientsiN = service.getAllClientsAgence(uneAgence, GlobalVar.PAGE);
        						listClients.addAll(listClientsiN);
        						
								createMtable(listClients);
                                return null;
                            }

                            @Override
                            protected void done() {
                                progressDialog.dispose();
                            }
                        };
                        worker.execute();

                        progressDialog.setVisible(true);
						
						
						// createMtable
					} else {

						if (selecteurdetype.equalsIgnoreCase(nomouprenom)) {
							
							
							
							
							final JDialog progressDialog = createProgressDialog(frameCaisse);

	                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                            @Override
	                            protected Void doInBackground() throws Exception {
	                            	listClients.clear();
	    							String textsearch = textField.getText().toString().trim();
	    							listClients = service.getAllClientsByName(textsearch);
	    							createMtable(listClients);
	                                return null;
	                            }

	                            @Override
	                            protected void done() {
	                                progressDialog.dispose();
	                            }
	                        };
	                        worker.execute();

	                        progressDialog.setVisible(true);
							
							
							
							
							
							
							// createMtable
						} else {

							if (selecteurdetype.equalsIgnoreCase(nomEtBureau)) {
								listClients.clear();

								int idAgence = listAgence.get(comboBoxListAgence.getSelectedIndex()).getIdagence();
								long idClient = listClientselected.get(combolisclient.getSelectedIndex()).getIdclient();

								listClients = service.getAllClientByIdAgenceAndIdclient(idAgence, idClient);
								createMtable(listClients);
								// createMtable
							} else {

							}

						}

					}

				}

				

			}
		});
        GroupLayout gl_jPanel1_1 = new GroupLayout(jPanel1_1);
        gl_jPanel1_1.setHorizontalGroup(
        	gl_jPanel1_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_jPanel1_1.createSequentialGroup()
        			.addGap(88)
        			.addComponent(BoutonSuppr, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
        			.addComponent(btnNext)
        			.addGap(289))
        );
        gl_jPanel1_1.setVerticalGroup(
        	gl_jPanel1_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1_1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanel1_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(BoutonSuppr)
        				.addComponent(btnNext))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1_1.setLayout(gl_jPanel1_1);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE)
        				.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        				.addComponent(jPanel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(jPanel1_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }

	DialogClient bonUse;

	private void listeBonsUse(MouseEvent evt, Client userselect) {
        
		bonUse = new DialogClient(this, userselect);
		bonUse.setLocationRelativeTo(null);
		bonUse.setDialogCallback(this);
		bonUse.show();

	}
    
    
    public static void main(String args[]) {
    	init();
    }
    
    
    
    
    
  /*  
    public static void init() {
    	
    	 try {
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                 if ("Nimbus".equals(info.getName())) {
                     javax.swing.UIManager.setLookAndFeel(info.getClassName());
                     break;
                 }
             }
         } catch (ClassNotFoundException ex) {
             java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
             java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
             java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }

         java.awt.EventQueue.invokeLater(() -> {
             new FrameClient().setVisible(true);
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
	        java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }

	    SwingUtilities.invokeLater(() -> {
	    	FrameClient frameClaise = new FrameClient();
	    	frameClaise.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    	frameClaise.setVisible(true);
	    });
	}
	
    
    
    
    
    

	@Override
	public void onButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void actualiser(long idutilisateur) {
		// TODO Auto-generated method stub
		listClients = service.getListClient(idutilisateur);
		createMtable(listClients);

	}

	private void checkAllCheckboxInTable() {
		int checkBoxColumn = 0; // Numéro de la colonne des cases à cocher

		for (int row = 0; row < myTable.getRowCount(); row++) {
			myTable.setValueAt(isChecked, row, checkBoxColumn);
		}

		isChecked = !isChecked; // Inverser l'état
	}

	private static JDialog createProgressDialog(JFrame parentFrame) {
		JDialog progressDialog = new JDialog(parentFrame, "Loading Data", Dialog.ModalityType.APPLICATION_MODAL);
        progressDialog.getContentPane().setLayout(new BorderLayout());

        JProgressBar circularProgressBar = new JProgressBar();
        circularProgressBar.setIndeterminate(true);  // Style circulaire
        circularProgressBar.setPreferredSize(new Dimension(50, 50));

        progressDialog.getContentPane().add(circularProgressBar, BorderLayout.CENTER);
        progressDialog.pack();
        progressDialog.setLocationRelativeTo(parentFrame);
        progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        return progressDialog;
		
	}

	
	
	
}