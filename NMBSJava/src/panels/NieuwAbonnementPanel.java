package panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.util.Date;
import java.util.Properties;
import java.util.Calendar;
import org.jdatepicker.impl.*;

import gui.GUIDateFormat;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;

public class NieuwAbonnementPanel extends JPanel {
	private JLabel lblTitle;
	private JLabel lblNaam;
	private JLabel lblVoornaam;
	private JLabel lblGeboortedatum;
	private JLabel lblEmail;
	private JLabel lblTelefoonnr;
	private JLabel lblGemeente;
	private JLabel lblPostcode;
	private JLabel lblStraatEnNummer;
	private JLabel lblStartDatum;
	private JLabel lblKlasse;
	private JLabel lblTreinkaart;
	private JLabel lblVastTraject;
	private JLabel lblDuurLabel;
	private JLabel lblDuur;
	private JLabel lblVervaldatum;
	private JLabel lblPrijs;
	private JLabel lblPrint;
	private JLabel lblStation1;
	private JLabel lblStation2;

	private JTextField txtNaam;
	private JTextField txtVoornaam;
	private JTextField txtEmail;
	private JTextField txtTelefoonnr;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JTextField txtStraatEnNummer;
	private JTextField txtStation1;
	private JTextField txtStation2;

	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private ButtonGroup grpKlasses;
	
	private JRadioButton rdbJa;
	private JRadioButton rdbNee;
	private ButtonGroup grpJaNee;
	

	private JDatePickerImpl geboorteDatum;
	private JDatePickerImpl startDatum;

	private JComboBox cbxTreinkaart;


	public NieuwAbonnementPanel() {
		this.setLayout(new GridLayout(21, 2, 1, 1));

		lblTitle = new JLabel("Nieuw Abonnement");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNaam = new JLabel("Naam: ");
		txtNaam = new JTextField();

		lblVoornaam = new JLabel("Voornaam: ");
		txtVoornaam = new JTextField();
		lblGeboortedatum = new JLabel("Geboortedatum: ");
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField();
		lblTelefoonnr = new JLabel("Telefoonnummer: ");
		txtTelefoonnr = new JTextField();
		lblGemeente = new JLabel("Gemeente: ");
		txtGemeente = new JTextField();
		lblPostcode = new JLabel("Postcode: ");
		txtPostcode = new JTextField();
		lblStraatEnNummer = new JLabel("Straat + nr: ");
		txtStraatEnNummer = new JTextField();
		lblStartDatum = new JLabel("Startdatum: ");
		lblKlasse = new JLabel("Klasse: ");
		rdbEersteKlasse = new JRadioButton("1e Klas");
		rdbTweedeKlasse = new JRadioButton("2e Klas");
		lblTreinkaart = new JLabel("Type treinkaart: ");
		lblVastTraject = new JLabel("Vast traject: ");
		rdbJa = new JRadioButton("Ja");
		rdbNee=new JRadioButton("Nee");
		lblDuurLabel = new JLabel("Duur: ");
		lblDuur = new JLabel("'Duur'");
		lblVervaldatum = new JLabel("'Vervaldatum'");
		lblPrijs = new JLabel("Prijs:");
		lblPrint = new JLabel("'Berekende prijs'");
		lblStation1 = new JLabel("Station 1: ");
		lblStation2 = new JLabel("Station 2: ");
		txtStation1 = new JTextField();
		txtStation2 = new JTextField();
		
		grpKlasses = new ButtonGroup();
		grpKlasses.add(rdbEersteKlasse);
		grpKlasses.add(rdbTweedeKlasse);
		
		grpJaNee = new ButtonGroup();
		grpJaNee.add(rdbJa);
		grpJaNee.add(rdbNee);

		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(new UtilDateModel(), properties);
		geboorteDatum = new JDatePickerImpl(datePanel1, new GUIDateFormat());
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		startDatum = new JDatePickerImpl(datePanel2, new GUIDateFormat());

		String[] str = { "Trajecttreinkaart", "Halftijdstreinkaart", "Nettreinkaart", "Schooltreinkaart" };
		cbxTreinkaart = new JComboBox(str);

		this.add(lblTitle);
		this.add(new JLabel());
		this.add(lblNaam);
		this.add(txtVoornaam);
		this.add(lblVoornaam);
		this.add(txtNaam);
		this.add(lblGeboortedatum);
		this.add(geboorteDatum);
		this.add(lblEmail);
		this.add(txtEmail);
		this.add(lblTelefoonnr);
		this.add(txtTelefoonnr);
		this.add(lblGemeente);
		this.add(txtGemeente);
		this.add(lblPostcode);
		this.add(txtPostcode);
		this.add(lblStraatEnNummer);
		this.add(txtStraatEnNummer);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(lblTreinkaart);
		this.add(cbxTreinkaart);
		this.add(lblStartDatum);
		this.add(startDatum);
		this.add(lblKlasse);
		this.add(rdbEersteKlasse);
		this.add(new JLabel());
		this.add(rdbTweedeKlasse);
		this.add(lblVastTraject);
		this.add(rdbJa);
		this.add(new JLabel());
		this.add(rdbNee);
		this.add(lblStation1);
		this.add(txtStation1);
		this.add(lblStation2);
		this.add(txtStation2);
		this.add(lblDuurLabel);
		this.add(lblDuur);
		this.add(new JLabel());
		this.add(lblVervaldatum);
		this.add(lblPrijs);
		this.add(lblPrint);

	}

}
