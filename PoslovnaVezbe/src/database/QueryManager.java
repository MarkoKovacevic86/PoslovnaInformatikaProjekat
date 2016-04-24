package database;

public class QueryManager {
	private static PrepareSearchQuery psq = new PrepareSearchQuery();
	private static PrepareInsertQuery piq = new PrepareInsertQuery();
	private static PrepareDeleteQuery pdq = new PrepareDeleteQuery();
	private static PrepareUpdateQuery puq = new PrepareUpdateQuery();
	
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
	
	
}
