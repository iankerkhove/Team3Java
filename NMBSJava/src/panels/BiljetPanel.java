package panels;

import javax.swing.*;

import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;


public class BiljetPanel<add> extends JPanel {
	private JLabel lblVan;
	private JTextField txtVan;
	private JLabel lblNaar;
	private JTextField txtNaar;
	private JDatePickerImpl dteDatum;

	/**
	 * Create the panel.
	 */
	public BiljetPanel() {
		this.setLayout(new GridLayout(4,4,10,10));

		JPanel vanNaarpanel = new JPanel();
		add(vanNaarpanel);
		
		vanNaarpanel.setLayout(new GridLayout(2, 2, 10, 10));
		// labelVan
		lblVan = new JLabel("Van: ");
		vanNaarpanel.add(lblVan);
		// textfieldVan
		txtVan = new JTextField("van");
		vanNaarpanel.add(txtVan);
		
		lblNaar = new JLabel("Naar: ");
		vanNaarpanel.add(lblNaar);
		
		txtNaar = new JTextField("naar");
		vanNaarpanel.add(txtNaar);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
		dteDatum = new JDatePickerImpl(datePanel, null);
		
		add(dteDatum);
		
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
	}

}
