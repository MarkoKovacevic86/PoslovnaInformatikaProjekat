package gui.standard.form;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

import net.miginfocom.swing.MigLayout;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaTable;

/**
 * @author igot
 *
 */
public class AddAnchorDialog {
	
	private MetaTable mt;
	private MyPanel sPanel;
	
	public AddAnchorDialog(MetaTable metat){
		mt = metat;
		initialiseDialog();
	}
	
	private void initialiseDialog(){
		sPanel = new MyPanel();
		sPanel.setCode(mt.getCode());

		sPanel.setLayout(new MigLayout());
		for(MetaColumn mc : mt){
			JLabel l = new JLabel(mc.getName());
			JTextField tf = new JTextField(10);
			
			tf.setName(mc.getName());
			sPanel.add(l);
			
			if(mc.isPartOfFK()){
				MyButton mb = new MyButton("+");
				mb.setButtonZoomTable(mc.getFkColParent().getParentTable());
				tf.setEditable(false);
				sPanel.add(tf);
				sPanel.add(mb, "wrap");
			}else{
				sPanel.add(tf,"wrap");
			}
		}
		
	}
	
	public JPanel getPanel(){
		return sPanel;
	}
	
}
