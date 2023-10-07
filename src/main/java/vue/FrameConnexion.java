package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Global.GlobalVar;
import Global.MyConnexion;
import dao.UserService;
import model.User;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class FrameConnexion {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	String imagePath = "user.png";
	String imagePath1 = "login.png";
	String imagePath2 = "connected.png";
	String imagePath0 = "logo.jpg";
	 int desiredWidt = 35;
	 
	 int desiredHeigh = 35;
	/*BufferedImage resizedImage = resizeImage(imagePath, desiredWidt, desiredHeigh);
	BufferedImage resizedImage1 = resizeImage(imagePath1, desiredWidt, desiredHeigh);
	BufferedImage resizedImage2 = resizeImage(imagePath2, desiredWidt, desiredHeigh);
	BufferedImage resizedImage3 = resizeImage(imagePath0, desiredWidt, desiredHeigh);*/
	UserService service = new UserService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		init();
	}
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameConnexion window = new FrameConnexion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public FrameConnexion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		// Create an ImageIcon from the byte array
		
		//BufferedImage resizedImage1 = resizeImage(imagePath0, desiredWidt, desiredHeigh);
		//ImageIcon icon5 = new ImageIcon(resizedImage5);
		//if(resizedImage1!=null) {
		//	System.out.println("je suis null");
		//}else {
			System.out.println("je ne suis pas null");
		//}
		//ImageIcon imageIcon = new ImageIcon(resizedImage1);

		// Use the ImageIcon as needed
		//frame.setIconImage(imageIcon.getImage());

		frame.setBounds(100, 100, 437, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		panel_1.setBounds(0, 11, 421, 69);
		panel.add(panel_1);
		panel_1.setLayout(null);
	
		
		//BufferedImage resizedImage5 = resizeImage(imagePath0, desiredWidt, desiredHeigh);
///		ImageIcon icon5 = new ImageIcon(resizedImage5);

		JLabel lblNewLabel = new JLabel("PAGE DE LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 421, 69);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaptionBorder);
		panel_2.setBorder(new MatteBorder(10, 1, 1, 1, (Color) new Color(0, 0, 255)));
		panel_2.setBounds(0, 68, 421, 267);
		panel.add(panel_2);
		panel_2.setLayout(null);

	//	ImageIcon icon = new ImageIcon(resizedImage);
		//ImageIcon icon1 = new ImageIcon(resizedImage1);
	//	ImageIcon icon3 = new ImageIcon(resizedImage2);
		JLabel passwordLabel_1 = new JLabel("Mot de passe");
		passwordLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		passwordLabel_1.setVerticalAlignment(SwingConstants.TOP);
		passwordLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel_1.setBounds(10, 100, 147, 35);
		panel_2.add(passwordLabel_1);

		JLabel userLabel = new JLabel("Nom Utilisateur");
		userLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(10, 50, 170, 30);

		panel_2.add(userLabel);

		// userLabel.setIcon(null);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(167, 53, 179, 25);
		panel_2.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(167, 109, 179, 26);
		panel_2.add(passwordField);
		JButton btConnexion = new JButton("Connexion");
		btConnexion.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btConnexion.setForeground(Color.WHITE);
		btConnexion.setBackground(new Color(0, 51, 0));
	//	btConnexion.setIcon(icon3);

		btConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MyConnexion connexion = new MyConnexion();
// connexion à la table user
				////User user = service.get(textField.getText().toLowerCase(), passwordField.getText().toLowerCase());
	// connexion à la partir de l'ad			
				/// User user=service.getActi(textField.getText().toLowerCase(), passwordField.getText().toLowerCase());
				
			/*	
				if (user != null)
					 
					
				{
					GlobalVar.CurentClient=user;
					frame.dispose();
		        	MenuPrincipaleMvmtCaise frameMvt = new MenuPrincipaleMvmtCaise();
		        	frameMvt.init();
		        	
		        } else {
		        	JOptionPane.showMessageDialog(null, " Utilisateur non trouvé.");
		        }
				*/
				
				
				
				
				
				
				
				
				
				// connextion à partir du fichier
	
		     String filePath = GlobalVar.documentsPaths + "\\save_banck\\fichier.txt";
				 String[] userInfo =GlobalVar.searchUser(filePath,textField.getText().toLowerCase(), passwordField.getText().toLowerCase());

			        if (userInfo != null) {
			        	frame.dispose();
			        	//MenuPrincipaleMvmtCaise frameMvt = new MenuPrincipaleMvmtCaise();
			        	FrameCaisse frameCaisse = new FrameCaisse();
			        	frameCaisse.init();
			        	
			        } else {
			        	JOptionPane.showMessageDialog(null, " Utilisateur non trouvé.");
			         //  exit(0);
			        }
			    
				
				
			
			}

		});

		btConnexion.setBounds(147, 172, 137, 45);
		panel_2.add(btConnexion);

		JLabel lbdate = new JLabel("");
		lbdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		SimpleDateFormat dh = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		long date = System.currentTimeMillis();
		lbdate.setText(dh.format(date));
		lbdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbdate.setBounds(10, 216, 127, 45);
		panel_2.add(lbdate);

		JLabel lbseconde = new JLabel("");
		lbseconde.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbseconde.setText(df.format(date));
		lbseconde.setHorizontalAlignment(SwingConstants.CENTER);
		lbseconde.setBounds(310, 221, 89, 35);

		panel_2.add(lbseconde);

	}

	/*public static BufferedImage resizeImage(String nomIMage, int desiredWidth, int desiredHeight) {
		System.out.println("image " + nomIMage);
		URL imageUrl = FrameConnexion.class.getClassLoader().getResource(nomIMage);

		// Convert the URL to a File object
		if (imageUrl != null) {

			File imageFile = new File(imageUrl.getFile());
			if (imageFile != null) {
				BufferedImage resizedImage = null;
				try {

					// Chargement de l'image d'origine
					BufferedImage originalImage = ImageIO.read(imageFile);

					// Cr�ation d'une nouvelle image redimensionn�e
					Image resultingImage = originalImage.getScaledInstance(desiredWidth, desiredHeight,
							Image.SCALE_SMOOTH);
					resizedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_ARGB);

					// Copie de l'image redimensionn�e dans le nouveau BufferedImage
					Graphics2D g2d = resizedImage.createGraphics();
					g2d.drawImage(resultingImage, 0, 0, null);
					g2d.dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return resizedImage;
			}

		}
		return null;*/

	}
	/*
	 * public static BufferedImage resizeImage(String imagePath, int desiredWidth,
	 * int desiredHeight) { BufferedImage resizedImage = null; try { // Chargement
	 * de l'image d'origine BufferedImage originalImage = ImageIO.read(new
	 * File(imagePath));
	 * 
	 * // Cr�ation d'une nouvelle image redimensionn�e Image resultingImage =
	 * originalImage.getScaledInstance(desiredWidth, desiredHeight,
	 * Image.SCALE_SMOOTH); resizedImage = new BufferedImage(desiredWidth,
	 * desiredHeight, BufferedImage.TYPE_INT_ARGB);
	 * 
	 * // Copie de l'image redimensionn�e dans le nouveau BufferedImage Graphics2D
	 * g2d = resizedImage.createGraphics(); g2d.drawImage(resultingImage, 0, 0,
	 * null); g2d.dispose(); } catch (IOException e) { e.printStackTrace(); } return
	 * resizedImage; }
	 */


