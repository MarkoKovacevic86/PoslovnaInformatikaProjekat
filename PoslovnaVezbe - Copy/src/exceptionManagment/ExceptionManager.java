package exceptionManagment;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import gui.standard.form.UniqueKeyErrorDialog;


public class ExceptionManager {
	
	private static PrimaryKeyExceptionHandler keyEHandler;
	private final static String uniqueKeyExceptionMessage = "[SQLITE_CONSTRAINT]  Abort due to constraint violation (PRIMARY KEY must be unique)"; 
	
	private static InvalidNumberFormatExceptionHandler numberFormatHandler;
	private final static String numberFormatKeyExceptionMessage = "For input string: ";
	private final static String invalidInput = "invalid input";
	
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
		case numberFormatKeyExceptionMessage:
			numberFormatHandler = new InvalidNumberFormatExceptionHandler();
			break;
		case invalidInput:
			numberFormatHandler = new InvalidNumberFormatExceptionHandler();
			break;
		}
	}
}
