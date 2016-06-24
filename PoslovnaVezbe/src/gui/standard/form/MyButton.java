package gui.standard.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.ModelContentProvider;
import gui.standard.form.StateManager.State;
import rs.mgifos.mosquito.model.MetaTable;

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
	
	public MyButton setButtonZoomTable(MetaTable table){
		zoomTableName = table.getCode();
		return this;
	}
	
	public String getButtonZoomTable(){
		return zoomTableName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel zoomDialogPanel = new AddAnchorDialog(ModelContentProvider.getTableByCode(zoomTableName)).getPanel();
		int n = JOptionPane.showConfirmDialog(null,zoomDialogPanel,zoomTableName,JOptionPane.OK_CANCEL_OPTION);
		
	
	}
}
