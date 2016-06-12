package database;

import java.util.Properties;


import rs.mgifos.mosquito.IMetaLoader;
import rs.mgifos.mosquito.LoadingException;
import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;

public class ModelContentProvider {
	
	
	private static MetaModel model ;
	
	public ModelContentProvider() throws LoadingException{
						
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
	
	public static MetaTable getTableByName(String tableName){
		for(MetaTable table : model){
			if(table.getName().toLowerCase().equals(tableName.toLowerCase())){
				return table;
			}
		}return null;
	}
	
}
