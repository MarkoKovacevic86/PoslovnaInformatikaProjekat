package gui.standard.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.standard.form.StateManager.State;

public class MyButton extends JButton implements ActionListener{
	String zoomTableName;
	StandardForm containingForm;
	
	public MyButton(){
		addActionListener(this);
	}
	
	public void setContainingForm(StandardForm sf){
		containingForm = sf;
	}
	
	public StandardForm getContainingForm(){
		return containingForm;
	}
	
	public MyButton(String name){
		super(name);		
		addActionListener(this);
	}
	
	public MyButton setButtonZoomTable(String name){
		zoomTableName = name;
		return this;
	}
	
	public String getButtonZoomTable(){
		return zoomTableName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub						
		StandardForm sf = new StandardForm(zoomTableName);
		sf.getStateManager().setState(State.ZOOM);
		//sf.getToolbar().setVisible(false);
		sf.setRefButton(this);
		sf.setVisible(true);
	}
}
