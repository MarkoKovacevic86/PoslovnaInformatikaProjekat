package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

public class PrepareColumnSearchQuary implements QueryI {

	private ResultSet rs;
	
	@Override
	public String prepareQueryForDB(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String parseQuery(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String parseColumnQuery(String tableName, String columnName) {
		return"SELECT "+columnName+ " FROM " + tableName.replace(" ", "_");
	}
	
	public ResultSetMetaData getMetaData(MetaTable table, MetaColumn column){
		Connection conn = DBConnection.getConnection();
		ResultSetMetaData rsmd = null;
		try{
			PreparedStatement ppstmnt = conn.prepareStatement(parseColumnQuery(table.getCode(), column.getCode()));
			rs = ppstmnt.executeQuery();
			rsmd = rs.getMetaData();
		}catch(SQLException e){
			System.out.println("******ERROR DURING COLUMN DATA ");
			e.printStackTrace();
		}
		return rsmd;
	}

	public ResultSet getResultSet() {
		return rs;
	}
	
	

}
