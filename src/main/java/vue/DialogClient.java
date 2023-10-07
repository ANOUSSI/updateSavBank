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
import model.Ville;

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

public class DialogClient extends JDialog {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	UserService service = new UserService();
	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JComboBox comboBoxtextNumBon;
	private JComboBox comboBox_sexe;
	private DialogCallback dialogCallback;
	Client userClient;
	Client updateuserClient;
	JDateChooser dateChooser;
	JCheckBox chckoxValider;
	JCheckBox chckoxActiver;

	List<Agence> listAgence = new ArrayList<Agence>();
	List<Ville> listVille = new ArrayList<Ville>();

	List<Rubriquemvcaisse> listRubrique;
	// List<Rubriquemvcaisse> listRubriqueDebit = new ArrayList<>();
	// List<Rubriquemvcaisse> listRubriqueCredit = new ArrayList<>();
	JComboBox comboBoxAgence;
	private JTextField textFieldAphanumerique;
	private JTextField textFieldIpost;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldLieuDeNaissanse;
	private JTextField textFieldNomPere;
	private JTextField textFieldPrenomPere;
	private JTextField textFieldNomMere;
	private JTextField textFieldPrenomMere;
	private JTextField textFieldContact;
	private JTextField textNature;
	private JComboBox comboBoxVille;
	private JTextField textFieldNumerique;
	private JTextField textFieldSolde;

	public JComboBox getComboBox() {
		return comboBox;
	}


	public JComboBox getComboBoxNumeroBon() {
		return comboBoxtextNumBon;
	}

