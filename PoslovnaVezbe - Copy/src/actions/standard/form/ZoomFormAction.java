package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.standard.form.StandardForm;
import gui.zoom.form.ZoomForm;


public class ZoomFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm sForm;
	
	public ZoomFormAction(StandardForm sf) {
		putValue(SHORT_DESCRIPTION, "Zoom");
		putValue(NAME, "...");
		sForm = sf;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		ZoomForm zf = new ZoomForm(sForm);
		zf.setVisible(true);
	}
	
}
