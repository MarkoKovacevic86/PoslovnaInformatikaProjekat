package gui.standard.form;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.print.attribute.Size2DSyntax;

import database.QueryManager;
import database.StatementExecutioner;

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
			String query = QueryManager.getInsertQuery().prepareQueryForDB(parentForm.getFormTable().getName());
			se = new StatementExecutioner(query, mdata, parentForm);			
			parentForm.getFormTable().refreshTable();			
			setState(State.DEFAULT);
			break;
		case UPDATE:
			setState(State.DEFAULT);			
			break;
		case ZOOM:
			System.out.println(parentForm.getFormTable().getSelectedRow());
			if(parentForm.getFormTable().getSelectedRow() >= 0){
				//parentForm.getFormTable().getPrimaryColumns();
				/*ResultSet rs = parentForm.getFormTable().getPrimaryKeyColumn();
				while(rs.next())
					System.out.println(rs.getString("COLUMN_NAME") + " Ovde");*/
				parentForm.dispose();
			}
			break;
		default:			
			String q = QueryManager.getUpdateQuery().prepareQueryForDB(parentForm.getFormTable().getName().replace(" ", "_"));
			System.out.println("11111");
			se = new StatementExecutioner(q, mdata, parentForm);
			parentForm.getFormTable().refreshTable();
			break;
		}
	}
}
