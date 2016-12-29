package gui;

import java.util.ArrayList;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import dao.DiscountDAO;
import model.Discount;

public class DiscountAutoCompletor extends JComboBox<String> {
	
	public DiscountAutoCompletor() {
		DiscountDAO handler = new DiscountDAO();
		ArrayList<Discount> list = handler.selectAll();
		for (int i = 0; i < list.size(); i++) {
			this.addItem(list.get(i).getName());
		}
		AutoCompleteDecorator.decorate(this);
	}
}