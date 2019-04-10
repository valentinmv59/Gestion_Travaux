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

public class Panel extends JFrame {
	private JTextField pseudo, password;	
	private Label connStatut, labelConnected;
	private JPanel Login_Panel, entete, Menu, entete_menu, Historique;
	private JButton btnHistoriqueDesTravaux, btnValiderLesTravaux;

	private JTable table;
	private List <Tache> tachesList;

	public class TacheTableModel extends AbstractTableModel {

		String[] entetes = {"Id", "Libelle", "Descriptif", "Priorité"};

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return tachesList == null ? 0 : tachesList.size();
			//return 6;
		}

		@Override
		public int getColumnCount() {
			return entetes.length;
		}

		@Override
		public String getColumnName(int column) {
			return entetes[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if ( columnIndex == 0 )
				return tachesList.get(rowIndex).getId();
			if ( columnIndex == 1 )
				return tachesList.get(rowIndex).getLibelle();
			if ( columnIndex == 2 )
				return tachesList.get(rowIndex).getDescriptif();
			if ( columnIndex == 3 )
				return tachesList.get(rowIndex).getPriorite();
			return null;
		}


	}

	private TacheTableModel tableModel;

	Bdd base;

	private DemandeFrame demandeFrame;

	public Panel() throws SQLException {
		base = new Bdd("localhost", "mrbs", "root", "root");

		ImageIcon image = new ImageIcon( getClass().getResource("logo.png"));
		setIconImage(image.getImage());
		setTitle("Maison des Ligues - Gestion Travaux");
		setSize(new Dimension(550, 400));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new CardLayout(0, 0));

		Login_Panel = new JPanel();
		Login_Panel.setName("Login panel");
		Login_Panel.setBounds(0, 0, 444, 270);
		Login_Panel.setLayout(null);

		entete = new JPanel();
		entete.setBackground(Color.ORANGE);
		entete.setBounds(10, 11, 534, 165);
		Login_Panel.add(entete);
		entete.setLayout(null);

		Historique = new JPanel();
		Historique.setLayout(null);
		Historique.setName("Historique");
		getContentPane().add(Historique, Historique.getName());

		JLabel Logo = new JLabel("");
		Logo.setBounds(148, 6, 239, 159);
		entete.add(Logo);

		ImageIcon image2 = new ImageIcon( getClass().getResource("logo.png"));
		if (image2 != null)
			Logo.setIcon(image2);

		demandeFrame = new DemandeFrame ();
		demandeFrame.setName("demande");

		JPanel corps = new JPanel();
		corps.setBounds(10, 177, 534, 195);
		Login_Panel.add(corps);
		corps.setLayout(null);

		pseudo = new JTextField();
		pseudo.setBounds(331, 36, 193, 20);
		corps.add(pseudo);
		pseudo.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(331, 68, 193, 20);
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
		btnConnexion.setBounds(321, 100, 102, 23);
		corps.add(btnConnexion);

		connStatut = new Label("Veuillez vous identifier.");
		connStatut.setAlignment(Label.CENTER);
		connStatut.setBounds(331, 150, 193, 14);
		corps.add(connStatut);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(435, 100, 89, 23);
		corps.add(btnQuitter);

		Label labelPseudo = new Label("Pseudo");
		labelPseudo.setBounds(230, 36, 95, 22);
		corps.add(labelPseudo);

		Label labelPassword = new Label("Mot de passe");
		labelPassword.setBounds(230, 68, 95, 22);
		corps.add(labelPassword);

		Label label = new Label("2018 - Maison des Ligues inc.");
		label.setEnabled(false);
		label.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC, 12));
		label.setBounds(0, 173, 215, 22);
		corps.add(label);

		Menu = new JPanel();
		Menu.setName("Menu panel");
		Menu.setBounds(0, 0, 444, 271);
		Menu.setLayout(null);

		entete_menu = new JPanel();
		entete_menu.setLayout(null);
		entete_menu.setBackground(Color.GREEN);
		entete_menu.setBounds(10, 6, 534, 123);
		Menu.add(entete_menu);

		JPanel corps_menu = new JPanel();
		corps_menu.setBounds(10, 131, 534, 241);
		Menu.add(corps_menu);
		corps_menu.setLayout(null);

		Label label_3 = new Label("2018 - Maison des Ligues inc.");
		label_3.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC, 12));
		label_3.setEnabled(false);
		label_3.setBounds(0, 219, 215, 22);
		corps_menu.add(label_3);

		labelConnected = new Label("Connecté en tant que ");
		labelConnected.setBounds(10, 11, 159, 28);
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
		btnDeconnexion.setBounds(363, 139, 137, 23);
		corps_menu.add(btnDeconnexion);

		btnValiderLesTravaux = new JButton("Valider des travaux");
		btnValiderLesTravaux.setBounds(329, 55, 171, 23);
		corps_menu.add(btnValiderLesTravaux);

		JButton btnDeclarePanne = new JButton("Déclarer une panne !");
		btnDeclarePanne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//((CardLayout)getContentPane().getLayout()).show(getContentPane(), demandeFrame.getName());
				DemandeFrame toto;
				try {
					toto = new DemandeFrame();
					toto.setBounds(10, 10, 500, 300);
					toto.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});
		corps_menu.add(btnDeclarePanne);
		btnHistoriqueDesTravaux = new JButton("Historique");
		btnHistoriqueDesTravaux.addActionListener(new ActionListener() {
			@Override
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
		btnHistoriqueDesTravaux.setBounds(363, 97, 137, 23);
		corps_menu.add(btnHistoriqueDesTravaux);

		btnDeclarePanne.setBounds(307, 16, 193, 23);
		corps_menu.add(btnDeclarePanne);

		getContentPane().add(Login_Panel, Login_Panel.getName());
		getContentPane().add(Menu, Menu.getName());

		((CardLayout)getContentPane().getLayout()).show(getContentPane(), Login_Panel.getName());

		JPanel corps_historique = new JPanel();
		corps_historique.setBounds(10, 131, 424, 129);
		Historique.add(corps_historique);
		corps_historique.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		JScrollPane scrollpan = new JScrollPane(table);
		scrollpan.setBounds(10, 132, 534, 240);
		//tachesList = base.findAllTaches();
		tableModel = new TacheTableModel();
		table.setModel(tableModel);
		corps_historique.add(scrollpan);

		JPanel Entete_historique = new JPanel();
		Entete_historique.setLayout(null);
		Entete_historique.setBackground(new Color(46, 139, 87));
		Entete_historique.setBounds(10, 11, 534, 118);
		Historique.add(Entete_historique);


		JButton btnRetourAccueil = new JButton("Accueil");
		btnRetourAccueil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), Menu.getName());
			}
		});
		btnRetourAccueil.setBounds(391, 85, 137, 23);
		Entete_historique.add(btnRetourAccueil);
	}

	protected void alimenteTableTache() throws SQLException {
		tachesList = base.findAllTaches();
		tableModel.fireTableDataChanged();;
	}
}
