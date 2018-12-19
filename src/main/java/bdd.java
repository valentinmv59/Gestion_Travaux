import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bdd {
	private String server, base, user, pswrd;
	Connection conn;
	
	String connectionURL;

	public bdd(String server, String base, String user, String pswrd) throws SQLException {
		super();
		this.server = server;
		this.base = base;
		this.user = user;
		this.pswrd = pswrd;
		
		connectionURL = "jdbc:mysql://" + server + ":3306/" + base + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		
	}
	
	public void SelectAll(String from) throws SQLException {
		Connection conn = DriverManager.getConnection(connectionURL, user, pswrd);
		
		Statement Execution = conn.createStatement();
		
	    String requete = "SELECT * FROM " +from;
	   
	    ResultSet rs = Execution.executeQuery(requete);
	    
	    while(rs.next()) {
		    System.out.println( "ID : " + rs.getInt("id")+" Email : "+rs.getString("email") + "\n");
	    }
	}
	
	public void CheckConnexion(String pseudo, String password) throws SQLException {
		Connection conn = DriverManager.getConnection(connectionURL, user, pswrd);
		
		Statement Execution = conn.createStatement();
		
	    String requete = "SELECT * FROM mrbs_users where name = '"+pseudo+"' AND password_hash = md5('"+password+"')";
	    System.out.print(password);
	    ResultSet rs = Execution.executeQuery(requete);
	    
	    while(rs.next()) {
		    System.out.println( "ID : " + rs.getInt("id")+" Email : "+rs.getString("email") + "\n");
	    }
	}
	
	public void FermerConnexion() throws SQLException {
		conn.close();
	}
	
}
