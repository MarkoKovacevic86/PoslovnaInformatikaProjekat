package gui.standard.form;

import java.awt.Component;

import javax.swing.JOptionPane;

public class UniqueKeyErrorDialog extends DialogAdapter{

	@Override
	public void setupDialog() {
		// TODO Auto-generated method stub
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "Uneti kljuc vec postoji!/n Potrebno je uneti jedinstveni kljuc");
	}

}
