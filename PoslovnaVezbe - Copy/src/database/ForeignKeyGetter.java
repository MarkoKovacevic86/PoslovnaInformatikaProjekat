package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForeignKeyGetter {
	
	ResultSet rs;
	
	public ForeignKeyGetter(String tableName) throws SQLException{
		Connection conn = DBConnection.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();		
		rs = dbmd.getImportedKeys(null, null, tableName.replace(" ", "_"));
	}
	
	public ResultSet getForeignTable(String tableName) throws SQLException{
		Connection conn = DBConnection.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();
		return dbmd.getExportedKeys(null, null, tableName.replace(" ", "_"));
	}
	
	public ResultSet getForeignKeyResultSet(){
		return rs;
	}
}	
