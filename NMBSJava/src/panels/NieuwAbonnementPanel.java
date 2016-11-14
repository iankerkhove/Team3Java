package panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.util.Properties;
import org.jdatepicker.impl.*;
import gui.GUIDateFormat;

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
	private JLabel lblDuur;
	private JLabel lblBerekendeVervaldatum;
	private JLabel lblVervaldatum;
	private JButton btnPrint;
	private JLabel lblPrint;
	private JLabel lblStation1;
	private JLabel lblStation2;
	private JButton btnValideer;
	private JLabel lblFoutmelding;

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
	

	private JDatePickerImpl dteGeboorteDatum;
	private JDatePickerImpl dteStartDatum;

	@SuppressWarnings("rawtypes")
	private JComboBox cbxTreinkaart;
	private JComboBox cbxDuur;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NieuwAbonnementPanel() {
		this.setLayout(new GridLayout(22, 2, 1, 1));

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
		lblDuur = new JLabel("Duur: ");
		lblVervaldatum = new JLabel("Vervaldatum: ");
		lblBerekendeVervaldatum = new JLabel("'Vervaldatum'");
		btnPrint = new JButton("PRINT");
		lblPrint = new JLabel("€0");
		lblStation1 = new JLabel("Station 1: ");
		lblStation2 = new JLabel("Station 2: ");
		txtStation1 = new JTextField();
		txtStation2 = new JTextField();
		btnValideer = new JButton("Valideer");
		lblFoutmelding = new JLabel("");
		
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
		dteGeboorteDatum = new JDatePickerImpl(datePanel1, new GUIDateFormat());
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartDatum = new JDatePickerImpl(datePanel2, new GUIDateFormat());

		String[] soortKaart = { "Trajecttreinkaart", "Halftijdstreinkaart", "Nettreinkaart", "Schooltreinkaart" };
		cbxTreinkaart = new JComboBox(soortKaart);
		
		String[] aantalMaanden = { "1 maand", "3 maanden", "12 maanden"};
		cbxDuur = new JComboBox(aantalMaanden);

		this.add(lblTitle);
		this.add(new JLabel());
		this.add(lblNaam);
		this.add(txtNaam);
		this.add(lblVoornaam);
		this.add(txtVoornaam);
		this.add(lblGeboortedatum);
		this.add(dteGeboorteDatum);
		this.add(lblEmail);
		this.add(txtEmail);
		this.add(lblTelefoonnr);
		this.add(txtTelefoonnr);
		this.add(lblStraatEnNummer);
		this.add(txtStraatEnNummer);
		this.add(lblPostcode);
		this.add(txtPostcode);
		this.add(lblGemeente);
		this.add(txtGemeente);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(lblTreinkaart);
		this.add(cbxTreinkaart);
		this.add(lblStartDatum);
		this.add(dteStartDatum);
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
		this.add(lblDuur);
		this.add(cbxDuur);
		this.add(lblVervaldatum);
		this.add(lblBerekendeVervaldatum);
		this.add(btnPrint);
		this.add(lblPrint);
		this.add(lblFoutmelding);
		this.add(btnValideer);
		

	}


	public JLabel getLblTitle() {
		return lblTitle;
	}


	public JLabel getLblNaam() {
		return lblNaam;
	}


	public JLabel getLblVoornaam() {
		return lblVoornaam;
	}


	public JLabel getLblGeboortedatum() {
		return lblGeboortedatum;
	}


	public JLabel getLblEmail() {
		return lblEmail;
	}


	public JLabel getLblTelefoonnr() {
		return lblTelefoonnr;
	}


	public JLabel getLblGemeente() {
		return lblGemeente;
	}


	public JLabel getLblPostcode() {
		return lblPostcode;
	}


	public JLabel getLblStraatEnNummer() {
		return lblStraatEnNummer;
	}


	public JLabel getLblStartDatum() {
		return lblStartDatum;
	}


	public JLabel getLblKlasse() {
		return lblKlasse;
	}


	public JLabel getLblTreinkaart() {
		return lblTreinkaart;
	}


	public JLabel getLblVastTraject() {
		return lblVastTraject;
	}


	public JLabel getLblDuur() {
		return lblDuur;
	}


	public JLabel getLblBerekendeVervaldatum() {
		return lblBerekendeVervaldatum;
	}


	public JLabel getLblVervaldatum() {
		return lblVervaldatum;
	}


	public JButton getBtnPrint() {
		return btnPrint;
	}


	public JLabel getLblPrint() {
		return lblPrint;
	}


	public JLabel getLblStation1() {
		return lblStation1;
	}


	public JLabel getLblStation2() {
		return lblStation2;
	}


	public JButton getBtnValideer() {
		return btnValideer;
	}


	public JTextField getTxtNaam() {
		return txtNaam;
	}


	public JTextField getTxtVoornaam() {
		return txtVoornaam;
	}


	public JTextField getTxtEmail() {
		return txtEmail;
	}


	public JTextField getTxtTelefoonnr() {
		return txtTelefoonnr;
	}


	public JTextField getTxtGemeente() {
		return txtGemeente;
	}


	public JTextField getTxtPostcode() {
		return txtPostcode;
	}


	public JTextField getTxtStraatEnNummer() {
		return txtStraatEnNummer;
	}


	public JTextField getTxtStation1() {
		return txtStation1;
	}


	public JTextField getTxtStation2() {
		return txtStation2;
	}


	public JRadioButton getRdbEersteKlasse() {
		return rdbEersteKlasse;
	}


	public JRadioButton getRdbTweedeKlasse() {
		return rdbTweedeKlasse;
	}


	public ButtonGroup getGrpKlasses() {
		return grpKlasses;
	}


	public JRadioButton getRdbJa() {
		return rdbJa;
	}


	public JRadioButton getRdbNee() {
		return rdbNee;
	}


	public ButtonGroup getGrpJaNee() {
		return grpJaNee;
	}


	public JDatePickerImpl getDteGeboorteDatum() {
		return dteGeboorteDatum;
	}


	public JDatePickerImpl getDteStartDatum() {
		return dteStartDatum;
	}


	public JComboBox getCbxTreinkaart() {
		return cbxTreinkaart;
	}


	public JComboBox getCbxDuur() {
		return cbxDuur;
	}

	public JLabel getFoutmelding(){
		return lblFoutmelding;
	}
	

}
