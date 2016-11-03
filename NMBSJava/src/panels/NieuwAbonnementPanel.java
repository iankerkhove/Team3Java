package panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
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

		private JTextField txtNaam;
		private JTextField txtVoornaam;
		private JTextField txtEmail;
		private JTextField txtTelefoonnr;
		private JTextField txtGemeente;
		private JTextField txtPostcode;
		private JTextField txtStraatEnNummer;
		
		private JButton butEersteKlas;
		private JButton butTweedeKlas;

		private JDatePickerImpl geboorteDatum;
		private JDatePickerImpl startDatum;

		public NieuwAbonnementPanel() {
			
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
			butEersteKlas = new JButton("1e Klas");
			butTweedeKlas = new JButton("2e Klas");
			
			Properties properties = new Properties();
			properties.put("text.today", "Today");
			properties.put("text.month", "Month");
			properties.put("text.year", "Year");
			JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
			geboorteDatum = new JDatePickerImpl(datePanel, new GUIDateFormat());
			startDatum = new JDatePickerImpl(datePanel, new GUIDateFormat());

			SpringLayout springLayout = (SpringLayout) geboorteDatum.getLayout();
			SpringLayout springLayout2 = (SpringLayout) startDatum.getLayout();

			springLayout.putConstraint(SpringLayout.WEST, geboorteDatum.getJFormattedTextField(), 0, SpringLayout.WEST, geboorteDatum);
			geboorteDatum.getJFormattedTextField().setText("01/11/2016");
			
			springLayout2.putConstraint(SpringLayout.WEST, startDatum.getJFormattedTextField(), 0, SpringLayout.WEST, geboorteDatum);
			startDatum.getJFormattedTextField().setText("01/11/2016");
			
			setLayout(new GridLayout(12,2,5,5));
			txtVoornaam.setHorizontalAlignment(SwingConstants.LEFT);
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
			this.add(lblStartDatum);
			this.add(startDatum);
			this.add(lblKlasse);
			this.add(new JLabel());
			this.add(butEersteKlas);
			this.add(butTweedeKlas);
			
		}

	}
