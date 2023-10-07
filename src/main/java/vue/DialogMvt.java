package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;

import Global.GlobalVar;
import dao.InterService;
import dao.UserService;
import model.Agence;
import model.Client;
import model.Mvtcaisse;
import model.Rubriquemvcaisse;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.UIManager;

public class DialogMvt extends JDialog {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	UserService service = new UserService();
	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JComboBox comboBoxtextNumBon;
	private JComboBox comboBox_mvt;
	private DialogCallback dialogCallback;
	Mvtcaisse mvtcaisse;
	Mvtcaisse updatemvtcaisse;
	private JTextField textFieMontant;
	JComboBox comboBoxType;
	JDateChooser dateChooser;
	JCheckBox chckoxValider;
	JTextArea textArea;
	List<Agence> listAgence = new ArrayList<Agence>();
	List<Rubriquemvcaisse> listRubrique;
	//List<Rubriquemvcaisse> listRubriqueDebit = new ArrayList<>();
	//List<Rubriquemvcaisse> listRubriqueCredit = new ArrayList<>();
	JComboBox comboBoxAgence;

	public JComboBox getComboBox() {
		return comboBox;
	}
	
	/*
	 * public JTextField getTextFieldAvancer() { return textFieldAvancer; }
	 * 
	 * public void setTextFieldAvancer(JTextField textFieldAvancer) {
	 * this.textFieldAvancer = textFieldAvancer; }
	 * 
	 * public JRadioButton getrRadioButtonAvancer() { return rRadioButtonAvancer; }
	 * 
	 * public void setrRadioButtonAvancer(JRadioButton rRadioButtonAvancer) {
	 * this.rRadioButtonAvancer = rRadioButtonAvancer; }
	 * 
	 * public JRadioButton getRadioButtonNonPayer() { return RadioButtonNonPayer; }
	 * 
	 * public void setRadioButtonNonPayer(JRadioButton radioButtonNonPayer) {
	 * RadioButtonNonPayer = radioButtonNonPayer; }
	 * 
	 * public JRadioButton getrRadioButtonComptant() { return rRadioButtonComptant;
	 * }
	 * 
	 * public void setrRadioButtonComptant(JRadioButton rRadioButtonComptant) {
	 * this.rRadioButtonComptant = rRadioButtonComptant; }
	 */

	public JComboBox getComboBoxNumeroBon() {
		return comboBoxtextNumBon;
	}

