package panels;

import java.awt.GridLayout;

import javax.swing.*;
import gui.LangageHandler;
import gui.StationsAutoCompletor;

public class StaffToevoegenPanel extends JPanel{

	private JLabel lblTitel;
	private JLabel lblVoornaam;
	private JLabel lblAchternaam;
	private JLabel lblStraat;
	private JLabel lblNummer;
	private JLabel lblGemeente;
	private JLabel lblPostcode;
	private JLabel lblStation;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JTextField txtStraat;
	private JTextField txtNummer;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	private JButton btnVoegToe;
	
	private StationsAutoCompletor txtStation;
	
	public StaffToevoegenPanel() {
		setLayout(new GridLayout(1, 1, 5, 5));
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(11,2,5,5));
		
		////1
		lblTitel = new JLabel();
		LangageHandler.chooseLangageLbl(lblTitel, "titelStaff");
		content.add(lblTitel);
		content.add(new JLabel());
		
		/////2
		lblVoornaam = new JLabel();
		LangageHandler.chooseLangageLbl(lblVoornaam, "naam");
		txtVoornaam = new JTextField();
		txtVoornaam.setColumns(1);
		content.add(lblVoornaam);
		content.add(txtVoornaam);
		
		/////3
		lblAchternaam = new JLabel();
		LangageHandler.chooseLangageLbl(lblAchternaam, "voornaam");
		txtAchternaam = new JTextField();
		txtAchternaam.setColumns(1);
		content.add(lblAchternaam);
		content.add(txtAchternaam);
		
		/////4
		lblStraat = new JLabel();
		LangageHandler.chooseLangageLbl(lblStraat, "straat");
		txtStraat = new JTextField();
		txtStraat.setColumns(1);
		content.add(lblStraat);
		content.add(txtStraat);
		
		//////5
		lblNummer = new JLabel();
		LangageHandler.chooseLangageLbl(lblNummer, "nummer");
		txtNummer = new JTextField();
		txtNummer.setColumns(1);
		content.add(lblNummer);
		content.add(txtNummer);
		
		//////6
		lblGemeente = new JLabel();
		LangageHandler.chooseLangageLbl(lblGemeente, "gemeente");
		txtGemeente = new JTextField();
		txtGemeente.setColumns(1);	
		content.add(lblGemeente);
		content.add(txtGemeente);
		
		///////7
		lblPostcode = new JLabel();
		LangageHandler.chooseLangageLbl(lblPostcode, "postcode");
		txtPostcode = new JTextField();
		txtPostcode.setColumns(1);
		content.add(lblPostcode);
		content.add(txtPostcode);
		
		///////8
		lblStation = new JLabel();
		LangageHandler.chooseLangageLbl(lblStation, "station");
		txtStation = new StationsAutoCompletor();
		content.add(lblStation);
		content.add(txtStation);
		
		////////9
		lblUsername = new JLabel();
		LangageHandler.chooseLangageLbl(lblUsername, "username");
		txtUsername = new JTextField();
		txtUsername.setColumns(1);
		content.add(lblUsername);
		content.add(txtUsername);
		
		/////////10
		lblPassword = new JLabel();
		LangageHandler.chooseLangageLbl(lblPassword, "password");
		txtPassword = new JTextField();
		txtPassword.setColumns(1);
		content.add(lblPassword);
		content.add(txtPassword);
		
		/////////11
		btnVoegToe = new JButton();
		LangageHandler.chooseLangageBtn(btnVoegToe, "voegStaff");
		content.add(new JLabel());
		content.add(btnVoegToe);
		
		this.add(content);
		
		
	}

	public JLabel getLblTitel() {
		return lblTitel;
	}

	public JLabel getLblGemeente() {
		return lblGemeente;
	}

	public JLabel getLblVoornaam() {
		return lblVoornaam;
	}

	public JLabel getLblAchternaam() {
		return lblAchternaam;
	}

	public JButton getBtnVoegToe() {
		return btnVoegToe;
	}

	public JLabel getLblStraat() {
		return lblStraat;
	}

	public JLabel getLblNummer() {
		return lblNummer;
	}

	
	public JLabel getLblPostcode() {
		return lblPostcode;
	}

	public JLabel getLblStation() {
		return lblStation;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}
	
}
