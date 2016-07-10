package gui.main.form;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import actions.main.form.OmniAction;
import database.DBConnection;
import database.ModelContentProvider;
import rs.mgifos.mosquito.model.MetaTable;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static MainFrame instance;
	private JMenuBar menuBar;

	public MainFrame(){

		setSize(new Dimension(800,600));
		setLocationRelativeTo(null);
		setTitle("Poslovna");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUpMenu();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"Da li ste sigurni?", "Pitanje",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					/*
					 * Zatvori konekciju
					 */
					DBConnection.close();
					System.exit(0);
				}
			}
		});
		
		setJMenuBar(menuBar);

	}

	private void setUpMenu(){
		menuBar = new JMenuBar();

		MainMenu orgSemaMenu = new MainMenu("Organizaciona šema");
		orgSemaMenu.setMnemonic(KeyEvent.VK_O);
		/*MainMenuItem drzaveMI = new MainMenuItem(new OmniAction(ModelContentProvider.getTableByName("Drzava").getName()));
		orgSemaMenu.add(drzaveMI);	
		MainMenuItem mestoMI = new MainMenuItem(new OmniAction(ModelContentProvider.getTableByName("Naseljeno mesto").getName()));
		orgSemaMenu.add(mestoMI);*/
		for(MetaTable table : ModelContentProvider.getModel()){
			orgSemaMenu.add(new MainMenuItem(new OmniAction(table.getName())));
		}
		
		menuBar.add(orgSemaMenu);				
	}



	public static MainFrame getInstance(){
		if (instance==null)
			instance=new MainFrame();
		return instance;

	}

}