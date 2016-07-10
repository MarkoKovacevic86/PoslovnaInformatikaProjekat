package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.StandardForm;
import gui.standard.form.VerifyDeletionDialog;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	
	public DeleteAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Brisanje");
		this.standardForm=standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(standardForm.getFormTable().getSelectedRow() != -1){
			VerifyDeletionDialog vdd = new VerifyDeletionDialog();
			vdd.setupDialog(standardForm);		
		}
	}
}
