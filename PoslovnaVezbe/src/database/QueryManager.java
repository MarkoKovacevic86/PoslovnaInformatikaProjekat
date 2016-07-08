package database;

public class QueryManager {
	private static PrepareSearchQuery psq = new PrepareSearchQuery();
	private static PrepareInsertQuery piq = new PrepareInsertQuery();
	private static PrepareDeleteQuery pdq = new PrepareDeleteQuery();
	private static PrepareColumnSearchQuary pcq = new PrepareColumnSearchQuary();
	private static PrepareUpdateQuery puq = new PrepareUpdateQuery();
	private static PrepareDeleteChildQuery pdcq = new PrepareDeleteChildQuery();
	
	public static PrepareSearchQuery getSearchQuery(){
		return psq;
	}

	public static PrepareInsertQuery getInsertQuery() {
		return piq;
	}
	
	public static PrepareDeleteQuery getDeleteQuery(){
		return pdq;
	}
	
	public static PrepareUpdateQuery getUpdateQuery(){
		return puq;
	}

	public static PrepareColumnSearchQuary getColumnQuery() {
		return pcq;
	}
	
	public static PrepareDeleteChildQuery getDeleteChildQuery(){
		return pdcq;
	}
	 
	
}
