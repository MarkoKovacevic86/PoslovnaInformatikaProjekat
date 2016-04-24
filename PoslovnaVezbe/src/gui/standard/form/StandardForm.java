package gui.standard.form;

import java.awt.Dimension;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import actions.standard.form.AddAction;
import actions.standard.form.CommitAction;
import actions.standard.form.DeleteAction;
import actions.standard.form.FirstAction;
import actions.standard.form.HelpAction;
import actions.standard.form.LastAction;
import actions.standard.form.NextAction;
import actions.standard.form.NextFormAction;
import actions.standard.form.PickupAction;
import actions.standard.form.PreviousAction;
import actions.standard.form.RefreshAction;
import actions.standard.form.RollbackAction;
import actions.standard.form.SearchAction;
import database.SqliteConnection;
import gui.main.form.MainFrame;
import gui.table.EntityTable;
import net.miginfocom.swing.MigLayout;

public class StandardForm extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JToolBar toolBar;
	private JButton btnAdd, btnCommit, btnDelete, btnFirst, btnLast, btnHelp, btnNext, btnNextForm,
	btnPickup, btnRefresh, btnRollback, btnSearch, btnPrevious;
	private JTextField tfSifra = new JTextField(5);
	private JTextField tfNaziv = new JTextField(20);
	private EntityTable tblGrid = new EntityTable(); 
	private MyPanel bottomPanel;
	private MyPanel dataPanel;
	private StateManager smanager;
	private String fName;
	private StandardForm parentForm;
	private MyButton referingButton;

	public StandardForm(String formName){
		
		setLayout(new MigLayout("fill"));

		setSize(new Dimension(800, 600));
		setTitle("Standard");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		
		
		initToolbar();
		initTable(formName);
		initGui();		
		try {
			fieldInit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fName = formName;
		smanager =  new StateManager(this);		
		
	}
	
	public void setRefButton(MyButton button){
		referingButton = button;
	}
	
	private void fieldInit() throws SQLException{
		DatabaseMetaData dbmd = SqliteConnection.getConnection().getMetaData();
		ResultSet rs = dbmd.getPrimaryKeys(null, null, getFormTable().getName().replace(" ", "_"));
		disableApropriateFields(rs);
		rs = dbmd.getImportedKeys(null, null, getFormTable().getName().replace(" ", "_"));
		disableApropriateFields(rs);		
	}
	
	public StateManager getStateManager(){
		return smanager;
	}
	
	public void disableApropriateFields(ResultSet rs) throws SQLException{		
		while(rs.next()){			
			String fkColumName;
			try{
				fkColumName = rs.getString("COLUMN_NAME");
			}catch(SQLException e){
				fkColumName = rs.getString("FKCOLUMN_NAME");
			}
			if(dataPanel.getTextFieldByName(fkColumName) != null){
				System.out.println("upo");
				dataPanel.getTextFieldByName(fkColumName).setEditable(false);;
			}
		}
	}
	
	public void zoomApropriateFields(ResultSet rs) throws SQLException{
		while(rs.next()){		
			String fkColumName = rs.getString("FKCOLUMN_NAME");		
			String fkTableName = rs.getString("PKTABLE_NAME");
			if(dataPanel.getTextFieldByName(fkColumName) != null){			
				dataPanel.getButtonByName(fkColumName).setVisible(true);
				dataPanel.getButtonByName(fkColumName).setButtonZoomTable(fkTableName);
				
			}
		}
	}
	
	public void initTable(String tableName){		
		add(tblGrid.initTable(this, tableName), "grow, wrap");				
	}
	
	public EntityTable getFormTable(){
		return tblGrid;
	}
	
	public void setSelectedData(Object[] data){
		
	}
	
	public JToolBar getToolbar(){
		return toolBar;
	}
	
	private void initToolbar(){

		toolBar = new JToolBar();
		btnSearch = new JButton(new SearchAction(this));
		toolBar.add(btnSearch);


		btnRefresh = new JButton(new RefreshAction());
		toolBar.add(btnRefresh);

		btnPickup = new JButton(new PickupAction(this));
		toolBar.add(btnPickup);


		btnHelp = new JButton(new HelpAction());
		toolBar.add(btnHelp);

		toolBar.addSeparator();

		btnFirst = new JButton(new FirstAction(this));
		toolBar.add(btnFirst);

		btnPrevious = new JButton(new PreviousAction(this));
		toolBar.add(btnPrevious);

		btnNext = new JButton(new NextAction(this));
		toolBar.add(btnNext);

		btnLast = new JButton(new LastAction(this));
		toolBar.add(btnLast);

		toolBar.addSeparator();


		btnAdd = new JButton(new AddAction(this));
		toolBar.add(btnAdd);

		btnDelete = new JButton(new DeleteAction(this));
		toolBar.add(btnDelete);

		toolBar.addSeparator();

		btnNextForm = new JButton(new NextFormAction(this));
		toolBar.add(btnNextForm);

		add(toolBar, "dock north");
	}
	
	public MyPanel getDataPanel(){
		return dataPanel;
	}
	
	
	
	private void initGui(){
		
		bottomPanel = new MyPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		dataPanel = new MyPanel();
		dataPanel.setLayout(new MigLayout("gapx 15px"));

		JPanel buttonsPanel = new JPanel();
		btnCommit = new JButton(new CommitAction(this));
		btnRollback = new JButton(new RollbackAction(this));

		for(int i = 0; i < tblGrid.getColumnCount();i++){
			JLabel tempLabel = new JLabel(tblGrid.getColumnName(i));			
			dataPanel.add(tempLabel);
			JTextField tempField = null;
			MyButton button = new MyButton("+");
			button.setVisible(false);
			button.setName(tblGrid.getColumnName(i));
			System.out.println(button.getName());
			tempField = new JTextField(10);			
			tempField.setName(tblGrid.getColumnName(i));
			dataPanel.add(tempField);
			dataPanel.add(button,"wrap");
		}
		bottomPanel.add(dataPanel);

		buttonsPanel.setLayout(new MigLayout("wrap"));
		buttonsPanel.add(btnCommit);
		buttonsPanel.add(btnRollback);
		bottomPanel.add(buttonsPanel,"dock east");
		repaint();
		revalidate();
		add(bottomPanel, "grow, wrap");
	}
	
	public void setFormTable(EntityTable table){
		tblGrid = table;
	}
	
	public void updateDataPanel(){
		if(tblGrid.getSelectedRow() > 0){
			if(tblGrid.getModel().getRowCount() > 0 ){
				for(int i = 0 ; i < tblGrid.getColumnCount(); i++){
				//System.out.println(tblGrid.getTableRowColumnData(i));
					dataPanel.getTextField().get(i).setText(tblGrid.getTableRowColumnData(i).toString());
				}
			}else{
				for(int i = 0 ; i < tblGrid.getColumnCount(); i++){
					dataPanel.getTextField().get(i).setText("");
				}
			}
		}
	}
	
	public void clearDataPanelFields(){
		for(JTextField field : dataPanel.getTextField()){
			field.setText("");
		}
	}


}
