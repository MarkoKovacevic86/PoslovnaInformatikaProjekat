package standardform.control;

import java.util.ArrayList;

import gui.standard.form.StandardForm;

public class ActiveForms {
	
	private static ArrayList<StandardForm> activeFormes =  new ArrayList<StandardForm>(); 
	
	public ActiveForms(){};
	 
	public static void activateForm(StandardForm sf){
		activeFormes.add(sf);
		
	}
	
	public static void deactivateForm(StandardForm sf){
		activeFormes.remove(sf);
	}
	
	public static ArrayList<StandardForm> getActiveFormes(){
		return activeFormes;
	}
	
	public static StandardForm getActiveParent(){
		return activeFormes.get(0);
	}
	
	
}
