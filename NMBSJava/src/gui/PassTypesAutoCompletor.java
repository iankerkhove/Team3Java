package gui;

import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controller.CachePassTypes;

public class PassTypesAutoCompletor extends JComboBox<String> {
	
	private static final long serialVersionUID = 1992628746130586151L;
	
	public PassTypesAutoCompletor() {
		for (int i = 0; i < CachePassTypes.getObjectCount(); i++) {
			this.addItem(CachePassTypes.get(i));
		}
		AutoCompleteDecorator.decorate(this);
	}

}
