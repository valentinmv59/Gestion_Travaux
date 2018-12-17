import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Panel app = new Panel();
		bdd base = new bdd("localhost", "mrbs", "root", "root");
		base.SelectAll("mrbs_users");
	}
}
