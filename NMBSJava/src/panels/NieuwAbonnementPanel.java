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
import gui.LangageHandler;

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
	private JLabel lblPrint;
	private JLabel lblStation1;
	private JLabel lblStation2;
	private JLabel lblFoutmelding;

	private JButton btnValideer;
	private JButton btnPrint;

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

	private JComboBox cbxTreinkaart;
	private JComboBox cbxDuur;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NieuwAbonnementPanel() {
		this.setLayout(new GridLayout(22, 2, 1, 1));

		lblTitle = new JLabel();
		LangageHandler.chooseLangageLbl(lblTitle, "nieuwAbo");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNaam = new JLabel();
		LangageHandler.chooseLangageLbl(lblNaam, "naam");
		txtNaam = new JTextField();

		lblVoornaam = new JLabel();
		LangageHandler.chooseLangageLbl(lblVoornaam, "voornaam");
		txtVoornaam = new JTextField();
		lblGeboortedatum = new JLabel();
		LangageHandler.chooseLangageLbl(lblGeboortedatum, "geboortedatum");
		lblEmail = new JLabel();
		LangageHandler.chooseLangageLbl(lblEmail, "email");
		txtEmail = new JTextField();
		lblTelefoonnr = new JLabel();
		LangageHandler.chooseLangageLbl(lblTelefoonnr, "telefoonNummer");
		txtTelefoonnr = new JTextField();
		lblGemeente = new JLabel();
		LangageHandler.chooseLangageLbl(lblGemeente, "gemeente");
		txtGemeente = new JTextField();
		lblPostcode = new JLabel();
		LangageHandler.chooseLangageLbl(lblPostcode, "postcode");
		txtPostcode = new JTextField();
		lblStraatEnNummer = new JLabel();
		LangageHandler.chooseLangageLbl(lblStraatEnNummer, "straat");

		txtStraatEnNummer = new JTextField();
		lblStartDatum = new JLabel();
		LangageHandler.chooseLangageLbl(lblStartDatum, "startdatum");
		lblKlasse = new JLabel();
		LangageHandler.chooseLangageLbl(lblKlasse, "klasse");
		rdbEersteKlasse = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbEersteKlasse, "1eKlasse");
		rdbTweedeKlasse = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbTweedeKlasse, "2eKlasse");
		lblTreinkaart = new JLabel();
		LangageHandler.chooseLangageLbl(lblTreinkaart, "treinkaart");
		lblVastTraject = new JLabel();
		LangageHandler.chooseLangageLbl(lblVastTraject,"vast");
		rdbJa = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbJa, "ja");
		rdbNee=new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbNee, "nee");
		lblDuur = new JLabel();
		LangageHandler.chooseLangageLbl(lblDuur, "duur");
		lblVervaldatum = new JLabel();
		LangageHandler.chooseLangageLbl(lblVervaldatum, "vervaldatum");
		
		//////////
		lblBerekendeVervaldatum = new JLabel("'Vervaldatum'");
		btnPrint = new JButton();
		LangageHandler.chooseLangageBtn(btnPrint, "print");
		lblPrint = new JLabel("€0");
		lblStation1 = new JLabel("Station 1: ");
		lblStation2 = new JLabel("Station 2: ");
		txtStation1 = new JTextField();
		txtStation2 = new JTextField();
		btnValideer = new JButton();
		LangageHandler.chooseLangageBtn(btnValideer, "valideer");
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
		dteGeboorteDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartDatum = new JDatePickerImpl(datePanel2, new GUIDateFormat());
		dteStartDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());


		String[] soortKaart = {LangageHandler.chooseLangage("trajecttreinkaart"), LangageHandler.chooseLangage("halftijdstreinkaart"), LangageHandler.chooseLangage("nettreinkaart"), LangageHandler.chooseLangage("schooltreinkaart") };
		cbxTreinkaart = new JComboBox(soortKaart);
		
		String[] aantalMaanden = {LangageHandler.chooseLangage("1maand"), LangageHandler.chooseLangage("3maand"), LangageHandler.chooseLangage("12maand")};
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
		this.add(lblStartDatum);
		this.add(dteStartDatum);
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
