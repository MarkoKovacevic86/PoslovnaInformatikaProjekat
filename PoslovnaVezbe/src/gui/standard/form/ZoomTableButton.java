package gui.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.standard.form.AddAction;
import rs.mgifos.mosquito.model.MetaTable;

public class ZoomTableButton extends JButton implements IFormButton {

	String zoomTableName;
	//StandardForm containingForm;y
	MyPanel parentPanel;
	
	public ZoomTableButton(){
		this("");
	}
	
	public ZoomTableButton(String text){
		super(text);
		super.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(zoomTableName + "Here");
		StandardForm sf = new StandardForm(zoomTableName,this);
		sf.setVisible(true);
	}

/*	@Override
	public void setContainingForm(StandardForm sf) {
		// TODO Auto-generated method stub
		containingForm = sf;
	}
*/
	@Override
	public IFormButton setButtonZoomTable(String name) {
		zoomTableName = name;
		return this;
	}

	@Override
	public IFormButton setButtonZoomTable(MetaTable table) {
		// TODO Auto-generated method stub
		zoomTableName = table.getName();
		return this;
	}

	@Override
	public void setParentPanel(MyPanel parentPanel) {
		// TODO Auto-generated method stub
		this.parentPanel = parentPanel;
	}

}
