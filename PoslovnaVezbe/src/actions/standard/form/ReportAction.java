package actions.standard.form;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import database.DBConnection;
import database.ForeignKeyGetter;
import gui.standard.form.StandardForm;
import gui.standard.form.StateManager.State;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	// kada se napravi genericka forma, staviti tu klasu umesto JDialog
	private StandardForm standardForm;

	public ReportAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/jasper.png")));
		putValue(SHORT_DESCRIPTION, "Report");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		  try{
			  JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("/reports/Lager.jasper").openStream(),null, DBConnection.getConnection());
				  JasperViewer.viewReport(jp, false);

				} catch (Exception ex) {
				  ex.printStackTrace();
				}
	}
}
