package database;
import java.sql.Connection;

import javax.swing.JFrame;


public class Login {

	public JFrame frame;


	Connection connection = null;
	
	public Login() {
		initialize();
		connection = SqliteConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