	public void setComboBoxNumeroBon(JComboBox comboBoxtextNumBon) {
		this.comboBoxtextNumBon = comboBoxtextNumBon;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	/*
	 * public JTextField getTextFieldCamion() { return textFieldCamion; }
	 * 
	 * public void setTextFieldCamion(JTextField textFieldCamion) {
	 * this.textFieldCamion = textFieldCamion; }
	 */

	/*
	 * public JTextField getTextFieldQte() { return textFieldQte; }
	 * 
	 * public void setTextFieldQte(JTextField textFieldQte) { this.textFieldQte =
	 * textFieldQte; }
	 */

	/*
	 * public JComboBox getComboBox() { return comboBox; }
	 * 
	 * public void setComboBox(JComboBox comboBox) { this.comboBox = comboBox; }
	 */

	public void setDialogCallback(DialogCallback callback) {
		this.dialogCallback = callback;
	}

	/*
	 * public JDateChooser getdateChooser() { return dateChooser; }
	 * 
	 * public void setdateChooser(JDateChooser dateChooser) { this.dateChooser =
	 * dateChooser; }
	 */
	public DialogMvt(JFrame parent, Mvtcaisse caiseselect) {
		super(parent, "Dialog", true);
		this.mvtcaisse = caiseselect;
		setBounds(100, 100, 763, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		listAgence = service.getListAgence();
		listRubrique = service.getListRubrique();

		// comboBox = new JComboBox<>();
		// Ajouter des éléments à la combobox
		// comboBox

		// comboBox_3.addItem("CLIENT");
		// comboBox_3.addItem("OPERATION");

		// comboBox = new JComboBox(elements);
		// comboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		// comboBox.setBounds(479, 120, 181, 31);

		// contentPanel.add(comboBox);

		// comboBoxtextNumBon = new JComboBox(elementCommandes);
		// comboBoxtextNumBon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		// comboBoxtextNumBon.setBounds(479, 188, 171, 37);
		// contentPanel.add(comboBoxtextNumBon);

		////////////////////////////////////////////////////////////////////////////////////////

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 747, 70);
		contentPanel.add(panel);
		panel.setLayout(null);

		String imagePath = "logo1.jpg";
		int desiredWidth = 290;
		int desiredHeight = 67;

		// BufferedImage resizedImage = FrameConnexion.resizeImage(imagePath,
		// desiredWidth, desiredHeight);
		// ImageIcon icon = new ImageIcon(resizedImage);
		// JLabel lblNewLabel = new JLabel("ENREGISTREMENT D'UNE NOUVELLE
		// COMMANDE",icon, JLabel.LEFT);
		JLabel lblNewLabel = new JLabel("Correction du Mouvement");
		lblNewLabel.setForeground(Color.WHITE);
		// lblNewLabel.setIcon(new ImageIcon("logo 1.jpg"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(326, 11, 321, 48);

		panel.add(lblNewLabel);
		JLabel lblBon = new JLabel("RUBRIQUE DE MOUVEMENT");
		lblBon.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblBon.setHorizontalAlignment(SwingConstants.LEFT);
		lblBon.setBounds(371, 183, 171, 31);
		contentPanel.add(lblBon);

		JLabel lbCaminion = new JLabel("TYPE");
		lbCaminion.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbCaminion.setHorizontalAlignment(SwingConstants.LEFT);
		lbCaminion.setBounds(12, 194, 71, 22);
		contentPanel.add(lbCaminion);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class); // Type de nombre accept� (Integer)

		// formatter.setMinimum(0); // Valeur minimale
		// formatter.setMaximum(100); // Valeur maximale
		formatter.setAllowsInvalid(true);

		JLabel lblQte = new JLabel("MONTANT");
		lblQte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte.setBounds(12, 260, 126, 31);
		contentPanel.add(lblQte);

		JLabel lblQte_1 = new JLabel("OBSERVATION");
		lblQte_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_1.setBounds(12, 316, 126, 31);
		contentPanel.add(lblQte_1);

		JLabel lblQte_3 = new JLabel("DATE");
		lblQte_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3.setBounds(410, 232, 126, 31);
		contentPanel.add(lblQte_3);

		textFieMontant = new JTextField();

		textFieMontant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieMontant.setForeground(new Color(139, 0, 0));
		textFieMontant.setFont(new Font("Times New Roman", Font.BOLD, 26));
		textFieMontant.setColumns(10);
		textFieMontant.setBounds(148, 253, 171, 37);
		contentPanel.add(textFieMontant);

		JLabel lblAgence = new JLabel("AGENCE");
		lblAgence.setHorizontalAlignment(SwingConstants.LEFT);
		lblAgence.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAgence.setBounds(12, 134, 71, 22);
		contentPanel.add(lblAgence);

		JLabel lblQte_4_1 = new JLabel("LIVRET");
		lblQte_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_4_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_4_1.setBounds(410, 130, 126, 31);
		contentPanel.add(lblQte_4_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(545, 128, 172, 37);
		contentPanel.add(comboBox_2);

		comboBoxType = new JComboBox();
		comboBoxType.setBounds(150, 187, 172, 39);

		contentPanel.add(comboBoxType);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(546, 242, 145, 31);
		contentPanel.add(dateChooser);

		chckoxValider = new JCheckBox("Valider");
		chckoxValider.setBounds(546, 292, 97, 23);
		contentPanel.add(chckoxValider);

		comboBoxAgence = new JComboBox();
		comboBoxAgence.setFont(UIManager.getFont("Label.font"));
		comboBoxAgence.setBounds(147, 127, 172, 39);
		contentPanel.add(comboBoxAgence);
		ButtonGroup buttonGroup = new ButtonGroup();

		((AbstractDocument) textFieMontant.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				StringBuilder sb = new StringBuilder();
				sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
				sb.insert(offset, string);

				if (isNumeric(sb.toString())) {
					super.insertString(fb, offset, string, attr);
				}
			}
			

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				StringBuilder sb = new StringBuilder();
				sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
				sb.replace(offset, offset + length, text);

				if (isNumeric(sb.toString())) {
					super.replace(fb, offset, length, text, attrs);
				}
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				StringBuilder sb = new StringBuilder();
				sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
				sb.delete(offset, offset + length);

				if (isNumeric(sb.toString())) {
					super.remove(fb, offset, length);
				}
			}
		});

		/*
		 * ListSelectionModel selectionModel = jTable1.getSelectionModel();
		 * selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 * selectionModel.addListSelectionListener(new ListSelectionListener() {
		 * 
		 * @Override public void valueChanged(ListSelectionEvent e) { if
		 * (!e.getValueIsAdjusting()) { int selectedRow = jTable1.getSelectedRow();
		 * GlobalVar.CurentClient= list.get(selectedRow);
		 * textFieldName.setText(GlobalVar.CurentClient.getName()); } } });
		 */
		for (Agence agence : listAgence) {
			/*
			 * comboBoxAgence.addItem(agence.getLabel());
			 * if(agence.getCode().equalsIgnoreCase(this.mvtcaisse.getAgenceCode())) {
			 * comboBoxAgence.setSelectedItem(agence.getLabel()); }
			 */
			// System.out.println("Code client "+this.mvtcaisse.getAgenceCode()) ;
			// System.out.println("Code client "+this.mvtcaisse.getAgenceCode()) ;
			comboBoxAgence.addItem(agence.getRaisonsociale());
			if (agence.getCode() != null && agence.getCode().equalsIgnoreCase(this.mvtcaisse.getAgenceCode())) {
				comboBoxAgence.setSelectedItem(agence.getRaisonsociale());
			}

		}
		comboBox_2.addItem(this.mvtcaisse.getNom() + " " + this.mvtcaisse.getPrenom());

		comboBox_mvt = new JComboBox();
		comboBox_mvt.setBounds(552, 187, 165, 29);
		contentPanel.add(comboBox_mvt);

		textArea = new JTextArea();
		textArea.setBounds(66, 370, 625, 111);
		contentPanel.add(textArea);

		// comboBox_3.addItem("OPERATION");

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(0, 0, 255));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("ENREGISTRER");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dialogCallback != null) {
					dialogCallback.onButtonClicked();
				}
				if (showRubriqueMouvementSelected() != null) {

					updatemvtcaisse = new Mvtcaisse();
					updatemvtcaisse.setIdmvtcaisse(mvtcaisse.getIdmvtcaisse());
					updatemvtcaisse.setIdagence(listAgence.get(comboBoxAgence.getSelectedIndex()).getIdagence());// code
																													// agence
					updatemvtcaisse.setIdrubriquemvcaisse(showRubriqueMouvementSelected().getIdrubriquemvcaisse());
					updatemvtcaisse.setCoden(showRubriqueMouvementSelected().getCode());// code rubrique
					updatemvtcaisse.setTypemvt(showRubriqueMouvementSelected().getType());
					updatemvtcaisse.setMvt_montant(Integer.parseInt(textFieMontant.getText()));
					updatemvtcaisse.setDatemvt(dateChooser.getDate());// detemvt
					updatemvtcaisse.setObservation(textArea.getText());
					
					//this.mvtcaisse.isValide()
				//	System.out.println("chckoxValider.isSelected() "+chckoxValider.isSelected());
					updatemvtcaisse.setValide(chckoxValider.isSelected());

					// updatemvtcaisse.setM..
					System.out.println("updatemvtcaisse.getAgenceCode " + updatemvtcaisse.getAgenceCode());
					System.out.println(
							"updatemvtcaisse.getIdrubriquemvcaisse " + updatemvtcaisse.getIdrubriquemvcaisse());
					System.out.println("updatemvtcaisse.getCoden " + updatemvtcaisse.getCoden());
					System.out.println("updatemvtcaisse.getTypemvt " + updatemvtcaisse.getTypemvt());
					System.out.println("updatemvtcaisse.getMvt_montant " + updatemvtcaisse.getMvt_montant());
					System.out.println("updatemvtcaisse.getDatemvt " + updatemvtcaisse.getDatemvt());
					System.out.println("updatemvtcaisse.getObservation " + updatemvtcaisse.getObservation());
					if (service.updateMvtcaisse(updatemvtcaisse)) {
						//JOptionPane.showMessageDialog(null, "Operation effectu� avec succes\n merci!");
						JOptionPane.showMessageDialog(
			                    null,
			                    "Opération effectuée avec succès\n merci!!",
			                    "Succès",
			                    JOptionPane.INFORMATION_MESSAGE
			            );
						
						dialogCallback.actualiser(1);
					} else {
						JOptionPane.showMessageDialog(null, "Operation Echoué\n merci!"
								 ,"Échec",
				                    JOptionPane.ERROR_MESSAGE);
						
					}
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Operation Echoué\n Rubrique Mouvement est vide!",
							 "Échec",
			                    JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
	    //System.out.println("mvtcaisse.getCoden() lkkjk "+mvtcaisse.getCodemouv());
		//Rubriquemvcaisse rubriquet=showRubriqueMouvement(mvtcaisse.getCodemouv());
	
		//comboBox_mvt.addItem(mvtcaisse.getCodemouv());
		comboBox_mvt.addItem(mvtcaisse.getLibelleRubrique(mvtcaisse.getIdrubriquemvcaisse()));// affichage de la rubrique en fonction id rubrique
		JButton cancelButton = new JButton("ANNULER");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		buttonPane.add(cancelButton);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		

		// int DATA
		initData();

	}

	Rubriquemvcaisse showRubriqueMouvementSelected() {
		if (comboBox_mvt.getSelectedItem() != null) {
			String code = comboBox_mvt.getSelectedItem().toString().trim();
			System.out.println("recherche code "+code);
		//System.out.println(comboBox_mvt.getSelectedItem());
			for (Rubriquemvcaisse rubrique : listRubrique) {
				String codemvement=rubrique.getCode().trim();	
				System.out.println("code "+rubrique.getCode());
				if (codemvement.equalsIgnoreCase(code)) {
					System.out.println("code trouvé "+code);
					return rubrique;
				}
			}

		}
		
		return null;
	}

	
	Rubriquemvcaisse showRubriqueMouvement(String code) {
		System.out.println(comboBox_mvt.getSelectedItem());
		for (Rubriquemvcaisse rubrique : listRubrique) {
			if (rubrique.getCode().equalsIgnoreCase(code)) {
				return rubrique;
			}
		}

		return null;
	}
	
	
	private boolean isNumeric(String str) {
		return str.matches("\\d*"); // Utilisation d'une expression régulière pour vérifier si c'est numérique
	}

	public void initData() {
		comboBoxType.addItem("Debit");
		comboBoxType.addItem("Crédit");
		textFieMontant.setText( String.valueOf(   this.mvtcaisse.getMvt_montant()));
		try {
			Date date = dateFormat.parse(this.mvtcaisse.getMvt_datemvt());
			System.out.println("Date convertie : " + date);
			dateChooser.setDate(date);
			;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println("this.mvtcaisse.actif "+this.mvtcaisse.isValide());
		if (this.mvtcaisse.isValide()) {
			chckoxValider.setSelected(true);
		} else {
			chckoxValider.setSelected(false);
			;
		}
		
		comboBoxType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboBoxType.getSelectedItem();
				comboBox_mvt.removeAllItems();
				if (selectedValue.equalsIgnoreCase("Debit")) {
					for (Rubriquemvcaisse rubrique : listRubrique) {
						if (rubrique.getDebiorcredit().equalsIgnoreCase("Debit")) {
							// listRubriqueDebit.add(rubrique);
							comboBox_mvt.addItem(rubrique.getCode());
						}
					}
					

				} else {
					for (Rubriquemvcaisse rubrique : listRubrique) {
						if (!rubrique.getDebiorcredit().equalsIgnoreCase("Debit")) {
							// listRubriqueCredit.add(rubrique);
							comboBox_mvt.addItem(rubrique.getCode());
						}

					}
				}
			}
		});

	}
}
