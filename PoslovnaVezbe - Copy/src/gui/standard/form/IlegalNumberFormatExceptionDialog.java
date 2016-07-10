package gui.standard.form;

import javax.swing.JOptionPane;

public class IlegalNumberFormatExceptionDialog extends DialogAdapter{
	@Override
	public void setupDialog() {
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "Tip unete vrednosti nije odgovarajuci");
	}
}
