package gui;

import java.util.ArrayList;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import dao.TypeTicketDAO;
import model.TypeTicket;

public class TicketTypesAutoCompletor extends JComboBox<String> {
	
	private static final long serialVersionUID = 5648915762663573962L;
	
	public TicketTypesAutoCompletor() {
		TypeTicketDAO handler = new TypeTicketDAO();
		ArrayList<TypeTicket> list = handler.selectAll();
		
		for (int i = 0; i < list.size(); i++) {
			this.addItem(list.get(i).getName());
		}
		AutoCompleteDecorator.decorate(this);
	}
}