package gui.main.form;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

public class MainMenuItem extends JMenuItem{
	private String code;
	public MainMenuItem(){
		super();
	}
	
	public MainMenuItem(String menuId){
		super();
		code = menuId;
	}
	
	public MainMenuItem(AbstractAction aa){
		super(aa);
	}
	
	public void setItemCode(String menuId){
		code = menuId;
	}
	
	
	
	public String getItemCode(){
		return code;
	}
	
	
}
