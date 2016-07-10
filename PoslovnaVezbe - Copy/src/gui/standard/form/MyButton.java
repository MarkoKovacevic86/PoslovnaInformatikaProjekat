package gui.standard.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import database.ModelContentProvider;
import database.QueryManager;
import database.StatementExecutioner;
import gui.standard.form.StateManager.State;
import rs.mgifos.mosquito.model.MetaTable;

public class MyButton extends JButton implements IFormButton{
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
		MyPanel zoomDialogPanel = (MyPanel) new AddAnchorDialog(ModelContentProvider.getTableByCode(zoomTableName)).getPanel();
		int n = JOptionPane.showConfirmDialog(null,zoomDialogPanel,zoomTableName,JOptionPane.OK_CANCEL_OPTION);
		if(n == 0){
			ResultSetMetaData mdata = QueryManager.getSearchQuery().getMetaData(zoomTableName);
			String query = QueryManager.getInsertQuery().prepareQueryForDB(zoomTableName);
			StatementExecutioner se = new StatementExecutioner(query, mdata, zoomDialogPanel);
			se.executeStatement();
			for(Object cfField : containingForm.getDataPanel().getTextField()){
				for(Object zField : zoomDialogPanel.getTextField()){
					JTextComponent parentCast = (JTextComponent)cfField;
					JTextComponent childCast = (JTextComponent) zField;
					if(parentCast.getName().equals(childCast.getName())){
						parentCast.setText(childCast.getText());
						
					}
				}
			}
		}
	
	}
}
