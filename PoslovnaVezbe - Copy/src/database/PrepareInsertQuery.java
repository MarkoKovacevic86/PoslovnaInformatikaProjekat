package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PrepareInsertQuery implements QueryI {
	
	private ResultSetMetaData mdata;
	private ResultSet rs;	
	
	@Override
	public String prepareQueryForDB(String tableName) {		
		// TODO Auto-generated method stub
		getTableData(tableName);
		
		return parseQuery(tableName);
	}

	@Override
	public String parseQuery(String tableName) {
		// TODO Auto-generated method stub
		String insertPart = "INSERT into "+ tableName.replace(" ", "_");
		String argumentPart = "(";
		String valuePart = " values (";
		try {
			for(int i = 1; i <= mdata.getColumnCount(); i++){
				if(i == mdata.getColumnCount()){
					argumentPart += mdata.getColumnName(i) + " ) ";
					valuePart += "?)";
				}else{
					argumentPart += mdata.getColumnName(i)+ ",";
					valuePart += "?, ";
				}				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(insertPart+ argumentPart +valuePart);
		return insertPart+ argumentPart +valuePart;/*argumentPart*/ 
	}
	
	private void getTableData(String tableName){
		mdata = QueryManager.getSearchQuery().getMetaData(tableName);
		rs = QueryManager.getSearchQuery().getResultSet();
		
	}

	@Override
	public String parseColumnQuery(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}	

}
