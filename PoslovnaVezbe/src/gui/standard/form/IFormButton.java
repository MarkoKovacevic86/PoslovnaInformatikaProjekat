package gui.standard.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rs.mgifos.mosquito.model.MetaTable;

public interface IFormButton extends ActionListener{
	@Override
	void actionPerformed(ActionEvent e);
	
	//public void setContainingForm(StandardForm sf);
	public void setParentPanel(MyPanel parentPanel);
	public IFormButton setButtonZoomTable(String name);
	public IFormButton setButtonZoomTable(MetaTable table);
}
