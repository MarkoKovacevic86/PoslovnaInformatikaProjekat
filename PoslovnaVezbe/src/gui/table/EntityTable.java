package gui.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import database.ModelContentProvider;
import database.PrepareSearchQuery;
import database.QueryManager;
import database.SqliteConnection;
import gui.standard.form.StandardForm;
import rs.mgifos.mosquito.model.MetaTable;

public class EntityTable extends JTable{
	
	private DefaultTableModel dtm;
	private StandardForm standardForm;
	private String tbName;
	private ResultSetMetaData mdata;
	ResultSet rs;
	
	
	public EntityTable(){		
	}
	
	@Override
	public String getName() {
		return tbName;
	};
	
	public JScrollPane initTable(StandardForm sf, String tableName){				
		dtm = new DefaultTableModel();
		standardForm = sf;
		tbName = tableName;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()){
					return;
				}
				standardForm.updateDataPanel();
			}
		});	
		//mdata = QueryManager.getSearchQuery().getMetaData(tbName);
		rs = QueryManager.getSearchQuery().getResultSet();				
		setupCols();
		//setupRows();
		this.setModel(dtm);
		return new JScrollPane(this);
	}
	
	public void refreshTable(){
		mdata = QueryManager.getSearchQuery().getMetaData(tbName);
		rs = QueryManager.getSearchQuery().getResultSet();			
		dtm.setColumnCount(0);
		dtm.setRowCount(0);
		setupCols();
		//setupRows();
		setModel(dtm);
		standardForm.setFormTable(this);
		
	}
	
	private void setupCols(){
			MetaTable mt = ModelContentProvider.getTableByName(tbName);			
			//mdata.getColumnCount();
			for(Object column : mt.cColumns()){
				TableColumn tc = new TableColumn();
				dtm.addColumn(column.toString());
				System.out.println(column.toString());
			}
	}
	
	private void setupRows(){
		try {
			while(rs.next()){			
				Vector<String> rowData = new Vector<>();
				for(int i = 1; i <= mdata.getColumnCount(); i++){
					if(rs.getObject(i) != null)
						rowData.addElement(rs.getObject(i).toString());
					else
						rowData.addElement("");
				}
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Object[] getTableRowData(){
		Object[] cols = new Object[this.getColumnCount()];
		if(this.getSelectedRow() != -1){
			for(int i = 0; i < this.getColumnCount(); i++){
				cols[i] = this.getValueAt(this.getSelectedRow(), i);
			}
		}
		return cols;
	}
	
	public void getDBData(){
		Connection conn = SqliteConnection.getConnection();		
	}
	
	public ResultSet getPrimaryKeyColumn() throws SQLException{
		Connection conn = SqliteConnection.getConnection();
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getPrimaryKeys(null, null, getName());
		return rs;
	}
	
	public void getPrimaryColumns() throws SQLException{
		ResultSet rs = getPrimaryKeyColumn();
		while(rs.next()){
			String columnName = rs.getString("COLUMN_NAME");	
			getColumnModel().getColumnIndex(columnName);
		}
	}
	
	public Object getColumnByName(String name){
		for(int i = 1;  i <= getColumnModel().getColumnCount();i++){
			if(getColumnName(i).equals(name))
				return getColumnModel().getColumn(i);
		}return null;
	}
	
	public Object getTableRowColumnData(int idx){		
		if(this.getSelectedRow() != -1){			
			return this.getValueAt(this.getSelectedRow(), idx);
		}
		return null;
	}
	
	
	public void deleteRowFromTable(){
		if(this.getSelectedRow() < 0){
			return;
		}
		int idx = getSelectedRow();
		System.out.println(dtm.getRowCount());
		if(dtm.getRowCount() > 1){
			if(idx == 0){
				setRowSelectionInterval(idx + 1 , idx + 1);			
			}else{		
				setRowSelectionInterval(idx - 1 , idx - 1);			
			}
			dtm.removeRow(idx);
		}else		
			dtm.setRowCount(0);
		
	}
} 
