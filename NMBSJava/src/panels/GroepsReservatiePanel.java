package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.github.lgooddatepicker.components.TimePicker;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

public class GroepsReservatiePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTextField txtGroepsnaam;
	private JLabel lblGroepsnaam;
	private JTextField txtNaamVerantwoordelijke;
	private JLabel lblNaamVerantwoordelijke;
	private StationsAutoCompletor txtVan;
	private JLabel lblVan;
	private StationsAutoCompletor txtNaar;
	private JLabel lblNaar;
	private JComboBox<String> cboTrein;
	private JSpinner personen;

	private JDatePickerImpl dteGaanDatum;
	private JDatePickerImpl dteTerugDatum;
	private JButton btnPrint;
	private JLabel lblPrijs;

	private JLabel lbltimegaan;
	private JLabel lbltimeterug;
	private TimePicker timeGaan;
	private TimePicker timeTerug;
	
	private JRadioButton rdbVertrekd;
	private JRadioButton rdbAankomstd;
	private ButtonGroup grpTimeSeld;
	
	private JRadioButton rdbVertrekt;
	private JRadioButton rdbAankomstt;
	private ButtonGroup grpTimeSelt;
	
	private JCheckBox doorTerug;

	private JPanel vanNaarpanel;
	private JPanel comboBoxpanel;
	private JPanel doorpanel;
	private JPanel terugpanel;
	private JPanel naampanel;

	public GroepsReservatiePanel() {
		this.setLayout(new GridLayout(4,2,5,5));
		//panels
		vanNaarpanel = new JPanel();
		vanNaarpanel.setLayout(new GridLayout(2, 2, 10, 10));
		comboBoxpanel = new JPanel();
		comboBoxpanel.setLayout(new FlowLayout());
		doorpanel = new JPanel();
		doorpanel.setLayout(new GridLayout(2, 1, 10, 10));
		terugpanel = new JPanel();
		terugpanel.setLayout(new GridLayout(2, 1, 10, 10));
		terugpanel.setVisible(false);
		naampanel = new JPanel();
		naampanel.setLayout(new GridLayout(2, 1, 10, 10));
		//labelGroepsnaam
		lblGroepsnaam= new JLabel("Groepsnaam: ");
		naampanel.add(lblGroepsnaam);
		
		txtGroepsnaam= new JTextField(15);
		naampanel.add(txtGroepsnaam);
		
		//label naam verantwoordelijke
		lblNaamVerantwoordelijke = new JLabel("Naam vd verantwoordelijke: ");
		naampanel.add(lblNaamVerantwoordelijke);
		
		txtNaamVerantwoordelijke = new JTextField(15);
		naampanel.add(txtNaamVerantwoordelijke);
		// labelVan
		lblVan = new JLabel("Van: ");
		vanNaarpanel.add(lblVan);

		txtVan = new StationsAutoCompletor();
		vanNaarpanel.add(txtVan);
		//labelNaar
		lblNaar = new JLabel("Naar: ");
		vanNaarpanel.add(lblNaar);

		txtNaar = new StationsAutoCompletor();
		vanNaarpanel.add(txtNaar);

		// datepicker properties
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		// datepicker
		JDatePanelImpl datePanel1 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteGaanDatum = new JDatePickerImpl(datePanel1, new GUIDateFormat());
		dteGaanDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteTerugDatum = new JDatePickerImpl(datePanel2,new GUIDateFormat());
		//dteTerugDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		dteTerugDatum.setEnabled(false);
		//time
		timeGaan = new TimePicker();
		timeGaan.setText(GUIDateFormat.getTime());
		
		timeTerug = new TimePicker();
		timeTerug.setText(GUIDateFormat.getTime());
		timeTerug.setEnabled(false);
		
		//rdb's
		rdbVertrekd = new JRadioButton("Vertrek");
		rdbVertrekd.setSelected(true);
		rdbVertrekd.setMnemonic(1);
		rdbAankomstd = new JRadioButton("Aankomst");
		rdbAankomstd.setMnemonic(2);
		grpTimeSeld = new ButtonGroup();
		grpTimeSeld.add(rdbVertrekd);
		grpTimeSeld.add(rdbAankomstd);
		
		JPanel timeSelPaneldoor = new JPanel();
		timeSelPaneldoor.add(rdbVertrekd);
		timeSelPaneldoor.add(rdbAankomstd);
		
		rdbVertrekt = new JRadioButton("Vertrek");
		rdbVertrekt.setSelected(true);
		rdbVertrekt.setMnemonic(1);
		rdbAankomstt = new JRadioButton("Aankomst");
		rdbAankomstt.setMnemonic(2);
		grpTimeSelt = new ButtonGroup();
		grpTimeSelt.add(rdbVertrekt);
		grpTimeSelt.add(rdbAankomstt);
		
		JPanel timeSelPanelterug = new JPanel();
		timeSelPanelterug.add(rdbVertrekt);
		timeSelPanelterug.add(rdbAankomstt);
		//combobox
		cboTrein=new JComboBox<String>();
		cboTrein.setPreferredSize(new Dimension(100,25));
		cboTrein.setEditable(true);
		cboTrein.setSelectedItem("Selecteer trein");
		comboBoxpanel.add(cboTrein);
		//Spinner
		SpinnerModel model = new SpinnerNumberModel(0, 0, 200, 1);
		personen= new JSpinner(model);
		personen.setPreferredSize(new Dimension(50,25));
		comboBoxpanel.add(personen);
		//print
		btnPrint = new JButton("Print");
		//prijs
		lblPrijs = new JLabel(" € 0 ");
		lblPrijs.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrijs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//checbox
		doorTerug = new JCheckBox("Heenreis en terugreis");
		comboBoxpanel.add(doorTerug);
		
		doorpanel.add(new JLabel("Door: "));
		doorpanel.add(dteGaanDatum);
		doorpanel.add(timeSelPaneldoor);
		doorpanel.add(timeGaan);
		terugpanel.add(new JLabel("Terug: "));
		terugpanel.add(dteTerugDatum);
		terugpanel.add(timeSelPanelterug);
		terugpanel.add(timeTerug);
		
		//add's
		add(naampanel);
		add(vanNaarpanel);
		add(doorpanel);
		add(terugpanel);
		add(comboBoxpanel);
		add(lblPrijs);
		add(btnPrint);
		
		
	}

	public JTextField getTxtGroepsnaam() {
		return txtGroepsnaam;
	}
	public JLabel getLblGroepsnaam() {
		return lblGroepsnaam;
	}
	public JTextField getTxtNaamVerantwoordelijke() {
		return txtNaamVerantwoordelijke;
	}
	public JLabel getLblNaamVerantwoordelijke() {
		return lblNaamVerantwoordelijke;
	}
	public StationsAutoCompletor getTxtVan() {
		return txtVan;
	}
	public JLabel getLblVan() {
		return lblVan;
	}
	public StationsAutoCompletor getTxtNaar() {
		return txtNaar;
	}
	public JLabel getLblNaar() {
		return lblNaar;
	}
	public JComboBox<String> getCboTrein() {
		return cboTrein;
	}
	public JSpinner getPersonen() {
		return personen;
	}
	public JDatePickerImpl getDteGaanDatum() {
		return dteGaanDatum;
	}
	public JDatePickerImpl getDteTerugDatum() {
		return dteTerugDatum;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
	public JLabel getLblPrijs() {
		return lblPrijs;
	}
	public JLabel getLbltimegaan() {
		return lbltimegaan;
	}
	public JLabel getLbltimeterug() {
		return lbltimeterug;
	}
	public TimePicker getTimeGaan() {
		return timeGaan;
	}
	public TimePicker getTimeTerug() {
		return timeTerug;
	}
	public JCheckBox getDoorTerug() {
		return doorTerug;
	}
	public JPanel getVanNaarpanel() {
		return vanNaarpanel;
	}
	public JPanel getComboBoxpanel() {
		return comboBoxpanel;
	}
	public JPanel getDoorpanel() {
		return doorpanel;
	}
	public JPanel getTerugpanel() {
		return terugpanel;
	}
	public JPanel getNaampanel() {
		return naampanel;
	}
}
