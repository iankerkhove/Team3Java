package gui;

import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controller.CacheTicketTypes;

public class TicketTypesAutoCompletor extends JComboBox<String> {
	
	private static final long serialVersionUID = 5648915762663573962L;
	
	public TicketTypesAutoCompletor() {
		for (int i = 0; i < CacheTicketTypes.getObjectCount(); i++) {
			this.addItem(CacheTicketTypes.get(i));
		}
		AutoCompleteDecorator.decorate(this);
	}

}
