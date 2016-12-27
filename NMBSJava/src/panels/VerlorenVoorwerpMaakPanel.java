package panels;

import java.awt.Font;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

@SuppressWarnings("serial")
public class VerlorenVoorwerpMaakPanel extends JPanel {

	
	private JLabel lblTitel;
	private JLabel lblStation;
	private JLabel lblTrein;
	private JLabel lblOmschrijving;
	private JLabel lblDatum;
	private JLabel lblResultat;
	
	private JDatePickerImpl datePicker;
	
	private JTextField txtTrein;
	private JTextField txtOmschrijving;
	
	private JButton btnMaak;
	
	private StationsAutoCompletor txtStation;
	
	public VerlorenVoorwerpMaakPanel() {
		setLayout(new GridLayout(1, 1, 5, 5));
		
		JPanel maak = new JPanel();
		maak.setLayout(new GridLayout(7, 2, 5,5));
		
		// 1
		lblTitel = new JLabel();
		lblTitel.setText("Toevoegen voorwerp");
		lblTitel.setFont((new Font("Tahoma", Font.PLAIN, 18)));
		maak.add(lblTitel);
		
		maak.add(new JLabel());
		
		////
		
		
		
		//2
		lblStation = new JLabel();
		lblStation.setText("Station: ");
		maak.add(lblStation);
	
		txtStation = new StationsAutoCompletor();
		maak.add(txtStation);
		
		//3
		lblTrein = new JLabel();
		lblTrein.setText("Treinnummer: ");
		maak.add(lblTrein);
		
		txtTrein = new JTextField();
		txtTrein.setColumns(1);
		maak.add(txtTrein);
		
		//4
		lblOmschrijving = new JLabel();
		lblOmschrijving.setText("Omschrijving: ");
		maak.add(lblOmschrijving);
		
		txtOmschrijving = new JTextField();
		txtOmschrijving.setColumns(1);
		maak.add(txtOmschrijving);
		
		//5
		lblDatum = new JLabel();
		lblDatum.setText("Datum: ");
		maak.add(lblDatum);
		
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
		datePicker = new JDatePickerImpl(datePanel, new GUIDateFormat());
		datePicker.getJFormattedTextField().setText(GUIDateFormat.getDate());
		maak.add(datePicker);
		
		//6
		maak.add(new JLabel());
		
		btnMaak = new JButton();
		btnMaak.setText("Voeg nieuwe voorwerp toe");
		maak.add(btnMaak);
		
		lblResultat = new JLabel();
		maak.add(lblResultat);
		
		
		this.add(maak);
		
		
	}

	public JLabel getLblTitel() {
		return lblTitel;
	}

	public JLabel getLblStation() {
		return lblStation;
	}

	public JLabel getLblTrein() {
		return lblTrein;
	}
	
	

	public JLabel getLblResultat() {
		return lblResultat;
	}

	public JLabel getLblOmschrijving() {
		return lblOmschrijving;
	}

	public JLabel getLblDatum() {
		return lblDatum;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public JTextField getTxtTrein() {
		return txtTrein;
	}

	public JTextField getTxtOmschrijving() {
		return txtOmschrijving;
	}

	public JButton getBtnMaak() {
		return btnMaak;
	}

	public StationsAutoCompletor getTxtStation() {
		return txtStation;
	}

}
