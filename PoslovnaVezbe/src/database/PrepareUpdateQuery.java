package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PrepareUpdateQuery implements QueryI {
	
	private ResultSetMetaData mdata;
	private ResultSet rs;	
	
	@Override
	public String prepareQueryForDB(String tableName) {
		// TODO Auto-generated method stub
		return parseQuery(tableName);
	}

	@Override
	public String parseQuery(String tableName) {
		// TODO Auto-generated method stub
		getTableData(tableName);
		String updatePart =  "UPDATE " + tableName.replace(" ", "_") + " SET ";
		String setPart = "";
		String parameterPart =  "";
		try {
			for(int i = 1; i <= mdata.getColumnCount();i++){
				if(i == 1){
					setPart +=  " " +mdata.getColumnName(i) + " = ?";
				}else{
					setPart += " , " + mdata.getColumnName(i) + " = ?";
				}
			}parameterPart += " WHERE " + mdata.getColumnName(1) + " = ?";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatePart + setPart + parameterPart + ";";
	}
	
	private void getTableData(String tableName){
		mdata = QueryManager.getSearchQuery().getMetaData(tableName.replace(" ", "_"));
		rs = QueryManager.getSearchQuery().getResultSet();
		
	}

	@Override
	public String parseColumnQuery(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}	

}
