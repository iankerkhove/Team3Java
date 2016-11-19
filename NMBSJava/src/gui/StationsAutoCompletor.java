package gui;

import javax.swing.JComboBox;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.CacheExistingSations;

public class StationsAutoCompletor extends JComboBox<String> {
	
	private static final long serialVersionUID = -5597294633497864663L;

	public StationsAutoCompletor() {
		for (int i = 0; i < CacheExistingSations.getAllStations().size(); i++) {
			this.addItem(CacheExistingSations.getAllStations().get(i));
		}
		AutoCompleteDecorator.decorate(this);
	}
}
