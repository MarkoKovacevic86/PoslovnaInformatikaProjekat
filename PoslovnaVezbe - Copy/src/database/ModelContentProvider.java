package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import rs.mgifos.mosquito.IMetaLoader;
import rs.mgifos.mosquito.LoadingException;
import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;

public class ModelContentProvider {
	
	
	private static MetaModel model ;
	private static ResultSetMetaData mtdt;
	private static ResultSet rs;
	
	public ModelContentProvider() throws LoadingException{
						
	}
	
	public static void readColumnTypes(){
		for(MetaTable table : model){
			for(MetaColumn column : table){
				System.out.println(column.getJClassName());
			}
		}
	}
	
	public static List<MetaColumn> getPKColumns(MetaTable mt){
		ArrayList<MetaColumn> pkCols = new ArrayList<MetaColumn>();
		for(MetaColumn column : mt){
			if(column.isPartOfPK())
				pkCols.add(column);
		}return pkCols;
	}
	
	public static List<MetaTable> getChildTables(MetaColumn parentColumn){
		ArrayList<MetaTable> tables = new ArrayList<MetaTable>();
		for(MetaTable table : model){
			for(MetaColumn mc : table){
				if(mc.isPartOfFK()){
					if(mc.getName().equals(parentColumn.getName())){
						tables.add(table);
					}
				}
			}
		}return tables;
	}
	
	public static void setupModel() throws LoadingException{
		IMetaLoader metaLoader = new PDMetaLoader();

		Properties properties = new Properties();
		properties.put(PDMetaLoader.FILENAME, "src/resources/magacinsko_poslovanje.pdm");

		model = metaLoader.getMetaModel(properties);
	}
	
	public static void readTablesCode(){
		for (MetaTable table : model) {
		    System.out.println(table.getCode());		    
		}
	}
	
	public static void readTableNames(){
		for (MetaTable table : model) {
		    System.out.println(table.getName());		    
		}
	}
	
	public static MetaModel getModel(){
		return model;
	}
	
	public static MetaColumn getColumnByName(String tCode, String cName){
		for(MetaColumn column : getTableByCode(tCode)){
			if(column.getName().equals(cName))
				return column;
		};
		return null;
	}
	
	public static List<MetaColumn> getForeignKeyCols(String tableCode){
		MetaTable mt = getTableByCode(tableCode);
		Iterator i = mt.iterator();
		ArrayList<MetaColumn> listOfForeignKeys = new ArrayList<MetaColumn>();
		while(i.hasNext()){
			MetaColumn mc = (MetaColumn) i.next();
			if(mc.isPartOfFK()){
				listOfForeignKeys.add(mc);
			}
		}
		return listOfForeignKeys;
		
	}
	
	public static List<MetaColumn> getTableColumns(String tableCode){
		MetaTable mt = getTableByCode(tableCode);
		Iterator i = mt.iterator();
		ArrayList<MetaColumn> cols = new ArrayList<MetaColumn>();
		while(i.hasNext()){
			MetaColumn mc = (MetaColumn)i.next();
			cols.add(mc);
		}return cols;
	}
	
	public static MetaTable getTableByName(String tableName){
		for(MetaTable table : model){
			if(table.getName().toLowerCase().equals(tableName.toLowerCase())){
				return table;
			}
		}return null;
	}
	
	public static MetaTable getTableByCode(String tableCode){
		for(MetaTable table : model){
			if(table.getCode().equals(tableCode)){
				return table;
			}
		}return null;
	}
	
	public static ArrayList<String> getColumnValues(MetaTable mt, MetaColumn mc){
		mtdt = QueryManager.getColumnQuery().getMetaData(mt, mc);
		rs = QueryManager.getColumnQuery().getResultSet(); 
		ArrayList<String> values = new ArrayList<String>();
		try {
			while(rs.next()){
				values.add(rs.getString(mc.getName().replace(" ", "_")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("****ERROR in modelcontetprovider for column data");
			e.printStackTrace();
		}
		return values;
		
	}
	
	private HashMap<String, String> tempDataMap;
	
	public void setTempTransferData(HashMap<String,String> map){
		tempDataMap = map;
		
	}
}
