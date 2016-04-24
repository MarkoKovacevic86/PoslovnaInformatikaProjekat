package exceptionManagment;

import gui.standard.form.UniqueKeyErrorDialog;

public class PrimaryKeyExceptionHandler {
	public PrimaryKeyExceptionHandler(){
		UniqueKeyErrorDialog uked = new UniqueKeyErrorDialog();
		uked.setupDialog();
	}
}
