package gui.search.form;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.ModelContentProvider;
import gui.standard.form.MyPanel;
import gui.table.EntityTable;
import net.miginfocom.swing.MigLayout;
import rs.mgifos.mosquito.model.MetaColumn;

public class SearchForm extends MyPanel {
	
	private EntityTable entityTable;
	private  boolean extended = false;
	private JTextField standardSearchField;
	private JComboBox<String> comboBox ;
	private MyPanel advancedPanel;
	private JTabbedPane tabPane;
	private ArrayList<JTextField> tFields;
	
	public SearchForm(EntityTable entityTable){
		this.entityTable = entityTable;
	}
	
	public Object getSearchField(){
		return standardSearchField;
	}
	
	public MyPanel getAdvancedPanel() {
		return advancedPanel;
	}

	public void setAdvancedPanel(MyPanel advancedPanel) {
		this.advancedPanel = advancedPanel;
	}

	private SearchForm initialiseAdvancedPanel(){
		for(MetaColumn mc : ModelContentProvider.getTableByCode(entityTable.getName().toUpperCase().replace(" ", "_"))){
			add(new JLabel(mc.getName()));
			JTextField tField = new JTextField(10);
			tField.setName(mc.getName());
			add(tField, "wrap");
		}
		return this;
	}
	
	public SearchForm initaliseAdvancedGui(){
		setSize(300,110);
		setVisible(true);
		setLayout(new MigLayout());
		return initialiseAdvancedPanel();
	}
	

	
	public SearchForm initialiseStandardGui(){
		setSize(300,110);
		setVisible(true);
		setLayout(new MigLayout());
		
		JLabel l = new JLabel();
		l.setText("Search: ");
		this.add(l);
		
		standardSearchField = new JTextField(10);
		this.add(standardSearchField);
	
		this.add(initialiseCombBox(), "wrap");
		
		return this;
	}
	
	public String getStandardComboBoxType(){
		return (String) comboBox.getSelectedItem();
	}
	
	private JComboBox initialiseCombBox(){
		comboBox = new JComboBox<>();
		for(MetaColumn mc : ModelContentProvider.getTableByCode(entityTable.getName().toUpperCase().replace(" ", "_"))){
			comboBox.addItem(mc.getName());
		}
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				standardSearchField.setText((String) comboBox.getSelectedItem());
			}
		});
		return comboBox;
		
	}
	
	
}
