package panels;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

import model.Station;
import java.awt.GridLayout;
import java.util.Properties;
import java.awt.Font;

import gui.LangageHandler;

@SuppressWarnings("serial") 
public class BiljetPanel extends JPanel {
	private String taal = LangageHandler.getTaal();
	
	private JLabel lblVan;
	private StationsAutoCompletor txtVan;
	private JLabel lblNaar;
	private StationsAutoCompletor txtNaar;
	private JDatePickerImpl dteGaanDatum;
	private JDatePickerImpl dteTerugDatum;
	private JComboBox<String> cboBiljet;
	private JRadioButton rdbEnkel;
	private JRadioButton rdbHeenTerug;
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private JRadioButton rdbKlasseVerhoging;
	private ButtonGroup grpHeenTerug;
	private ButtonGroup grpKlasseTicket;
	private JButton btnPrint;
	private JLabel lblPrijs;
	//
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
		lblVan = new JLabel();
		LangageHandler.chooseLangageLbl(lblVan, taal, "van");
		vanNaarpanel.add(lblVan);
		// textfieldVan
		txtVan = new StationsAutoCompletor();
		vanNaarpanel.add(txtVan);
		
		lblNaar = new JLabel("Naar: ");
		LangageHandler.chooseLangageLbl(lblNaar, taal, "naar");
		vanNaarpanel.add(lblNaar);
		
		txtNaar = new StationsAutoCompletor();
		vanNaarpanel.add(txtNaar);
		//datepicker
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		JDatePanelImpl datePanel1 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteGaanDatum = new JDatePickerImpl(datePanel1,new GUIDateFormat());
		dteGaanDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		
		comboBoxpanel.setLayout(new GridLayout(2, 1, 5, 5));
		String[] s = {"Standaardbiljet" , "GoPass 1" , "Weekendbiljet", "Seniorenbiljet", "Biljet Kind"};
		cboBiljet = new JComboBox<String>(s);
		
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteTerugDatum = new JDatePickerImpl(datePanel2,new GUIDateFormat());
		dteTerugDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		
		rdbEnkel = new JRadioButton();
		
		rdbEnkel.setMnemonic(1);
		rdbEnkel.setSelected(true);
		rdbHeenTerug = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbHeenTerug, taal, "heenTerug");
		rdbHeenTerug.setMnemonic(2);
		
		typeTicketpanel.setLayout(new GridLayout(2, 2, 5, 5));
		
		klasseTicketpanel.setLayout(new GridLayout(3,2,5,5));
		rdbEersteKlasse = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbEersteKlasse, taal, "1eKlasse");
		rdbEersteKlasse.setMnemonic(1);
		rdbTweedeKlasse = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbTweedeKlasse, taal, "2eKlasse");
		rdbTweedeKlasse.setMnemonic(2);
		rdbTweedeKlasse.setSelected(true);
		rdbKlasseVerhoging = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbKlasseVerhoging, taal, "klasseVerhoging");
		rdbKlasseVerhoging.setMnemonic(3);
		
		grpHeenTerug = new ButtonGroup();
		grpKlasseTicket = new ButtonGroup();
		
		btnPrint = new JButton("Print");
		LangageHandler.chooseLangageBtn(btnPrint, taal, "print");
		lblPrijs = new JLabel();
		LangageHandler.chooseLangageLbl(lblPrijs, taal, "prijs");
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
		
		JLabel label = new JLabel();
		LangageHandler.chooseLangageLbl(label, taal, "koopBiljet");
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
	public JLabel getLblVan() {
		return lblVan;
	}
	public StationsAutoCompletor getTxtVan() {
		return txtVan;
	}
	public JLabel getLblNaar() {
		return lblNaar;
	}
	public StationsAutoCompletor getTxtNaar() {
		return txtNaar;
	}
	public JDatePickerImpl getDteGaanDatum() {
		return dteGaanDatum;
	}
	public JDatePickerImpl getDteTerugDatum() {
		return dteTerugDatum;
	}
	public JComboBox<String> getCboBiljet() {
		return cboBiljet;
	}
	public JRadioButton getRdbEnkel() {
		return rdbEnkel;
	}
	public JRadioButton getRdbHeenTerug() {
		return rdbHeenTerug;
	}
	public JRadioButton getRdbEersteKlasse() {
		return rdbEersteKlasse;
	}
	public JRadioButton getRdbTweedeKlasse() {
		return rdbTweedeKlasse;
	}
	public JRadioButton getRdbKlasseVerhoging() {
		return rdbKlasseVerhoging;
	}
	public ButtonGroup getGrpHeenTerug() {
		return grpHeenTerug;
	}
	public ButtonGroup getGrpKlasseTicket() {
		return grpKlasseTicket;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
	public JLabel getLblPrijs() {
		return lblPrijs;
	}
}
