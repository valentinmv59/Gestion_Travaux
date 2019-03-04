

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Demande extends JFrame {
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtHyj;
	private JComboBox comboBox;
	private JLabel lblQui;
	private JComboBox comboBox_1;
	private JLabel lblO;
	private JLabel lblIntitulDesTravaux;
	private JTextField textField;
	private JLabel lblCommentairesdtails;
	private JTextField textField_1;
	private JLabel lblMaison;
	public Demande() {
		setTitle("Maison des ligues - Gestion Travaux" );
		getContentPane().add(getPanel(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getTxtHyj());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getComboBox());
			panel_1.add(getLblQui());
			panel_1.add(getComboBox_1());
			panel_1.add(getLblO());
			panel_1.add(getLblIntitulDesTravaux());
			panel_1.add(getTextField());
			panel_1.add(getLblCommentairesdtails());
			panel_1.add(getTextField_1());
			panel_1.add(getLblMaison());
		}
		return panel_1;
	}
	private JTextField getTxtHyj() {
		if (txtHyj == null) {
			txtHyj = new JTextField();
			txtHyj.setHorizontalAlignment(SwingConstants.CENTER);
			txtHyj.setText("DEMANDE DE TRAVAUX");
			txtHyj.setColumns(10);
		}
		return txtHyj;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(236, 12, 52, 27);
		}
		return comboBox;
	}
	private JLabel getLblQui() {
		if (lblQui == null) {
			lblQui = new JLabel("Qui ?");
			lblQui.setBounds(163, 16, 61, 16);
		}
		return lblQui;
	}
	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(236, 40, 52, 27);
		}
		return comboBox_1;
	}
	private JLabel getLblO() {
		if (lblO == null) {
			lblO = new JLabel("OÃ¹ ? ");
			lblO.setBounds(163, 44, 61, 16);
		}
		return lblO;
	}
	private JLabel getLblIntitulDesTravaux() {
		if (lblIntitulDesTravaux == null) {
			lblIntitulDesTravaux = new JLabel("Intitulé des travaux :");
			lblIntitulDesTravaux.setBounds(80, 72, 144, 16);
		}
		return lblIntitulDesTravaux;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(80, 89, 278, 26);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblCommentairesdtails() {
		if (lblCommentairesdtails == null) {
			lblCommentairesdtails = new JLabel("Commentaires/détails : ");
			lblCommentairesdtails.setBounds(80, 118, 160, 16);
		}
		return lblCommentairesdtails;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(80, 138, 278, 79);
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JLabel getLblMaison() {
		if (lblMaison == null) {
			lblMaison = new JLabel("2018 - Maison des ligues inc.");
			lblMaison.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
			lblMaison.setBounds(6, 230, 256, 16);
		}
		return lblMaison;
	}
}