	public void setComboBoxNumeroBon(JComboBox comboBoxtextNumBon) {
		this.comboBoxtextNumBon = comboBoxtextNumBon;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	

	public void setDialogCallback(DialogCallback callback) {
		this.dialogCallback = callback;
	}

	
	public DialogClient(JFrame parent, Client caiseselect) {
		super(parent, "Dialog", true);
		this.userClient = caiseselect;
		setBounds(100, 100, 875, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		listAgence = service.getListAgence();
		listRubrique = service.getListRubrique();
		listVille = service.getVille();

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
		panel.setBounds(0, 0, 859, 70);
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
		JLabel lblNewLabel = new JLabel("MODIFIER UN MOUVEMENT");
		lblNewLabel.setForeground(Color.WHITE);
		// lblNewLabel.setIcon(new ImageIcon("logo 1.jpg"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(302, 0, 338, 70);
		panel.add(lblNewLabel);
		textFieldAphanumerique = new JTextField();
		JLabel lbCaminion = new JLabel("Nature");
		lbCaminion.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbCaminion.setHorizontalAlignment(SwingConstants.LEFT);
		lbCaminion.setBounds(12, 134, 71, 22);
		contentPanel.add(lbCaminion);
		chckoxActiver = new JCheckBox("Activer");
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class); // Type de nombre accept� (Integer)

		// formatter.setMinimum(0); // Valeur minimale
		// formatter.setMaximum(100); // Valeur maximale
		formatter.setAllowsInvalid(true);

		JLabel lblQte = new JLabel("N° Numerique");
		lblQte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte.setBounds(10, 182, 97, 31);
		contentPanel.add(lblQte);

		JLabel lblQte_3 = new JLabel("Date de Naissance");
		lblQte_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3.setBounds(379, 81, 126, 31);
		contentPanel.add(lblQte_3);
		textFieldSolde = new JTextField();
		textFieldSolde.setForeground(new Color(255, 0, 0));
		textFieldSolde.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSolde.setFont(new Font("Times New Roman", Font.BOLD, 22));
		JLabel lblAgence = new JLabel("AGENCE");
		lblAgence.setHorizontalAlignment(SwingConstants.LEFT);
		lblAgence.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAgence.setBounds(10, 89, 71, 22);
		contentPanel.add(lblAgence);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(527, 81, 172, 31);
		contentPanel.add(dateChooser);
		comboBoxVille = new JComboBox();
		comboBoxVille.setFont(new Font("Times New Roman", Font.BOLD, 15));

		chckoxValider = new JCheckBox("Valider");
		chckoxValider.setBounds(601, 563, 97, 23);
		contentPanel.add(chckoxValider);
		textNature = new JTextField();
		textFieldPrenom = new JTextField();
		textFieldLieuDeNaissanse = new JTextField();
		textFieldNomPere = new JTextField();
		textFieldPrenomPere = new JTextField();
		textFieldNomMere = new JTextField();
		textFieldPrenomMere = new JTextField();
		textFieldContact = new JTextField();
		textFieldNumerique = new JTextField();
		textFieldIpost = new JTextField();
		comboBoxAgence = new JComboBox();
		textFieldNom = new JTextField();
		comboBox_sexe = new JComboBox();
		comboBoxAgence.setFont(UIManager.getFont("Label.font"));
		comboBoxAgence.setBounds(135, 81, 171, 29);
		contentPanel.add(comboBoxAgence);
		ButtonGroup buttonGroup = new ButtonGroup();

		for (Agence agence : listAgence) {

			comboBoxAgence.addItem(agence.getRaisonsociale());
			if (agence.getCode() != null && agence.getIdagence() == this.userClient.getIdagence()) {
				comboBoxAgence.setSelectedItem(agence.getRaisonsociale());
			}

		}
		textNature.setText(userClient.getNature());

		 textFieldNumerique.setText(String.valueOf(userClient.getCoden()));
		textFieldAphanumerique.setText(userClient.getCode() );

		textFieldIpost.setText(String.valueOf(userClient.getIpost()));

		textFieldPrenom.setText(String.valueOf(userClient.getPrenom()));
		textFieldNom.setText(String.valueOf(userClient.getNom()));
		if (userClient.getLieunaissance() != null)
			textFieldLieuDeNaissanse.setText(String.valueOf(userClient.getLieunaissance()));
		else
			textFieldLieuDeNaissanse.setText("");
		textFieldNomPere.setText(String.valueOf(userClient.getNompernompere()));
		textFieldPrenomPere.setText(String.valueOf(userClient.getPernompere()));
		textFieldNomMere.setText(String.valueOf(userClient.getNomprenommere()));
		textFieldPrenomMere.setText(String.valueOf(userClient.getPrenommere()));

		if (userClient.getContact() != null)
			textFieldContact.setText(String.valueOf(userClient.getContact()));
		else
			textFieldContact.setText("");
		textFieldSolde.setText(String.valueOf(userClient.getSolde()));
		for (Ville ville : listVille) {

			comboBoxVille.addItem(ville.getLibelle());
			if (ville.getId_ville() == userClient.getId_ville()) {
				comboBoxAgence.setSelectedItem(ville.getLibelle());
			}

		}

		comboBox_sexe.addItem("Masculin");
		comboBox_sexe.addItem("Feminin");
		if (userClient.getSexe() == "Masculin") {
			comboBoxAgence.setSelectedItem("Masculin");
		} else {
			if (userClient.getSexe() == "Feminin") {
				comboBoxAgence.setSelectedItem("Masculin");
			}
		}
		
		//userClient.getCode()
	//	userClient.getCoden()

		/*
		 * client = new Client(); client.setIdclient(rs.getLong("idclient"));
		 * client.setCode(rs.getString("code")); client.setCoden(rs.getLong("coden"));
		 * client.setIdagence(rs.getInt("idagence"));
		 * client.setRaisonsociale(rs.getString("raisonsociale"));
		 * client.setDate_naissance(rs.getString("date_naissance"));
		 * client.setIpost(rs.getString("ipost"));
		 * client.setNature(rs.getString("nature"));
		 * client.setValide(rs.getBoolean("valide"));
		 * client.setNom(rs.getString("nom")); client.setPrenom(rs.getString("prenom"));
		 * client.setNompernompere(rs.getString("nompernompere"));
		 * client.setNomprenommere(rs.getString("nomprenommere"));
		 * client.setPernompere(rs.getString("pernompere"));
		 * client.setPrenommere(rs.getString("prenommere"));
		 * client.setDate_enregistre(rs.getString("date_enregistre"));
		 * client.setSolde(rs.getDouble("solde"));
		 * 
		 * 
		 */

		comboBox_sexe.setBounds(527, 182, 172, 29);
		contentPanel.add(comboBox_sexe);

		textFieldAphanumerique.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAphanumerique.setForeground(new Color(0, 0, 0));
		textFieldAphanumerique.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldAphanumerique.setColumns(10);
		textFieldAphanumerique.setBounds(136, 228, 171, 31);
		contentPanel.add(textFieldAphanumerique);

		JLabel lblNAlphanumerque = new JLabel("N° Alphanumerque");
		lblNAlphanumerque.setHorizontalAlignment(SwingConstants.LEFT);
		lblNAlphanumerque.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNAlphanumerque.setBounds(12, 232, 124, 31);
		contentPanel.add(lblNAlphanumerque);

		textFieldIpost.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIpost.setForeground(new Color(0, 0, 0));
		textFieldIpost.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textFieldIpost.setColumns(10);
		textFieldIpost.setBounds(136, 293, 171, 31);
		contentPanel.add(textFieldIpost);

		JLabel lblNIpost = new JLabel("N° IPOST");
		lblNIpost.setHorizontalAlignment(SwingConstants.LEFT);
		lblNIpost.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNIpost.setBounds(12, 292, 124, 31);
		contentPanel.add(lblNIpost);

		textFieldNom.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNom.setForeground(new Color(0, 0, 0));
		textFieldNom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(100, 351, 233, 31);
		contentPanel.add(textFieldNom);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNom.setBounds(17, 352, 90, 31);
		contentPanel.add(lblNom);

		textFieldPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrenom.setForeground(new Color(0, 0, 0));
		textFieldPrenom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(136, 415, 171, 31);
		contentPanel.add(textFieldPrenom);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrenom.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPrenom.setBounds(17, 415, 90, 31);
		contentPanel.add(lblPrenom);

		textFieldLieuDeNaissanse.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLieuDeNaissanse.setForeground(new Color(139, 0, 0));
		textFieldLieuDeNaissanse.setFont(new Font("Times New Roman", Font.BOLD, 26));
		textFieldLieuDeNaissanse.setColumns(10);
		textFieldLieuDeNaissanse.setBounds(528, 134, 171, 31);
		contentPanel.add(textFieldLieuDeNaissanse);

		JLabel lblQte_3_1 = new JLabel("Lieu de Naissance");
		lblQte_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1.setBounds(379, 134, 126, 31);
		contentPanel.add(lblQte_3_1);

		JLabel lblQte_3_1_1 = new JLabel("Sexe");
		lblQte_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1_1.setBounds(379, 178, 126, 31);
		contentPanel.add(lblQte_3_1_1);

		textFieldNomPere.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNomPere.setForeground(new Color(0, 0, 0));
		textFieldNomPere.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldNomPere.setColumns(10);
		textFieldNomPere.setBounds(528, 222, 171, 31);
		contentPanel.add(textFieldNomPere);

		JLabel lblQte_3_1_1_1 = new JLabel("Nom du prere");
		lblQte_3_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1_1_1.setBounds(379, 228, 126, 31);
		contentPanel.add(lblQte_3_1_1_1);

		textFieldPrenomPere.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrenomPere.setForeground(new Color(0, 0, 0));
		textFieldPrenomPere.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldPrenomPere.setColumns(10);
		textFieldPrenomPere.setBounds(528, 271, 171, 31);
		contentPanel.add(textFieldPrenomPere);

		JLabel lblQte_3_1_1_1_1 = new JLabel("Prenom du prere");
		lblQte_3_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1_1_1_1.setBounds(379, 270, 126, 31);
		contentPanel.add(lblQte_3_1_1_1_1);

		textFieldNomMere.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNomMere.setForeground(new Color(0, 0, 0));
		textFieldNomMere.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldNomMere.setColumns(10);
		textFieldNomMere.setBounds(528, 320, 171, 31);
		contentPanel.add(textFieldNomMere);

		textFieldPrenomMere.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrenomMere.setForeground(new Color(0, 0, 0));
		textFieldPrenomMere.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldPrenomMere.setColumns(10);
		textFieldPrenomMere.setBounds(527, 375, 171, 31);
		contentPanel.add(textFieldPrenomMere);

		JLabel lblQte_3_1_1_1_1_1 = new JLabel("Prenom de la mere");
		lblQte_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1_1_1_1_1.setBounds(379, 365, 126, 31);
		contentPanel.add(lblQte_3_1_1_1_1_1);

		JLabel lblQte_3_1_1_1_1_2 = new JLabel("Nom de la mere");
		lblQte_3_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte_3_1_1_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte_3_1_1_1_1_2.setBounds(379, 306, 126, 31);
		contentPanel.add(lblQte_3_1_1_1_1_2);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setHorizontalAlignment(SwingConstants.LEFT);
		lblVille.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblVille.setBounds(17, 470, 90, 31);
		contentPanel.add(lblVille);

		textFieldContact.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldContact.setForeground(new Color(139, 0, 0));
		textFieldContact.setFont(new Font("Times New Roman", Font.BOLD, 26));
		textFieldContact.setColumns(10);
		textFieldContact.setBounds(528, 430, 171, 31);
		contentPanel.add(textFieldContact);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setHorizontalAlignment(SwingConstants.LEFT);
		lblContact.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblContact.setBounds(393, 419, 90, 31);
		contentPanel.add(lblContact);

		JLabel lblSolde = new JLabel("SOLDE");
		lblSolde.setHorizontalAlignment(SwingConstants.LEFT);
		lblSolde.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSolde.setBounds(393, 483, 90, 31);
		contentPanel.add(lblSolde);

		chckoxActiver.setBounds(508, 563, 71, 23);
		contentPanel.add(chckoxActiver);

		comboBoxVille.setBounds(136, 471, 172, 31);
		contentPanel.add(comboBoxVille);

		JButton addVille = new JButton("+");
		addVille.setBounds(313, 471, 57, 31);
		contentPanel.add(addVille);

		textNature.setHorizontalAlignment(SwingConstants.CENTER);
		textNature.setForeground(new Color(0, 0, 0));
		textNature.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textNature.setColumns(10);
		textNature.setBounds(136, 136, 171, 31);
		contentPanel.add(textNature);

		textFieldNumerique.setBounds(135, 182, 171, 31);
		contentPanel.add(textFieldNumerique);
		textFieldNumerique.setColumns(10);

		textFieldSolde.setBounds(514, 472, 199, 48);
		contentPanel.add(textFieldSolde);
		textFieldSolde.setColumns(10);

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
			
					updateuserClient = new Client();
					updateuserClient.setIdclient(userClient.getIdclient());
					updateuserClient.setIdagence(listAgence.get(comboBoxAgence.getSelectedIndex()).getIdagence());// agence
					updateuserClient.setValide(chckoxValider.isSelected());
					
					System.out.println("----------is aceit---------------- "+chckoxActiver.isSelected());
					updateuserClient.setActive(chckoxActiver.isSelected());
				
					updateuserClient.setNature(textNature.getText());
					
					updateuserClient.setCode(textFieldAphanumerique.getText());
					
					updateuserClient.setCoden( Long.parseLong( textFieldNumerique.getText() ));
					
					updateuserClient.setIpost( textFieldIpost.getText());
					updateuserClient.setPrenom( textFieldPrenom.getText());
					updateuserClient.setNom(textFieldNom.getText());
					System.out.println("dateChooser.getDate() "+dateChooser.getDate());
					updateuserClient.setDatenaissance(dateChooser.getDate());
					
					updateuserClient.setLieunaissance(textFieldLieuDeNaissanse.getText());
					
					updateuserClient.setNompernompere( textFieldNomPere.getText());
						
					updateuserClient.setPernompere(textFieldPrenomPere.getText());
					
					updateuserClient.setNomprenommere(textFieldNomMere.getText());
					
					updateuserClient.setPrenommere(textFieldPrenomMere.getText());
					 
					updateuserClient.setContact(textFieldContact.getText());
					updateuserClient.setSolde(Double.parseDouble(textFieldSolde.getText()));
					updateuserClient.setId_ville(listVille.get(comboBoxVille.getSelectedIndex()).getId_ville());
					updateuserClient.setSexe(comboBoxVille.getSelectedItem().toString());
					
				//	comboBoxType_livret.getSelectedItem().toString()
					
					
					
					if (service.updateAllAtributClient(updateuserClient)) {
						JOptionPane.showMessageDialog(null, "Operation effectu� avec succes\n merci!");
						dialogCallback.actualiser(userClient.getIdclient());
					} else {
						JOptionPane.showMessageDialog(null, "Operation Echoué\n merci!");
						
					}
					
					dispose();

			}
		});
		// System.out.println("mvtcaisse.getCoden() lkkjk "+mvtcaisse.getCodemouv());
		// Rubriquemvcaisse rubriquet=showRubriqueMouvement(mvtcaisse.getCodemouv());

		////// comboBox_mvt.addItem(userClient.getCodemouv());
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
		if (comboBox_sexe.getSelectedItem() != null) {
			String code = comboBox_sexe.getSelectedItem().toString().trim();
			System.out.println("recherche code " + code);
			// System.out.println(comboBox_mvt.getSelectedItem());
			for (Rubriquemvcaisse rubrique : listRubrique) {
				String codemvement = rubrique.getCode().trim();
				System.out.println("code " + rubrique.getCode());
				if (codemvement.equalsIgnoreCase(code)) {
					System.out.println("code trouvé " + code);
					return rubrique;
				}
			}

		}

		return null;
	}

	Rubriquemvcaisse showRubriqueMouvement(String code) {
		System.out.println(comboBox_sexe.getSelectedItem());
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

		//////// textFieMontant.setText( String.valueOf(
		//////// this.userClient.getMvt_montant()));
		     
		try {
			Date date = dateFormat.parse(userClient.getDate_naissance());
			System.out.println("Date convertie : " + date);
			dateChooser.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (!this.userClient.isValide()) {
			chckoxValider.setSelected(false);
		} else {
			chckoxValider.setSelected(true);
		}
		 System.out.println("la valeur est 111    "+this.userClient.isActif());
		if (!this.userClient.isActif()) {
			chckoxActiver.setSelected(false);
		} else {
			chckoxActiver.setSelected(true);
		}
		
		// valide

	}
}
