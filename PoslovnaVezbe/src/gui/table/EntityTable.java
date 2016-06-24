package gui.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import database.DBConnection;
import database.ModelContentProvider;
import database.QueryManager;
import gui.standard.form.StandardForm;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

public class EntityTable extends JTable{
	
	private DefaultTableModel dtm;
	private StandardForm standardForm;
	private String tbName;
	private String tbCode;
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
		tbCode = tableName.toUpperCase().replace(" ", "_");
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
		mdata = QueryManager.getSearchQuery().getMetaData(tbName);
		rs = QueryManager.getSearchQuery().getResultSet();				
		setupCols();
		setupRows();
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
			MetaTable mt = ModelContentProvider.getTableByCode(tbCode);
			for(Object column : mt.cColumns()){
				TableColumn tc = new TableColumn();
				dtm.addColumn(column.toString());
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
		Connection conn = DBConnection.getConnection();		
	}

	public Object getColumnByName(String name){
		MetaTable mt = ModelContentProvider.getTableByCode(tbCode);
		for(Object column : mt.cColumns()){
			if(((MetaColumn)column).getName().equals(name)){
				return column;
			}
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

	public String getTbCode() {
		return tbCode;
	}

	public void setTbCode(String tbCode) {
		this.tbCode = tbCode;
	}
	
	
} 
