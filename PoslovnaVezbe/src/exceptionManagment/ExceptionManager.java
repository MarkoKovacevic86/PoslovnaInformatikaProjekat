package exceptionManagment;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import gui.standard.form.UniqueKeyErrorDialog;


public class ExceptionManager {
	
	private static PrimaryKeyExceptionHandler keyEHandler;
	private final static String uniqueKeyExceptionMessage = "[SQLITE_CONSTRAINT]  Abort due to constraint violation (PRIMARY KEY must be unique)"; 
	
	public ExceptionManager(){
		
	}
	
	
	public void getKeyExceptionHandler(){
		keyEHandler = new PrimaryKeyExceptionHandler();
		
	}
	
	public static void handleException(String message){
		switch(message){
		case uniqueKeyExceptionMessage:
			keyEHandler = new PrimaryKeyExceptionHandler();
			break;		
		}
	}
}
