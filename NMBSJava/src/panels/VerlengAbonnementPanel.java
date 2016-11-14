package panels;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;

public class VerlengAbonnementPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblAbonnementsNummer;
	private JLabel lblKlantenNummer;
	private JLabel lblKLantenNummerResult;
	private JLabel lblStartdatum;
	private JLabel lblDuur;
	private JLabel lblDuurResult;
	private JLabel lblVervaldatum;
	private JLabel lblKlasse;
	private JLabel lblVastTraject;
	private JLabel lblTreinkaart;
	private JLabel lblStation1;
	private JLabel lblStation2;
	private JLabel lblPrint;

	
	private JDatePickerImpl dteStartdatum;
	
	private JComboBox cbxTreinkaart;
	
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
	
	public VerlengAbonnementPanel() {
		this.setLayout(new GridLayout(12, 3, 1, 1));

		lblTitle = new JLabel("Verleng Abonnement");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblAbonnementsNummer = new JLabel("Abonnementsnummer: ");
		txtAbonnementsNummer = new JTextField();
		btnZoek = new JButton("Zoek");
		lblKlantenNummer = new JLabel("Klantennumer: ");
		lblKLantenNummerResult = new JLabel("123456");
		lblStartdatum = new JLabel("Startdatum: ");
		
		lblTreinkaart = new JLabel("Treinkaart: ");
		String[] str = { "Trajecttreinkaart", "Halftijdstreinkaart", "Nettreinkaart", "Schooltreinkaart" };
		cbxTreinkaart = new JComboBox(str);
		
		lblDuur = new JLabel("Duur: ");
		lblDuurResult = new JLabel("'result'");
		lblVervaldatum = new JLabel("'result'");
		
		lblKlasse = new JLabel("Klasse: ");
		rdbEersteKlasse = new JRadioButton("1e Klas");
		rdbTweedeKlasse = new JRadioButton("2e Klas");
		
		lblVastTraject = new JLabel("Vast traject: ");
		rdbJa = new JRadioButton("Ja");
		rdbNee=new JRadioButton("Nee");
		
		lblStation1 = new JLabel("Station 1: ");
		lblStation2 = new JLabel("Station 2: ");
		txtStation1 = new JTextField();
		txtStation2 = new JTextField();
		
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartdatum = new JDatePickerImpl(datePanel, new GUIDateFormat());
		
		grpKlasses = new ButtonGroup();
		grpKlasses.add(rdbEersteKlasse);
		grpKlasses.add(rdbTweedeKlasse);
		
		grpJaNee = new ButtonGroup();
		grpJaNee.add(rdbJa);
		grpJaNee.add(rdbNee);
		
		btnPrint = new JButton("PRINT");
		lblPrint = new JLabel("€0");
		
		btnMeerInfo = new JButton("Meer info");
		
		btnValideer = new JButton("Valideer");
		
		this.add(lblTitle);
		this.add(new JLabel());
		this.add(new JLabel());
		
		
		this.add(lblAbonnementsNummer);
		this.add(txtAbonnementsNummer);
		this.add(btnZoek);
		
		this.add(lblKlantenNummer);
		this.add(lblKLantenNummerResult);
		this.add(btnMeerInfo);

		this.add(lblTreinkaart);
		this.add(cbxTreinkaart);
		this.add(new JLabel());
		
		this.add(lblStartdatum);
		this.add(dteStartdatum);
		this.add(new JLabel());

		this.add(lblDuur);
		this.add(lblDuurResult);
		this.add(lblVervaldatum);
		
		this.add(lblKlasse);
		this.add(rdbEersteKlasse);
		this.add(rdbTweedeKlasse);
		
		this.add(lblVastTraject);
		this.add(rdbJa);
		this.add(rdbNee);
		this.add(new JLabel());

		this.add(lblStation1);
		this.add(txtStation1);
		this.add(new JLabel());
		this.add(lblStation2);
		this.add(txtStation2);
		
		this.add(btnPrint);
		this.add(lblPrint);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());

		this.add(btnValideer);
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

	public JComboBox getCbxTreinkaart() {
		return cbxTreinkaart;
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
	
	

}
