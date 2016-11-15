package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;

public class VerlengAbonnementPanel extends JPanel {
	
	private JPanel titel;
	private JPanel abonnementsNummer;
	private JPanel klantenNummer;
	private JPanel treinkaart;
	private JPanel startdatum;
	private JPanel duur;
	private JPanel vervaldatum;
	private JPanel klasse;
	private JPanel traject;
	private JPanel station1;
	private JPanel station2;
	private JPanel printEnValideer;
	
	private JLabel lblTitle;
	private JLabel lblAbonnementsNummer;
	private JLabel lblKlantenNummer;
	private JLabel lblKLantenNummerResult;
	private JLabel lblStartdatum;
	private JLabel lblDuur;
	private JLabel lblDuurResult;
	private JLabel lblVervaldatum;
	private JLabel lblVervaldatumResult;
	private JLabel lblKlasse;
	private JLabel lblVastTraject;
	private JLabel lblTreinkaart;
	private JLabel lblStation1;
	private JLabel lblStation2;
	private JLabel lblPrint;

	private JDatePickerImpl dteStartdatum;
	
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private ButtonGroup grpKlasses;
	
	private JRadioButton rdbJa;
	private JRadioButton rdbNee;
	private ButtonGroup grpJaNee;
	
	private JButton btnZoek;
	private JButton btnPrint;
	private JButton btnMeerInfo;
	private JButton btnValideer;

	private JTextField txtAbonnementsNummer;
	private JTextField txtStation1;
	private JTextField txtStation2;
	
	private JComboBox cbxTreinkaart;
	private JComboBox cbxDuur;

	public VerlengAbonnementPanel() {
		titelPanel();
		abonnementsNummerPanel();
		klantenNummerPanel();
		treinkaartPanel();
		startdatumPanel();
		duurPanel();
		vervaldatumPanel();
		klassePanel();
		trajectPanel();
		station1Panel();
		station2Panel();
		printEnValideerPanel();
		fullPanel();
	}
	
	private void fullPanel(){
		this.setLayout(new GridLayout(12,1,1,1));
		this.add(titel);
		this.add(abonnementsNummer);
		this.add(klantenNummer);
		this.add(treinkaart);
		this.add(startdatum);
		this.add(duur);
		this.add(vervaldatum);
		this.add(klasse);
		this.add(traject);
		this.add(station1);
		this.add(station2);
		this.add(printEnValideer);
	}
	
	private void titelPanel(){
		titel=new JPanel();
		titel.setLayout(new GridLayout(1, 1,1,1));
		
		lblTitle = new JLabel("Verleng Abonnement");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		titel.add(lblTitle);
	}
	
	private void abonnementsNummerPanel(){
		abonnementsNummer = new JPanel();
		abonnementsNummer.setLayout(new GridLayout(1,3,1,1));
		
		lblAbonnementsNummer = new JLabel("Abonnementsnummer: ");
		txtAbonnementsNummer = new JTextField();
		btnZoek = new JButton("Zoek");
		
		abonnementsNummer.add(lblAbonnementsNummer);
		abonnementsNummer.add(txtAbonnementsNummer);
		abonnementsNummer.add(btnZoek);
	}
	
	private void klantenNummerPanel(){
		klantenNummer = new JPanel();
		klantenNummer.setLayout(new GridLayout(1,3,1,1));
		
		lblKlantenNummer = new JLabel("Klantennumer: ");
		lblKLantenNummerResult = new JLabel("123456");
		btnMeerInfo = new JButton("Meer info");

		klantenNummer.add(lblKlantenNummer);
		klantenNummer.add(lblKLantenNummerResult);
		klantenNummer.add(btnMeerInfo);	
	}
	
	private void treinkaartPanel(){
		treinkaart = new JPanel();
		treinkaart.setLayout(new GridLayout(1,2,1,1));

		lblTreinkaart = new JLabel("Treinkaart: ");
		String[] str = { "Trajecttreinkaart", "Halftijdstreinkaart", "Nettreinkaart", "Schooltreinkaart" };
		cbxTreinkaart = new JComboBox(str);
		
		treinkaart.add(lblTreinkaart);
		treinkaart.add(cbxTreinkaart);
	}
	
	private void startdatumPanel(){
		startdatum = new JPanel();
		startdatum.setLayout(new GridLayout(1,2,1,1));

		lblStartdatum = new JLabel("Startdatum: ");
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartdatum = new JDatePickerImpl(datePanel, new GUIDateFormat());
		
		startdatum.add(lblStartdatum);
		startdatum.add(dteStartdatum);
	}
	
	private void duurPanel(){
		duur = new JPanel();
		duur.setLayout(new GridLayout(1,2,1,1));

		lblDuur = new JLabel("Duur: ");
		String[] aantalMaanden = { "1 maand", "3 maanden", "12 maanden"};
		cbxDuur = new JComboBox(aantalMaanden);
		
		duur.add(lblDuur);
		duur.add(cbxDuur);
	}
	
