package gui.standard.form;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	
	String pCode;
	
	public MyPanel(){
		super();
	}
	
	public void setCode(String code){
		pCode = code;
	}
	
	public String getCode(){
		return pCode;
	}
	
	public void enableTextFields(Boolean bool){
		for(Object field : getTextField()){
			if(bool == true)
				((JTextField)field).setEditable(true);
			else
				((JTextField)field).setEditable(false);
		}
	}
	
	public void buttonVisibility(Boolean bool){
		for(JButton button : getButtons()){
		button.setVisible(bool);
		}
	}
	
	public ArrayList<Object> getTextField(){
		ArrayList<Object> textFields = new ArrayList<Object>();
		for(Component component : this.getComponents()){
			if(component.getClass() == JTextField.class){
				textFields.add((JTextField) component);
			}
		}
		return textFields;
	}
	
	public ArrayList<MyButton> getButtons(){
		ArrayList<MyButton> buttons = new ArrayList<MyButton>();
		for(Component component : this.getComponents()){
			if(component.getClass() == MyButton.class){
				buttons.add((MyButton) component);
			}
		}return buttons;
	}
	
	public ArrayList<ZoomTableButton> getZoomButtons(){
		ArrayList<ZoomTableButton> buttons = new ArrayList<ZoomTableButton>();
		for(Component component : this.getComponents()){
			if(component.getClass() == ZoomTableButton.class){
				buttons.add((ZoomTableButton) component);
			}
		}return buttons;
	}
	
	public Object getTextFieldByName(String name){
		for(Object tField : getTextField()){
			if(((JTextField)tField).getName().toUpperCase().equals(name.toUpperCase())){
				return tField;
			}
		}return null;
	}
	
	public MyButton getButtonByName(String name){
		for(MyButton button : getButtons()){			
			if(button.getName().toUpperCase().equals(name.toUpperCase()))
				//System.out.println("test");
				return button;
		}return null;
	}
	
	public ZoomTableButton getZoomButtonByName(String name){
		for(ZoomTableButton button : getZoomButtons()){
			if(button.getName().toUpperCase().equals(name.toUpperCase())){
				return button;
			}
		}return null;
	}
}
