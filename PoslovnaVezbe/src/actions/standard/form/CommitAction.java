package actions.standard.form;


import java.awt.event.ActionEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import database.DBConnection;
import gui.standard.form.StandardForm;



public class CommitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	
	public CommitAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/commit.gif")));
		putValue(SHORT_DESCRIPTION, "Commit");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			standardForm.disableApropriateFields();
			standardForm.disableApropriateFields();
			standardForm.getDataPanel().buttonVisibility(false);
			standardForm.getStateManager().determainState();			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
	}
}