	private void vervaldatumPanel(){
		vervaldatum = new JPanel();
		vervaldatum.setLayout(new GridLayout(1,2,1,1));

		lblVervaldatum = new JLabel("Vervaldatum: ");
		lblVervaldatumResult = new JLabel(" ");
		
		vervaldatum.add(lblVervaldatum);
		vervaldatum.add(lblVervaldatumResult);
	}
	
	private void klassePanel(){
		klasse = new JPanel();
		klasse.setLayout(new GridLayout(1, 3,1,1));
		
		lblKlasse = new JLabel("Klasse: ");
		rdbEersteKlasse = new JRadioButton("1e Klas");
		rdbTweedeKlasse = new JRadioButton("2e Klas");
		
		grpKlasses = new ButtonGroup();
		grpKlasses.add(rdbEersteKlasse);
		grpKlasses.add(rdbTweedeKlasse);
				
		klasse.add(lblKlasse);
		klasse.add(rdbEersteKlasse);
		klasse.add(rdbTweedeKlasse);
	}
	
	private void trajectPanel(){
		traject = new JPanel();
		traject.setLayout(new GridLayout(1, 3,1,1));
		
		lblVastTraject = new JLabel("Vast traject: ");
		rdbJa = new JRadioButton("Ja");
		rdbNee=new JRadioButton("Nee");
		
		grpJaNee = new ButtonGroup();
		grpJaNee.add(rdbJa);
		grpJaNee.add(rdbNee);
		
		traject.add(lblVastTraject);
		traject.add(rdbJa);
		traject.add(rdbNee);
	}
	
	private void station1Panel(){
		station1 = new JPanel();
		station1.setLayout(new GridLayout(1,2,1,1));		
		
		lblStation1 = new JLabel("Station 1: ");
		txtStation1 = new JTextField();
		
		station1.add(lblStation1);
		station1.add(txtStation1);
		
	}
	
	private void station2Panel(){
		station2 = new JPanel();
		station2.setLayout(new GridLayout(1,2,1,1));
		
		lblStation2 = new JLabel("Station 2: ");
		txtStation2 = new JTextField();
		
		station2.add(lblStation2);
		station2.add(txtStation2);
	}
	
	
	private void printEnValideerPanel(){
		printEnValideer = new JPanel();
		printEnValideer.setLayout(new GridLayout(1, 3, 1, 1));
		
		btnPrint = new JButton("PRINT");
		lblPrint = new JLabel("€0");		
		btnValideer = new JButton("Valideer");
		
		printEnValideer.add(btnPrint);
		printEnValideer.add(lblPrint);
		printEnValideer.add(btnValideer);
	}

	public JPanel getTitel() {
		return titel;
	}

	public JPanel getAbonnementsNummer() {
		return abonnementsNummer;
	}

	public JPanel getKlantenNummer() {
		return klantenNummer;
	}

	public JPanel getTreinkaart() {
		return treinkaart;
	}

	public JPanel getStartdatum() {
		return startdatum;
	}

	public JPanel getDuur() {
		return duur;
	}

	public JPanel getVervaldatum() {
		return vervaldatum;
	}

	public JPanel getKlasse() {
		return klasse;
	}

	public JPanel getTraject() {
		return traject;
	}

	public JPanel getStation1() {
		return station1;
	}

	public JPanel getStation2() {
		return station2;
	}

	public JPanel getPrintEnValideer() {
		return printEnValideer;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblAbonnementsNummer() {
		return lblAbonnementsNummer;
	}

	public JLabel getLblKlantenNummer() {
		return lblKlantenNummer;
	}

	public JLabel getLblKLantenNummerResult() {
		return lblKLantenNummerResult;
	}

	public JLabel getLblStartdatum() {
		return lblStartdatum;
	}

	public JLabel getLblDuur() {
		return lblDuur;
	}

	public JLabel getLblDuurResult() {
		return lblDuurResult;
	}

	public JLabel getLblVervaldatum() {
		return lblVervaldatum;
	}

	public JLabel getLblVervaldatumResult() {
		return lblVervaldatumResult;
	}

	public JLabel getLblKlasse() {
		return lblKlasse;
	}

	public JLabel getLblVastTraject() {
		return lblVastTraject;
	}

	public JLabel getLblTreinkaart() {
		return lblTreinkaart;
	}

	public JLabel getLblStation1() {
		return lblStation1;
	}

	public JLabel getLblStation2() {
		return lblStation2;
	}

	public JLabel getLblPrint() {
		return lblPrint;
	}

	public JDatePickerImpl getDteStartdatum() {
		return dteStartdatum;
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

	public JButton getBtnZoek() {
		return btnZoek;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JButton getBtnMeerInfo() {
		return btnMeerInfo;
	}

	public JButton getBtnValideer() {
		return btnValideer;
	}

	public JTextField getTxtAbonnementsNummer() {
		return txtAbonnementsNummer;
	}

	public JTextField getTxtStation1() {
		return txtStation1;
	}

	public JTextField getTxtStation2() {
		return txtStation2;
	}

	public JComboBox getCbxTreinkaart() {
		return cbxTreinkaart;
	}

	public JComboBox getCbxDuur() {
		return cbxDuur;
	}

}
