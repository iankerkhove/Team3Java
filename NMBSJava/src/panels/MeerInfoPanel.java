package panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeerInfoPanel extends JPanel {
private JLabel lblTitle;
private JLabel lblNaam;
private JLabel lblNaamResult;
private JLabel lblVoornaam;
private JLabel lblVoornaamResult;
private JLabel lblGeboortedatum;
private JLabel lblGeboortedatumResult;
private JLabel lblEmail;
private JLabel lblEmailResult;
private JLabel lblTelefoonnummer;
private JLabel lblTelefoonnummerResult;
private JLabel lblGemeente;
private JLabel lblGemeenteResult;
private JLabel lblPostcode;
private JLabel lblPostcodeResult;
private JLabel lblStraatEnNummer;
private JLabel lblStraatEnNummerResult;

	public MeerInfoPanel() {
		this.setLayout(new GridLayout(9, 2));
		lblTitle = new JLabel("Meer info");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNaam = new JLabel("Naam: ");
		lblVoornaam = new JLabel("Voornaam: ");
		lblGeboortedatum = new JLabel("Geboortedatum: ");
		lblEmail = new JLabel("Email: ");
		lblTelefoonnummer = new JLabel("Telefoonnummer: ");
		lblGemeente = new JLabel("Gemeente: ");
		lblPostcode = new JLabel("Postcode: ");
		lblStraatEnNummer = new JLabel("Straat + nr: ");
		
		lblNaamResult = new JLabel("");
		lblVoornaamResult = new JLabel("");
		lblGeboortedatumResult = new JLabel("");
		lblEmailResult = new JLabel("");
		lblTelefoonnummerResult = new JLabel("");
		lblGemeenteResult = new JLabel("");
		lblPostcodeResult = new JLabel("");
		lblStraatEnNummerResult = new JLabel("");
		
		
		this.add(lblTitle);
		this.add(new JLabel());
		this.add(lblNaam);
		this.add(lblNaamResult);
		this.add(lblVoornaam);
		this.add(lblVoornaamResult);
		this.add(lblGeboortedatum);
		this.add(lblGeboortedatumResult);
		this.add(lblEmail);
		this.add(lblEmailResult);
		this.add(lblTelefoonnummer);
		this.add(lblTelefoonnummerResult);
		this.add(lblGemeente);
		this.add(lblGemeenteResult);
		this.add(lblPostcode);
		this.add(lblPostcodeResult);
		this.add(lblStraatEnNummer);
		this.add(lblStraatEnNummerResult);
		
		
	}

}
