package database;

import java.sql.ResultSetMetaData;

public interface QueryI {
	public String prepareQueryForDB(String tableName);
	public String parseQuery(String tableName);
	public String parseColumnQuery(String tableName,String columnName);
}
