package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.StandardForm;
import gui.zoom.form.ZoomForm;


public class NextFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	
	public NextFormAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/nextform.gif")));
		putValue(SHORT_DESCRIPTION, "Sledeća forma");
		this.standardForm  = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ZoomForm zf = new ZoomForm(standardForm);
		zf.setVisible(true);
	}
}
