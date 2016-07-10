package gui.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ModelContentProvider;
import database.QueryManager;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

public class ZoomTable extends JTable {
	
	private DefaultTableModel dtm;
	private ResultSetMetaData mdata;
	private ResultSet rs;
	private String tbName;
	
	public ZoomTable(ResultSet rs, ResultSetMetaData rsmdt){
		this.rs = rs;
		this.mdata = rsmdt;
	}
	
	public void initTable(String dataQ, String tbName){
		dtm = new DefaultTableModel();
		mdata = QueryManager.getSearchQuery().getChildData(dataQ);
		rs = QueryManager.getSearchQuery().getResultSet();
		this.tbName = tbName;
		setName(tbName);
		setupCols();
		setupRows();
	}
	
	private void setupCols(){
		MetaTable mt = ModelContentProvider.getTableByCode(tbName.toUpperCase().replace(" ", "_"));
		for(MetaColumn mc : mt){
			dtm.addColumn(mc.getName());
		}
		setModel(dtm);
	}
	
	private void setupRows(){
		try{
			while(rs.next()){
				Vector<String> rowData = new Vector<String>();
				for(int i = 0; i < mdata.getColumnCount(); i++){
					if(rs.getObject(i+1) != null){
						rowData.addElement(rs.getObject(i+1).toString());
					}else{
						rowData.addElement("");
					}
				}dtm.addRow(rowData);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
