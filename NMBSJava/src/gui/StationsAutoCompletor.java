package gui;

import java.util.ArrayList;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import dao.StationDAO;
import model.Station;

public class StationsAutoCompletor extends JComboBox<String> {
	
	private static final long serialVersionUID = -5597294633497864663L;

	public StationsAutoCompletor() {
		StationDAO handler = new StationDAO();
		ArrayList<Station> list = handler.selectAll();
		
		for (int i = 0; i < list.size(); i++) {
			this.addItem(list.get(i).getStationName());
		}
		AutoCompleteDecorator.decorate(this);
	}
}