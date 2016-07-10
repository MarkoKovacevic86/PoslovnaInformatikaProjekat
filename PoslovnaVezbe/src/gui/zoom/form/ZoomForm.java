package gui.zoom.form;

import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import database.ModelContentProvider;
import database.QueryManager;
import gui.standard.form.StandardForm;
import gui.table.EntityTable;
import gui.table.ZoomTable;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;
import standardform.control.MyWindowAdapter;

public class ZoomForm extends MyWindowAdapter implements WindowListener {
	
	private StandardForm parentForm;
	private JTabbedPane tp;
	
	
	public ZoomForm(StandardForm parentForm){
		this.setSize(500,500);
		setLocationRelativeTo(null);
		this.parentForm = parentForm;
		initialiseData();
	}
	
	private void initialiseData(){
		MetaTable mt = ModelContentProvider.getTableByCode(parentForm.getfCode());
		ArrayList<MetaColumn> parentPKs = (ArrayList<MetaColumn>) ModelContentProvider.getPKColumns(mt);
		tp = new JTabbedPane();
		for(int i = 0; i < parentPKs.size(); i++){
			for(MetaTable childTable : ModelContentProvider.getChildTables(parentPKs.get(i))){
				if((!childTable.getName().equals(parentForm.getfName()))&& !parentPKs.get(i).isPartOfFK()){
					String query = QueryManager.getSearchQuery().parseColumnQuery(childTable.getName(), parentPKs.get(i).getName());
					Object data = parentForm.getFormTable().getTableRowColumnData(i);
					query += data.toString();
					ResultSetMetaData rsmd = QueryManager.getSearchQuery().getChildData(query);
					ResultSet rs = QueryManager.getSearchQuery().getResultSet();
					try {
						init(rs,query,childTable.getName(), rsmd);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}this.add(tp);
		}
	}
	
	private void init(ResultSet rs,String dataQ, String tbName, ResultSetMetaData rsmd) throws SQLException{
		ZoomTable zt = new ZoomTable(rs,rsmd);
		if(parentForm.getFormTable().getSelectedRow() != -1){
			zt.initTable(dataQ, tbName);
			tp.add(zt.getName(), new JScrollPane(zt));
		}
		
	}
	
	
}
