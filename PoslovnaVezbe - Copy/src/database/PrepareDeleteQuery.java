package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrepareDeleteQuery implements QueryI {

	private ResultSetMetaData mdata;
	private ResultSet rs;
	private String tname;
	
	@Override
	public String prepareQueryForDB(String tableName) {
		// TODO Auto-generated method stub
		return parseQuery(tableName);
	}

	@Override
	public String parseQuery(String tableName) {
		// TODO Auto-generated method stub
		tname = tableName;
		String delete = "DELETE FROM " + tableName.replace(" ", "_") + " WHERE ";		
		String arguments = "";
		mdata = QueryManager.getSearchQuery().getMetaData(tableName);
		prepareMetaData();
		for(int i = 0; i < primaryCols.size(); i++){
			if(i == 0){
				arguments += primaryCols.get(i) + " = ? ";
			}else{
				arguments += "AND " + primaryCols.get(i) + " = ? ";
			}				
		}
		return delete + arguments;
	}
	
	private ArrayList<String> primaryCols = new ArrayList<String>();
	
	public void prepareMetaData(){
		Connection conn = DBConnection.getConnection();
		DatabaseMetaData md;
		primaryCols.clear();
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getPrimaryKeys(null, null, tname.replace(" ", "_"));			
			while(rs.next()){
				
				primaryCols.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	@Override
	public String parseColumnQuery(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

}
