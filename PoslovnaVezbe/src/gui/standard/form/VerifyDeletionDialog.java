package gui.standard.form;

import java.awt.Component;
import java.awt.TrayIcon.MessageType;
import java.sql.ResultSetMetaData;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import database.PrepareDeleteQuery;
import database.QueryManager;
import database.StatementExecutioner;

public class VerifyDeletionDialog extends DialogAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatementExecutioner se;

	@Override
	public void setupDialog(Component parentFrame) {
		// TODO Auto-generated method stub	
		JOptionPane jop = new JOptionPane();		
		int choice = jop.showOptionDialog
		(parentFrame, "Da li zelite da izbrisete izabrani slog", "Brisanje",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null , 
				null, parentFrame);		
		if(choice == JOptionPane.OK_OPTION){			
			String dQuery = QueryManager.getDeleteQuery().prepareQueryForDB(((StandardForm)parentFrame).getFormTable().getName());
			ResultSetMetaData mdata = QueryManager.getSearchQuery().getMetaData(((StandardForm)parentFrame).getFormTable().getName());
			se = new StatementExecutioner(dQuery, mdata,((StandardForm)parentFrame));
			((StandardForm)parentFrame).getFormTable().deleteRowFromTable();
		}else{
			return;
		}
	}
}
