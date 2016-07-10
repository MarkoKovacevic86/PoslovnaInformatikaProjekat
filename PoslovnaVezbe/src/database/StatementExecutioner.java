package database;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import exceptionManagment.ExceptionManager;
import gui.standard.form.MyPanel;
import gui.standard.form.StandardForm;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

public class StatementExecutioner {
	ResultSetMetaData metadata;
	DatabaseMetaData dbMetadata;
	PreparedStatement pstatement ;
	MyPanel dataPanel;
	String q;
	String tableName;
	
	public StatementExecutioner(String query,ResultSetMetaData mdata){
		this(query,mdata,null);
	}
	
	public StatementExecutioner(String query, ResultSetMetaData mdata, MyPanel dataPanel){		
		metadata = mdata;
		this.dataPanel = dataPanel;
		q = query;
	}
	
	public void executeStatement(String query, ResultSetMetaData metadata){
		try {
			System.out.println(query);		
			pstatement = DBConnection.getConnection().prepareStatement(query);
			if(!query.contains("DELETE")){
				for(int i = 1; i <= metadata.getColumnCount(); i++){
					setSwitch(metadata.getColumnTypeName(i),i);
				}if(query.contains("UPDATE")){
					setSwitch("int", 1);
				}
			}
			else{	
				for(int i = 0; i < getPrimaryCols().size();i++){
					setSwitch(metadata.getColumnTypeName(i +1),i + 1);
				}
			}
			pstatement.executeUpdate();
			
			DBConnection.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionManager.handleException(e.getMessage());
			
			
			//e.printStackTrace();
		}		
	}
	
	public void executeStatement(){
		try {
			System.out.println(q);		
			pstatement = DBConnection.getConnection().prepareStatement(q);
			if(!q.contains("DELETE")){
				for(int i = 1; i <= metadata.getColumnCount(); i++){
					setSwitch(metadata.getColumnTypeName(i),i);
				}if(q.contains("UPDATE")){
					setSwitch("int", 1);
				}
			}
			else{	
				for(int i = 0; i < getPrimaryCols().size();i++){
					setSwitch(metadata.getColumnTypeName(i +1),i + 1);
				}
			}
			pstatement.executeUpdate();
			
			DBConnection.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionManager.handleException(e.getMessage());
			
			
			//e.printStackTrace();
		}		
	}
	
	
	public StatementExecutioner setTableName(String name){
		tableName = name;
		return this;
	}
	
	ArrayList<String> primaryCols = new ArrayList<String>();
	
	public List<MetaColumn> getPrimaryCols(){
		MetaTable mt = ModelContentProvider.getTableByCode(dataPanel.getCode());
		return ModelContentProvider.getPKColumns(mt);
	}
	
	private void setSwitch(String type, int idx){
		try{
			if(!((JTextField) dataPanel.getTextField().get(idx - 1)).getText().equals("")){
					switch(type){
					case "int" 	:
						pstatement.setInt(idx, Integer.parseInt(((JTextComponent) dataPanel.getTextField().get(idx - 1)).getText()));				
						break;
					case "bigint" :
						pstatement.setInt(idx, Integer.parseInt(((JTextComponent) dataPanel.getTextField().get(idx - 1)).getText()));				
						break;
					case "varchar":									
						pstatement.setString(idx, ((JTextComponent) dataPanel.getTextField().get(idx - 1)).getText());
						break;
					default:
						break;
					}
			}else{						
				pstatement.setNull(idx, Types.INTEGER);			
			}
		}catch(SQLException e){
			
		}
	}
}
