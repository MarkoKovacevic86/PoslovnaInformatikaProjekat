package gui.standard.form;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

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
import database.DBConnection;
import database.ModelContentProvider;
import database.MyFormatVerifier;
import database.MyInputVerifier;
import gui.main.form.MainFrame;
import gui.standard.form.StateManager.State;
import gui.table.EntityTable;
import net.miginfocom.swing.MigLayout;
import rs.mgifos.mosquito.model.MetaColumn;
import standardform.control.ActiveForms;
import standardform.control.MyWindowAdapter;

public class StandardForm extends MyWindowAdapter implements WindowListener{
	private static final long serialVersionUID = 1L;

	private JToolBar toolBar;
	private JButton btnAdd, btnCommit, btnDelete, btnFirst, btnLast, btnHelp, btnNext, btnNextForm, btnPickup,
			btnRefresh, btnRollback, btnSearch, btnPrevious;

	private EntityTable tblGrid = new EntityTable();
	private MyPanel bottomPanel;
	private MyPanel dataPanel;
	private StateManager smanager;
	private String fName;
	private String fCode;
	private StandardForm parentForm;
	private IFormButton referingButton;

	public StandardForm(String formName){
		fName = formName;
		fCode = formName.toUpperCase().replace(" ", "_");
		ActiveForms.activateForm(this);
		this.addWindowListener(this);
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
		smanager = new StateManager(this);

	}

	public StandardForm(String formName, IFormButton parentRefButton) {
		this(formName);
		setRefButton(parentRefButton);
		toolBar.setVisible(false);
		smanager.setState(State.ZOOM);
	}
	
	

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfCode() {
		return fCode;
	}

	public void setfCode(String fCode) {
		this.fCode = fCode;
	}

	public void setRefButton(IFormButton button) {
		referingButton = button;
	}
	

	public IFormButton getReferingButton() {
		return referingButton;
	}

	private void fieldInit() throws SQLException {
		DatabaseMetaData dbmd = DBConnection.getConnection().getMetaData();
		disableApropriateFields();
	}

	public StateManager getStateManager() {
		return smanager;
	}

	public void disableApropriateFields() {
		ArrayList<MetaColumn> listOfForeignCols = (ArrayList<MetaColumn>) ModelContentProvider.getForeignKeyCols(fCode);
		if (getFormTable().getSelectedRow() != -1) {
			for (MetaColumn mc : listOfForeignCols) {
				if (((JTextComponent) dataPanel.getTextFieldByName(mc.getName())) != null) {
					((JTextComponent) dataPanel.getTextFieldByName(mc.getName())).setEditable(false);
				}
			}
		}
	}

	public void zoomApropriateFields(){
		ArrayList<MetaColumn> listOfForeignCols = (ArrayList<MetaColumn>) ModelContentProvider.getForeignKeyCols(fCode);
		for (MetaColumn mc : listOfForeignCols) {
			dataPanel.getButtonByName(mc.getName()).setVisible(true);
			dataPanel.getButtonByName(mc.getName()).setButtonZoomTable(mc.getFkColParent().getParentTable().toString());
			dataPanel.getZoomButtonByName(mc.getName()).setVisible(true);
			dataPanel.getZoomButtonByName(mc.getName()).setButtonZoomTable(mc.getFkColParent().getParentTable().toString());
			((JTextComponent) dataPanel.getTextFieldByName(mc.getName())).setEditable(false);
			
		}
		System.out.println("testjedan");
		for(MetaColumn mc : ModelContentProvider.getTableByCode(fCode)){
			System.out.println("testdva");
			if(!mc.isMandatory()){
				System.out.println("testtri");
				((JTextComponent) dataPanel.getTextFieldByName(mc.getName())).
					setBorder(BorderFactory.createBevelBorder
							(BevelBorder.LOWERED,java.awt.Color.red, java.awt.Color.white));
				((JTextComponent) dataPanel.getTextFieldByName(mc.getName())).setText("*");
			}
		}
	}
	
	public boolean fieldValidation(){
		ArrayList<MetaColumn> cols = (ArrayList<MetaColumn>) ModelContentProvider.getTableColumns(fCode);
		for(MetaColumn mc : cols){
			MyInputVerifier miv = new MyInputVerifier(mc);
			if(!miv.verify((JComponent) dataPanel.getTextFieldByName(mc.getName()))){
				return true;
			}
		}return true;
	}

	public void initTable(String tableName) {
		add(tblGrid.initTable(this, tableName), "grow, wrap");
	}

	public EntityTable getFormTable() {
		return tblGrid;
	}

	public JToolBar getToolbar() {
		return toolBar;
	}

	private void initToolbar() {

		toolBar = new JToolBar();
		btnSearch = new JButton(new SearchAction(this));
		toolBar.add(btnSearch);

		btnRefresh = new JButton(new RefreshAction(this));
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

	public MyPanel getDataPanel() {
		return dataPanel;
	}

	private void initGui() {

		bottomPanel = new MyPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		dataPanel = new MyPanel();
		dataPanel.setCode(fCode);
		dataPanel.setLayout(new MigLayout("gapx 15px"));

		JPanel buttonsPanel = new JPanel();
		btnCommit = new JButton(new CommitAction(this));
		btnRollback = new JButton(new RollbackAction(this));

		for (int i = 0; i < tblGrid.getColumnCount(); i++) {
			JLabel tempLabel = new JLabel(tblGrid.getColumnName(i));
			dataPanel.add(tempLabel);
			JTextField tempField = null;
			MyButton button = new MyButton("+");
			ZoomTableButton ztb = new ZoomTableButton("import");
		//	button.setContainingForm(this);
			button.setParentPanel(dataPanel);
			button.setVisible(false);
			button.setName(tblGrid.getColumnName(i));
			//ztb.setContainingForm(this);
			ztb.setVisible(false);
			ztb.setName(tblGrid.getColumnName(i));
			tempField = new JTextField(10);
			tempField.setName(tblGrid.getColumnName(i));
			dataPanel.add(tempField);
			dataPanel.add(ztb);
			dataPanel.add(button, "wrap");
		}
		bottomPanel.add(dataPanel);

		buttonsPanel.setLayout(new MigLayout("wrap"));
		buttonsPanel.add(btnCommit);
		buttonsPanel.add(btnRollback);
		bottomPanel.add(buttonsPanel, "dock east");
		repaint();
		revalidate();
		add(bottomPanel, "grow, wrap");
	}

	public void setFormTable(EntityTable table) {
		tblGrid = table;
	}
	
	public void importData(HashMap<String,String> map){
		for(Object o : dataPanel.getTextField()){
			JTextField tField = (JTextField)o;
			if(map.containsKey(tField.getName())){
				tField.setText(map.get(tField.getName()));
			}
		}
	}

	public void updateDataPanel() {
		if (tblGrid.getSelectedRow() >= 0) {
			if (tblGrid.getModel().getRowCount() > 0) {
				for (int i = 0; i < tblGrid.getColumnCount(); i++) {
					((JTextField) dataPanel.getTextField().get(i)).setText(tblGrid.getTableRowColumnData(i).toString());
					disableApropriateFields();
				}
			} else {
				for (Object tf : dataPanel.getTextField()) {
					((JTextField) tf).setText("");
				}
			}
		}
	}

	public void clearDataPanelFields() {
		for (Object field : dataPanel.getTextField()) {
			((JTextField) field).setText("");
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Form "+ this.fName + " IS CLOSING");
		ActiveForms.getActiveFormes().remove(this);
	}

}
