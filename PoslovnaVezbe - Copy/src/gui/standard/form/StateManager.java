package gui.standard.form;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JTextField;

import database.ModelContentProvider;
import database.QueryManager;
import database.StatementExecutioner;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;
import standardform.control.ActiveForms;

public class StateManager {
	public enum State { ADD, UPDATE,DEFAULT, ZOOM} ;
	private State state = State.DEFAULT;
	private StandardForm parentForm;
	private StatementExecutioner se;
	
	public StateManager(StandardForm pform){
		parentForm = pform;
	}
	
	public StandardForm getParentForm(){
		return parentForm;
	}
	
	public void setState(State s){
		state = s;
	}
	
	public void determainState() throws SQLException{	
		ResultSetMetaData mdata = QueryManager.getSearchQuery().getMetaData(parentForm.getFormTable().getName());
		switch (state) {		
		case ADD:		
			System.out.println(parentForm.getfName() + "Insertig data");
			String query = QueryManager.getInsertQuery().prepareQueryForDB(parentForm.getFormTable().getName());
			se = new StatementExecutioner(query, mdata, parentForm.getDataPanel());
			se.executeStatement();
			parentForm.getFormTable().refreshTable();			
			setState(State.DEFAULT);
			break;
		case UPDATE:
			setState(State.DEFAULT);			
			break;
		case ZOOM:
			System.out.println(parentForm.getFormTable().getSelectedRow());
			if(parentForm.getFormTable().getSelectedRow() >= 0){
				System.out.println(parentForm.getfName() + " Exporting data");
				MetaTable mt = ModelContentProvider.getTableByName(parentForm.getfName());
				HashMap<String, String> mapOfData = new HashMap<String,String>();
				for(MetaColumn mc : mt){
					System.out.println("Imported DATA : "+ mc.getName() + " + " + ((JTextField)parentForm.getDataPanel().getTextFieldByName(mc.getName())).getText());
					mapOfData.put(mc.getName(), ((JTextField)parentForm.getDataPanel().getTextFieldByName(mc.getName())).getText());
				}
				ActiveForms.deactivateForm(parentForm);
				ActiveForms.getActiveParent().importData(mapOfData);
				parentForm.dispose();
			}
			break;
		default:			
			String q = QueryManager.getUpdateQuery().prepareQueryForDB(parentForm.getFormTable().getName().replace(" ", "_"));			
			se = new StatementExecutioner(q, mdata, parentForm.getDataPanel());
			se.executeStatement();
			parentForm.getFormTable().refreshTable();
			break;
		}
	}
}
