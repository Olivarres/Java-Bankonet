package lib;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;

// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:

public class DBManager {
	public java.sql.Connection connection;
	
	public DBManager() {
	
	}

	public void connectMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet_db","root", "");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}

	public void connectMongo() {
	}
}
