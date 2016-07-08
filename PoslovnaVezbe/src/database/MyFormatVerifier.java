package database;

import java.math.BigInteger;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import rs.mgifos.mosquito.model.MetaColumn;

public class MyFormatVerifier extends InputVerifier {

	private MetaColumn mc;
	
	public MyFormatVerifier(MetaColumn mc) {
		this.mc = mc;
	}
	
	@Override
	public boolean verify(JComponent input) {
		JTextField jtf = (JTextField)input;
		typeSwitch(mc.getJClassName().split("\\.")[2],jtf.getText());
		return false;
	}
	
	private void typeSwitch(String type, String value){
		switch(type){
			case "BigInteger":
				System.out.println("It'a a BigInt");
				
				try{
					BigInteger bi  = new BigInteger(value);
					System.out.println(bi + "<<<<<<<<");
				}catch(ClassCastException e){
					System.out.println("dva");
				}
				break;
			case "BigDecimal":
				System.out.println("It'a a BigDec");
				break;
			case "String":
				BigInteger bi = new BigInteger(value);
				System.out.println(bi + ">>>>>>>");
				System.out.println("It'a a String");
				break;
			case "Date":
				System.out.println("It'a a Date");
				break;
			case "Boolean":
				System.out.println("It'a a Boolean");
				break;
			default:
				System.out.println("Nothing man");
				break;
		}
	}

}
