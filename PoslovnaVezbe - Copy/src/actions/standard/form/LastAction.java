package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.StandardForm;

public class LastAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;


	public LastAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/last.gif")));
		putValue(SHORT_DESCRIPTION, "Poslednji");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		standardForm.getFormTable().setRowSelectionInterval(standardForm.getFormTable().getRowCount() - 1, standardForm.getFormTable().getRowCount() - 1);
		//standardForm.updateDataPanel();
	}
}
