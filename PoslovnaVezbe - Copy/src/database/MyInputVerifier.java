package database;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import exceptionManagment.ExceptionManager;
import rs.mgifos.mosquito.model.MetaColumn;

public class MyInputVerifier extends InputVerifier {	
	
	MetaColumn mc;
	
	public MyInputVerifier(MetaColumn mc){
		this.mc = mc;
	};
	
	@Override
	public boolean verify(JComponent input) {
		String inputText = ((JTextField)input).getText();
		if(inputText.length() != 0){
			if((mc.getLength() == 0 && inputText.length() >= 0)|| inputText.length() <= mc.getLength()){
				if(typeSwitch(mc.getJClassName().split("\\.")[2], inputText))
					return true;
			}
		}
		return false;
	}
	
	private boolean typeSwitch(String type, String value){
		switch(type){
			case "BigInteger":
				System.out.println("It'a a BigInt");
				
				try{
					BigInteger bi  = new BigInteger(value);
					return true;
				}catch(NumberFormatException e){
					ExceptionManager.handleException("invalid input");
					return false;
				}
			case "BigDecimal":
				System.out.println("It'a a BigDec");
				try{
					BigDecimal bd = new BigDecimal(value);
					return true;
				}catch(NumberFormatException e){
					ExceptionManager.handleException("invalid input");
					return false;
				}
			case "String":
				System.out.println("It'a a String");
				try{
					String s = new String(value);
					return true;
				}catch(NumberFormatException e){
					ExceptionManager.handleException("invalid input");
					return false;
				}
			case "Date":
				System.out.println("It'a a Date");
				try{
					Date d = null;
					DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
					d = (Date) formatter.parse(value);
				}catch(java.text.ParseException e){
					ExceptionManager.handleException("invalid input");
					return false;
				}
				break;
			case "Boolean":
				System.out.println("It'a a Boolean");
				return true;
			default: 
				System.out.println("Nothing man");
				return false;
 		}return false;
	}
	
	

}
