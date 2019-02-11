import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.SystemColor;
import java.awt.MenuBar;

public class Panel extends JFrame {
	private JTextField pseudo, password;
	
	private Label connStatut;
	private JPanel Login_Panel, Menu;
	public Panel() throws SQLException {
		bdd base = new bdd("localhost", "mrbs", "root", "root");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\shado\\eclipse-workspace\\Gestion_Travaux\\src\\main\\java\\logo.png"));
		setTitle("Maison des Ligues - Gestion Travaux");
		setSize(new Dimension(450,300));
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		Login_Panel = new JPanel();
		layeredPane.setLayer(Login_Panel, 1);
		Login_Panel.setBounds(0, 0, 444, 270);
		layeredPane.add(Login_Panel);
		Login_Panel.setLayout(null);
		
		JPanel entete = new JPanel();
		
		entete.setBackground(Color.ORANGE);
		entete.setBounds(10, 11, 424, 118);
		Login_Panel.add(entete);
		entete.setLayout(null);
		
		JPanel corps = new JPanel();
		corps.setBounds(10, 132, 424, 129);
		Login_Panel.add(corps);
		corps.setLayout(null);
		
		pseudo = new JTextField();
		pseudo.setBounds(231, 11, 193, 20);
		corps.add(pseudo);
		pseudo.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(231, 42, 193, 20);
		corps.add(password);
		setVisible(true);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(base.CheckConnexion(pseudo.getText(), password.getText())) {
						connStatut.setForeground(Color.GREEN);
						connStatut.setText("Connexion en cours..");
						TimeUnit.SECONDS.sleep(2);
						layeredPane.setLayer(Login_Panel, 0);
						layeredPane.setLayer(Menu, 1);
					}
					else {
						connStatut.setForeground(Color.RED);
						connStatut.setText("Identifiants incorrects !");
					}						
				} catch (SQLException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConnexion.setBounds(231, 73, 89, 23);
		corps.add(btnConnexion);
		
		connStatut = new Label("Veuillez vous identifier.");
		connStatut.setAlignment(Label.CENTER);
		connStatut.setBounds(231, 104, 193, 14);
		corps.add(connStatut);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(335, 73, 89, 23);
		corps.add(btnQuitter);
		
		Label labelPseudo = new Label("Pseudo");
		labelPseudo.setBounds(130, 11, 95, 22);
		corps.add(labelPseudo);
		
		Label labelPassword = new Label("Mot de passe");
		labelPassword.setBounds(130, 42, 95, 22);
		corps.add(labelPassword);
		
		Label label = new Label("2018 - Maison des Ligues inc.");
		label.setEnabled(false);
		label.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC, 12));
		label.setBounds(0, 107, 215, 22);
		corps.add(label);
		
		Menu = new JPanel();
		layeredPane.setLayer(Menu, 0);
		Menu.setBounds(0, 0, 444, 271);
		layeredPane.add(Menu);
		Menu.setLayout(null);
		
		JPanel entete_menu = new JPanel();
		entete_menu.setLayout(null);
		entete_menu.setBackground(Color.GREEN);
		entete_menu.setBounds(10, 11, 424, 118);
		Menu.add(entete_menu);
		
		JPanel corps_menu = new JPanel();
		corps_menu.setBounds(10, 131, 424, 129);
		Menu.add(corps_menu);
		corps_menu.setLayout(null);
		
		Label label_3 = new Label("2018 - Maison des Ligues inc.");
		label_3.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC, 12));
		label_3.setEnabled(false);
		label_3.setBounds(0, 107, 215, 22);
		corps_menu.add(label_3);
		
		Label labelConnected = new Label("Connecté en tant que Admin");
		labelConnected.setBounds(10, 11, 137, 14);
		corps_menu.add(labelConnected);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					base.CloseConnexion();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				password.setText("");
				connStatut.setForeground(Color.BLACK);
				connStatut.setText("Veuillez vous identifier.");
				layeredPane.setLayer(Login_Panel, 1);
				layeredPane.setLayer(Menu, 0);
			}
		});
		btnDeconnexion.setBounds(10, 36, 137, 23);
		corps_menu.add(btnDeconnexion);
		
		JButton btnDeclarePanne = new JButton("D\u00E9clarer une panne");
		btnDeclarePanne.setBounds(277, 7, 137, 23);
		corps_menu.add(btnDeclarePanne);
		
	}
}
