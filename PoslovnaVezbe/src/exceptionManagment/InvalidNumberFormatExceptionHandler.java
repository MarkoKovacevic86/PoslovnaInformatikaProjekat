package exceptionManagment;

import gui.standard.form.IlegalNumberFormatExceptionDialog;

public class InvalidNumberFormatExceptionHandler {
	public InvalidNumberFormatExceptionHandler(){
		IlegalNumberFormatExceptionDialog infed = new IlegalNumberFormatExceptionDialog();
		infed.setupDialog();
	}
}
