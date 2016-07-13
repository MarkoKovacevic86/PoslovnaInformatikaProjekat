package actions.main.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.standard.form.StandardForm;

public class OmniAction extends AbstractAction{
	
	private String actionName;
	
	public OmniAction(String actionName){
		this.actionName = actionName;
		putValue(NAME, this.actionName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		StandardForm form = new StandardForm(this.actionName);
		if(form.getfName().equals("ANALITIKA_MAGACINSKE_KARTICE") || form.getfName().equals("STAVKE_DOKUMENTA") || form.getfName().equals("ROBNA_KARTICA")){
			form.getDataPanel().setVisible(false);
		}
		form.setVisible(true);
		
	}
}
