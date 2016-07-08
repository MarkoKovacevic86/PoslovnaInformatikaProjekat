package database;

import java.sql.ResultSetMetaData;

public class PrepareDeleteChildQuery implements QueryI{

	private ResultSetMetaData mdata;
	
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
		String delete = "DELETE FROM " + tableName.replace(" ", "_") + " WHERE " + columnName.replace(" ", "_") + " = ?";		
		return delete;
	}

}
