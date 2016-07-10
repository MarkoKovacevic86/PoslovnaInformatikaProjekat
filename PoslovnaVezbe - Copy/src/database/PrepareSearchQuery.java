package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PrepareSearchQuery implements QueryI{
	
	ResultSet rs;
	
	@Override
	public String prepareQueryForDB(String tableName) {
		// TODO Auto-generated method stub	
		return parseQuery(tableName);
	}
	

	@Override
	public String parseQuery(String tableName) {
		// TODO Auto-generated method stub
		return "SELECT * FROM " + tableName.replace(" ",  "_");
	}

	
	public ResultSetMetaData getMetaData(String tableName) {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		ResultSetMetaData rsmd = null;
		try {
			PreparedStatement ppstmnt = conn.prepareStatement(prepareQueryForDB(tableName));
			rs = ppstmnt.executeQuery();
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsmd;
	}
	
	public ResultSet getResultSet(){
		return rs;
	}


	@Override
	public String parseColumnQuery(String tableName, String columnName) {
		return "SELECT * FROM " + tableName.replace(" ", "_") + " WHERE " + columnName.replace(" ", "_") + " = ";
	}
	
	public ResultSetMetaData getChildData(String query){
		Connection conn = DBConnection.getConnection();
		ResultSetMetaData rsmd = null;
		try{
			PreparedStatement ppstmnt = conn.prepareStatement(query);
			rs = ppstmnt.executeQuery();
			rsmd = rs.getMetaData();
		}catch(SQLException e){
			e.printStackTrace();
		}return rsmd;
	}
	
		

}
