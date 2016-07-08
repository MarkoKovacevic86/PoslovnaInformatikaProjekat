package gui.standard.form;

import java.awt.Component;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.ModelContentProvider;
import database.QueryManager;
import database.StatementExecutioner;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

public class VerifyDeletionDialog extends DialogAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatementExecutioner se;

	@Override
	public void setupDialog(Component parentFrame) {
		// TODO Auto-generated method stub	
		JOptionPane jop = new JOptionPane();		
		int choice = jop.showOptionDialog
		(parentFrame, "Da li zelite da izbrisete izabrani slog", "Brisanje",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null , 
				null, parentFrame);		
		if(choice == JOptionPane.OK_OPTION){
			MetaTable parentTable = ModelContentProvider.getTableByCode(((StandardForm)parentFrame).getfCode());
			ArrayList<MetaColumn> parentColumns = (ArrayList<MetaColumn>) ModelContentProvider.getPKColumns(parentTable);
			String dQuery = QueryManager.getDeleteQuery().prepareQueryForDB(((StandardForm)parentFrame).getFormTable().getName());
			ResultSetMetaData mdata = QueryManager.getSearchQuery().getMetaData(((StandardForm)parentFrame).getFormTable().getName());
			se = new StatementExecutioner(dQuery, mdata,((StandardForm)parentFrame));
			for(MetaColumn column : parentColumns){
				ArrayList<MetaTable> childTables = (ArrayList<MetaTable>) ModelContentProvider.getChildTables(column);
				for(MetaTable table : childTables){
					String query = QueryManager.getDeleteChildQuery().parseColumnQuery(table.getName(), column.getName());
					mdata = QueryManager.getSearchQuery().getMetaData(table.getName());
					se.executeStatement(query, mdata);
					//se = new StatementExecutioner(query, mdata).setTableName(table.getName());

					System.out.println("child tables: " + table.getName());
				}
			}
			se.executeStatement();
			((StandardForm)parentFrame).getFormTable().refreshTable();
		}else{
			return;
		}
	}
}
