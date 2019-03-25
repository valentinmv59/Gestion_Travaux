import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Panel extends JFrame {
	private JTextField pseudo, password;	
	private Label connStatut, labelConnected;
	private JPanel Login_Panel, entete, Menu, entete_menu, Historique;
	private JButton btnHistoriqueDesTravaux, btnValiderLesTravaux;
	private JTable table;
	private List <Tache> tachesList;
	
	public class TacheTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return tachesList == null ? 0 : tachesList.size();
			//return 6;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if ( columnIndex == 0 )
				return tachesList.get(rowIndex).getDescriptif();
			return null;
		}
		
	}
	
	private TacheTableModel tableModel;
	
	bdd base;
	
	public Panel() throws SQLException {
		base = new bdd("localhost", "mrbs", "root", "root");

		ImageIcon image = new ImageIcon( getClass().getResource("logo.png"));
		setIconImage(image.getImage());
		setTitle("Maison des Ligues - Gestion Travaux");
		setSize(new Dimension(450,300));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new CardLayout(0, 0));

		Login_Panel = new JPanel();
		Login_Panel.setName("Login panel");
		Login_Panel.setBounds(0, 0, 444, 270);
		Login_Panel.setLayout(null);

		entete = new JPanel();
		entete.setBackground(Color.ORANGE);
		entete.setBounds(10, 11, 424, 118);
		Login_Panel.add(entete);
		entete.setLayout(null);
		
		Historique = new JPanel();
		Historique.setLayout(null);
		Historique.setName("Historique");
		getContentPane().add(Historique, Historique.getName());
		
		JLabel Logo = new JLabel("");
		Logo.setBounds(152, 11, 113, 77);
		entete.add(Logo);

		ImageIcon image2 = new ImageIcon( getClass().getResource("logo.png"));
		if (image2 != null)
			Logo.setIcon(image2);


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
						labelConnected.setText("Bonjour "+base.getUsrPseudo());
						if(base.getUsrLevel() == 2) {
							btnValiderLesTravaux.setEnabled(true);
							btnHistoriqueDesTravaux.setEnabled(true);
						}else {
							btnValiderLesTravaux.setEnabled(false);
							btnHistoriqueDesTravaux.setEnabled(false);							
						}
						TimeUnit.SECONDS.sleep(2);
						((CardLayout)getContentPane().getLayout()).show(getContentPane(), Menu.getName());
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
		btnConnexion.setBounds(231, 73, 102, 23);
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
		Menu.setName("Menu panel");
		Menu.setBounds(0, 0, 444, 271);
		Menu.setLayout(null);

		entete_menu = new JPanel();
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

		labelConnected = new Label("Connecté en tant que ");
		labelConnected.setBounds(10, 11, 137, 14);
		corps_menu.add(labelConnected);

		JButton btnDeconnexion = new JButton("Déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					base.CloseConnexion();
					((CardLayout)getContentPane().getLayout()).show(getContentPane(), Login_Panel.getName());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				password.setText("");
				connStatut.setForeground(Color.BLACK);
				connStatut.setText("Veuillez vous identifier.");
			}
		});
		btnDeconnexion.setBounds(277, 95, 137, 23);
		corps_menu.add(btnDeconnexion);

		JButton btnDeclarePanne = new JButton("Déclarer une panne");
		btnDeclarePanne.setBounds(277, 7, 137, 23);
		corps_menu.add(btnDeclarePanne);

		btnHistoriqueDesTravaux = new JButton("Historique");
		btnHistoriqueDesTravaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), Historique.getName());
				try {
					alimenteTableTache();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHistoriqueDesTravaux.setBounds(277, 65, 137, 23);
		corps_menu.add(btnHistoriqueDesTravaux);

		btnValiderLesTravaux = new JButton("Valider des travaux");
		btnValiderLesTravaux.setBounds(277, 36, 137, 23);
		corps_menu.add(btnValiderLesTravaux);

		getContentPane().add(Login_Panel, Login_Panel.getName());
		getContentPane().add(Menu, Menu.getName());

		((CardLayout)getContentPane().getLayout()).show(getContentPane(), Login_Panel.getName());
		
		JPanel Entête_historique = new JPanel();
		Entête_historique.setLayout(null);
		Entête_historique.setBackground(new Color(46, 139, 87));
		Entête_historique.setBounds(10, 11, 424, 118);
		Historique.add(Entête_historique);
		
		Label label_2 = new Label("Connecté en tant que ");
		label_2.setBounds(10, 94, 137, 14);
		Entête_historique.add(label_2);
		
		JButton btnRetourAccueil = new JButton("Accueil");
		btnRetourAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), Menu.getName());
			}
		});
		btnRetourAccueil.setBounds(277, 84, 137, 23);
		Entête_historique.add(btnRetourAccueil);
		
		JPanel corps_historique = new JPanel();
		corps_historique.setBounds(10, 131, 424, 129);
		Historique.add(corps_historique);
		corps_historique.setLayout(new BorderLayout(0, 0));
		
		String[] entetes = {"Travaux", "Descriptif", "Date début", "Etat"};
		table = new JTable();
		JScrollPane scrollpan = new JScrollPane(table);
		//tachesList = base.findAllTaches();
		tableModel = new TacheTableModel();
		table.setModel(tableModel);
		corps_historique.add(scrollpan);
	}
	
	protected void alimenteTableTache() throws SQLException {
		tachesList = base.findAllTaches();
		tableModel.fireTableDataChanged();;
	}
}
