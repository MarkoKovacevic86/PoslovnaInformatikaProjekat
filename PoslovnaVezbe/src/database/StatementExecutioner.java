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

import javax.swing.SwingUtilities;

import exceptionManagment.ExceptionManager;
import gui.standard.form.StandardForm;

public class StatementExecutioner {
	ResultSetMetaData metadata;
	DatabaseMetaData dbMetadata;
	PreparedStatement pstatement ;
	StandardForm pForm;
	String q;
	
	public StatementExecutioner(String query,ResultSetMetaData mdata){
		this(query,mdata,null);
	}
	
	public StatementExecutioner(String query, ResultSetMetaData mdata, StandardForm parentForm){		
		metadata = mdata;
		pForm = parentForm;
		q = query;
		try {
			System.out.println(query);		
			pstatement = SqliteConnection.getConnection().prepareStatement(query);
			if(!query.contains("DELETE")){
				for(int i = 1; i <= metadata.getColumnCount(); i++){				
					setSwitch(metadata.getColumnType(i),i);
				}if(query.contains("UPDATE")){
					setSwitch(Types.INTEGER, 1);
				}
			}
			else{	
				prepareMetaData();
				for(int i = 0; i < primaryCols.size();i++){				
					setSwitch(metadata.getColumnType(i +1),i + 1);
				}
			}
			System.out.println("doso sam");
			pstatement.executeUpdate();
			
			SqliteConnection.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionManager.handleException(e.getMessage());
			
			
			//e.printStackTrace();
		}		
	}
	
	ArrayList<String> primaryCols = new ArrayList<String>();
	
	public void prepareMetaData(){
		Connection conn = SqliteConnection.getConnection();
		primaryCols.clear();		
		try {
			dbMetadata = conn.getMetaData();
			ResultSet rs = dbMetadata.getPrimaryKeys(null, null, pForm.getFormTable().getName().replace(" ", "_"));
			while(rs.next()){				
				primaryCols.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setSwitch(int type, int idx) throws SQLException{
		System.out.println("test");
		if(!pForm.getDataPanel().getTextField().get(idx - 1).getText().equals("")){
	
			switch(type){
			case 4:				
				System.out.println(pForm.getDataPanel().getTextField().get(idx - 1).getText());
				pstatement.setInt(idx, Integer.parseInt(pForm.getDataPanel().getTextField().get(idx - 1).getText()));				
				break;
			case 12:									
				System.out.println(pForm.getDataPanel().getTextField().get(idx - 1).getText());
				pstatement.setString(idx, pForm.getDataPanel().getTextField().get(idx - 1).getText());
				break;
			default:
				break;
			}
		}else{						
			pstatement.setNull(idx, Types.INTEGER);			
		}
	}
}
