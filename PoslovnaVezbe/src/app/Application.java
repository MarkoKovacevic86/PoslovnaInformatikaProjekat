package app;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import javax.swing.UIManager;

import database.Login;
import database.ModelContentProvider;
import database.TestDataParser;
import gui.main.form.MainFrame;
import rs.mgifos.mosquito.IMetaLoader;
import rs.mgifos.mosquito.LoadingException;
import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;



public class Application {
	
	public static void main (String[] args) throws LoadingException{
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		UIManager.put("OptionPane.cancelButtonText", "Otkaži");		
		
				
		
		Login window = new Login();
		new TestDataParser();
		window.frame.setVisible(false);
		ModelContentProvider.setupModel();
		ModelContentProvider.readTableNames();;
		
		MainFrame.getInstance().setVisible(true);
	}

}
