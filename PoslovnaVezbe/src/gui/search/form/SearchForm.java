package gui.search.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.ModelContentProvider;
import gui.table.EntityTable;
import net.miginfocom.swing.MigLayout;
import rs.mgifos.mosquito.model.MetaColumn;

public class SearchForm extends JPanel {
	
	private EntityTable entityTable;
	private  boolean extended = false;
	private JTextField standardSearchField;
	private JComboBox<String> comboBox ;
	
	public SearchForm(EntityTable entityTable){
		this.entityTable = entityTable;
		initialiseGui();
	}
	
	public Object getSearchField(){
		return standardSearchField;
	}
	
	
	
	private void initialiseGui(){
		setSize(300,110);
		setVisible(true);
		setLayout(new MigLayout());
		
		JLabel l = new JLabel();
		l.setText("Search: ");
		this.add(l);
		
		standardSearchField = new JTextField(10);
		this.add(standardSearchField);
		
		this.add(initialiseCombBox(), "wrap");
		
		JButton extendBtn = new JButton("Advanced");
		extendBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!extended){
					setSize(300,500);
					
					extended = true;
				}
				else{
					setSize(300,300);
					extended = false;
				}		
			}
		});
		this.add(extendBtn);
	}
	
	public String getStandardComboBoxType(){
		return (String) comboBox.getSelectedItem();
	}
	
	private JComboBox initialiseCombBox(){
		comboBox = new JComboBox<>();
		for(MetaColumn mc : ModelContentProvider.getTableByCode(entityTable.getName().toUpperCase().replace(" ", "_"))){
			comboBox.addItem(mc.getName());
		}
		return comboBox;
		
	}
}
