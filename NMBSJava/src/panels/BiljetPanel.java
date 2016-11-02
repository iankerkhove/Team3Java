package panels;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;
import java.awt.GridLayout;
import java.util.Properties;
import java.awt.Font;


@SuppressWarnings("serial")
public class BiljetPanel extends JPanel {
	private JLabel lblVan;
	private JTextField txtVan;
	private JLabel lblNaar;
	private JTextField txtNaar;
	private JDatePickerImpl dteGaanDatum;
	private JDatePickerImpl dteTerugDatum;
	private JComboBox cboBiljet;
	private JRadioButton rdbEnkel;
	private JRadioButton rdbHeenTerug;
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private JRadioButton rdbKlasseVerhoging;
	private ButtonGroup grpHeenTerug;
	private ButtonGroup grpKlasseTicket;
	private JButton btnPrint;
	private JLabel lblPrijs;
	
	//panels
	private JPanel vanNaarpanel;
	private JPanel datumspanel;
	private JPanel typeTicketpanel;	
	private JPanel klasseTicketpanel;
	private JPanel comboBoxpanel;
	/**
	 * Create the panel.
	 */
	public BiljetPanel() {
		this.setLayout(new GridLayout(5,2,5,5));
		vanNaarpanel = new JPanel();
		
		vanNaarpanel.setLayout(new GridLayout(2, 2, 10, 10));
		datumspanel = new JPanel();
		typeTicketpanel = new JPanel();
		klasseTicketpanel = new JPanel();
		comboBoxpanel = new JPanel();
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
		//datepicker
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		JDatePanelImpl datePanel1 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteGaanDatum = new JDatePickerImpl(datePanel1,new GUIDateFormat());
		dteGaanDatum.getJFormattedTextField().setText("Begin datum");
		
		comboBoxpanel.setLayout(new GridLayout(2, 1, 5, 5));
		String[] s = {"Standaardbiljet" , "GoPass 1" , "Weekendbiljet", "Seniorenbiljet", "Biljet Kind"};
		cboBiljet = new JComboBox(s);
	
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteTerugDatum = new JDatePickerImpl(datePanel2,new GUIDateFormat());
		dteTerugDatum.getJFormattedTextField().setText("Eind datum");
		
		rdbEnkel = new JRadioButton("Enkel");
		rdbHeenTerug = new JRadioButton("Heen en terug");
		
		typeTicketpanel.setLayout(new GridLayout(2, 2, 5, 5));
		
		klasseTicketpanel.setLayout(new GridLayout(3,2,5,5));
		rdbEersteKlasse = new JRadioButton("1e klasse");
		rdbTweedeKlasse = new JRadioButton("2e klasse");
		rdbKlasseVerhoging = new JRadioButton("Klasseverhoging");
		
		grpHeenTerug = new ButtonGroup();
		grpKlasseTicket = new ButtonGroup();
		
		btnPrint = new JButton("Print");
		lblPrijs = new JLabel(" € 0 ");
		lblPrijs.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrijs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//adds
		grpHeenTerug.add(rdbEnkel);
		grpHeenTerug.add(rdbHeenTerug);
		grpKlasseTicket.add(rdbEersteKlasse);
		grpKlasseTicket.add(rdbTweedeKlasse);
		grpKlasseTicket.add(rdbKlasseVerhoging);
		comboBoxpanel.add(cboBiljet);
		comboBoxpanel.add(new JLabel());
		klasseTicketpanel.add(new JLabel("Klasse: "));
		klasseTicketpanel.add(rdbEersteKlasse);
		klasseTicketpanel.add(new JLabel());
		klasseTicketpanel.add(rdbTweedeKlasse);
		klasseTicketpanel.add(new JLabel());
		klasseTicketpanel.add(rdbKlasseVerhoging);
		
		typeTicketpanel.add(new JLabel("type: "));
		typeTicketpanel.add(rdbEnkel);
		typeTicketpanel.add(new JLabel());
		typeTicketpanel.add(rdbHeenTerug);
		datumspanel.add(dteGaanDatum);
		datumspanel.add(dteTerugDatum);
		
		JLabel label = new JLabel("Koop biljet");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(label);
		add(new JLabel());
		add(vanNaarpanel);
		add (datumspanel);
		add(typeTicketpanel);
		add(comboBoxpanel);
		add(klasseTicketpanel);
		add(btnPrint);
		add(lblPrijs);
	}

}
