package app;

import javax.swing.UIManager;

import database.Login;
import gui.main.form.MainFrame;

public class Application {
	
	public static void main (String[] args){
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		UIManager.put("OptionPane.cancelButtonText", "Otkaži");
		
		Login window = new Login();
		window.frame.setVisible(false);
		
		MainFrame.getInstance().setVisible(true);
	}

}
