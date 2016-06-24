package actions.main.form;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

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


