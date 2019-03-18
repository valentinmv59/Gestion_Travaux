import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bdd {
	private String server, base, user, pswrd;
	Connection conn;

	String connectionURL;

	public int connLevel = 999;
	public boolean connected = false;
	private String usrPseudo;
	private int usrLevel;

	public bdd(String server, String base, String user, String pswrd) throws SQLException {
		super();
		this.server = server;
		this.base = base;
		this.user = user;
		this.pswrd = pswrd;

		connectionURL = "jdbc:mysql://" + server + ":3307/" + base + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

		conn = DriverManager.getConnection(connectionURL, user, pswrd);

	}

	public void SelectAll(String from) throws SQLException {

		Statement Execution = conn.createStatement();

		String requete = "SELECT * FROM " +from;

		ResultSet rs = Execution.executeQuery(requete);

		while(rs.next()) {
			System.out.println( "ID : " + rs.getInt("id")+" Email : "+rs.getString("email") + "\n");
		}
	}

	public boolean CheckConnexion(String pseudo, String password) throws SQLException {

		if(conn.isClosed())
			conn = DriverManager.getConnection(connectionURL, user, pswrd);

		Statement Execution = conn.createStatement();

		String requete = "SELECT * FROM mrbs_users where name = '"+pseudo+"' AND password_hash = md5('"+password+"')";

		ResultSet rs = Execution.executeQuery(requete);

		while(rs.next()) {
			usrPseudo = rs.getString(3);
			usrLevel = rs.getInt(2);
			connected = true;
		}

		return connected;   
	}

	public String getUsrPseudo() {
		return usrPseudo.substring(0, 1).toUpperCase() + usrPseudo.substring(1);
	}

	public int getUsrLevel() {
		return usrLevel;
	}

	public void CloseConnexion() throws SQLException {
		conn.close();
	}

}
