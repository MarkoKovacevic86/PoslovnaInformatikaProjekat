package app;

import javax.swing.UIManager;

import database.Login;
import database.ModelContentProvider;
import gui.main.form.MainFrame;
import rs.mgifos.mosquito.LoadingException;



public class Application {
	
	public static void main (String[] args) throws LoadingException{
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		UIManager.put("OptionPane.cancelButtonText", "Otkaži");		
		
				
		
		Login window = new Login();
		window.frame.setVisible(false);
		ModelContentProvider.setupModel();
		ModelContentProvider.readTableNames();
		MainFrame.getInstance().setVisible(true);
		
	}
	

}
