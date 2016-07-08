package actions.standard.form;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import database.ForeignKeyGetter;
import gui.standard.form.StandardForm;
import gui.standard.form.StateManager.State;



public class AddAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	//kada se napravi genericka forma, staviti tu klasu umesto JDialog
	private StandardForm standardForm;
	
	public AddAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/add.gif")));
		putValue(SHORT_DESCRIPTION, "Dodavanje");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {			
			ForeignKeyGetter fkg = new ForeignKeyGetter(standardForm.getFormTable().getName());
			ResultSet rs = fkg.getForeignKeyResultSet();
			standardForm.getDataPanel().enableTextFields(true);
			standardForm.getFormTable().clearSelection();
			standardForm.clearDataPanelFields();
			standardForm.zoomApropriateFields();			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		standardForm.getStateManager().setState(State.ADD);		
		//QueryManager.getInsertQuery().prepareQueryForDB(standardForm.getFormTable().getName());
	}
}
