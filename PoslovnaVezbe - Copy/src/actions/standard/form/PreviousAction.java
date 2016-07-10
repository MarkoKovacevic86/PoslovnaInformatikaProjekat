package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.StandardForm;

public class PreviousAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public PreviousAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/prev.gif")));
		putValue(SHORT_DESCRIPTION, "Prethodni");
		this.standardForm=standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(standardForm.getFormTable().getSelectedRow() < 0 || standardForm.getFormTable().getSelectedRow() == 0){
			standardForm.getFormTable().setRowSelectionInterval(standardForm.getFormTable().getRowCount() - 1,standardForm.getFormTable().getRowCount() - 1);
		}else{
			standardForm.getFormTable().setRowSelectionInterval(standardForm.getFormTable().getSelectedRow() - 1, standardForm.getFormTable().getSelectedRow() - 1);
		}
		//standardForm.updateDataPanel();
	}
}
