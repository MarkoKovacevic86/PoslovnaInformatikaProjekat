package gui.search.form;

import javax.swing.JTabbedPane;

import gui.table.EntityTable;

public class MyTabbedPane extends JTabbedPane {
	
	private SearchForm standardSearch;
	private SearchForm advancedSearch;
	
	public MyTabbedPane(EntityTable entityTable){
		standardSearch = new SearchForm(entityTable);
		this.add(standardSearch.initialiseStandardGui(), "Standard search");
		
		advancedSearch = new SearchForm(entityTable);
		this.add(advancedSearch.initaliseAdvancedGui(), "Advanced search");
	}
	
	public SearchForm getActiveTab(){
		if(this.getSelectedIndex() == 0){
			return standardSearch;
		}else
			return advancedSearch;
	}
	
	
	
	
}
