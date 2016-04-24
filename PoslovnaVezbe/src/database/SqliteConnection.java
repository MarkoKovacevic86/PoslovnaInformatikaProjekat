package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class SqliteConnection {
	
	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null)
			try {
				open();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return conn;
	}
	
	public static void open() throws ClassNotFoundException, SQLException {
		if (conn != null)
			return;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:Resources/PoslovnaIBaza2.sqlite");
		conn.setAutoCommit(false);
		JOptionPane.showMessageDialog(null, "Konekcija uspesna");
	}

	public static void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

}
