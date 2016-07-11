package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.util.ShadowedSymbolTable;

import gui.search.form.MyTabbedPane;
import gui.search.form.SearchForm;
import gui.standard.form.StandardForm;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public SearchAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/search.gif")));
		putValue(SHORT_DESCRIPTION, "Pretraga");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//SearchForm sf = new SearchForm(standardForm.getFormTable());
		MyTabbedPane mtb = new MyTabbedPane(standardForm.getFormTable());
		int n = JOptionPane.showConfirmDialog(null, mtb, standardForm.getfName(), JOptionPane.OK_CANCEL_OPTION);
		if(n == 0){
			if(mtb.getSelectedIndex() == 0){
				JTextField tf = (JTextField)mtb.getActiveTab().getSearchField();
				standardForm.getFormTable().showSearchedRows(tf.getText(), mtb.getActiveTab().getStandardComboBoxType());
			}else{
				System.out.println("upao");
				for(Object tf : mtb.getActiveTab().getTextField()){
					JTextField cast = (JTextField)tf;
					System.out.println(cast.getName());
					standardForm.getFormTable().showSearchedRows(cast.getText(), cast.getName());
				}
			}
		}
	}
}
