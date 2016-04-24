package actions.main.form;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import database.SqliteConnection;
import gui.standard.form.StandardForm;


public class DrzaveAction  extends AbstractAction{
	private static final long serialVersionUID = 1L;
	
	public DrzaveAction() {
		KeyStroke ctrlDKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK);
		putValue(ACCELERATOR_KEY,ctrlDKeyStroke);
		putValue(SHORT_DESCRIPTION, "Države");
		putValue(NAME, "Države");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		StandardForm form = new StandardForm("Drzava");
		form.setVisible(true);
	}
}


