package panels;

import javax.swing.*;



import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

import java.awt.GridLayout;
import java.util.Properties;
import java.awt.Font;
import gui.LangageHandler;
@SuppressWarnings("serial")
public class VerlorenVoorwerpZoekPanel extends JPanel {

	private String taal = LangageHandler.getTaal();
	
	private JButton btnToonAlles;
	private JButton btnZoek;

	private JLabel lblTreinNummer;
	private JLabel lblDatum;
	private JLabel lblResultat;
	private JLabel lblTitel;
	private JLabel lblStation;

	private JTextField txtTreinNummer;
	
	private StationsAutoCompletor txtStation;
	
	private JDatePickerImpl datePicker;
	
	public VerlorenVoorwerpZoekPanel() {
		setLayout(new GridLayout(2, 1, 5, 5));

		JPanel search = new JPanel();
		search.setLayout(new GridLayout(6, 2, 10, 10));

		//1
		lblTitel = new JLabel();
		LangageHandler.chooseLangageLbl(lblTitel, taal, "zoekVoorwerp");
		lblTitel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		search.add(lblTitel);
		
		search.add(new JLabel());
		
		//2
		search.add(new JLabel());
		
		btnToonAlles = new JButton();
		LangageHandler.chooseLangageBtn(btnToonAlles, taal, "toonAlles");
		search.add(btnToonAlles);
		
		//3
		lblStation = new JLabel();
		LangageHandler.chooseLangageLbl(lblStation, taal, "station");
		search.add(lblStation);
	
		txtStation = new StationsAutoCompletor();
		search.add(txtStation);
				

		//4
		lblTreinNummer = new JLabel();
		LangageHandler.chooseLangageLbl(lblTreinNummer, taal, "treinnummer");
		search.add(lblTreinNummer);

		txtTreinNummer = new JTextField();
		search.add(txtTreinNummer);

		//5
		lblDatum = new JLabel();
		LangageHandler.chooseLangageLbl(lblDatum, taal, "datum");
		search.add(lblDatum);

		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
		datePicker = new JDatePickerImpl(datePanel, new GUIDateFormat());
		datePicker.getJFormattedTextField().setText(GUIDateFormat.getDate());
		search.add(datePicker);

		//6
		search.add(new JLabel());
		
		btnZoek = new JButton();
		LangageHandler.chooseLangageBtn(btnZoek, taal, "zoek");
		search.add(btnZoek);

		
		this.add(search);

		lblResultat = new JLabel();
		lblResultat.setText("  ");
		lblResultat.setVerticalAlignment(SwingConstants.TOP);

		JScrollPane scroller = new JScrollPane(lblResultat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroller);
	}

	public JButton getBtnToonAlles() {
		return btnToonAlles;
	}

	public JButton getBtnZoek() {
		return btnZoek;
	}

	public JLabel getLblTreinNummer() {
		return lblTreinNummer;
	}

	public JLabel getLblDatum() {
		return lblDatum;
	}

	public JLabel getLblResultat() {
		return lblResultat;
	}

	public JLabel getLblTitel() {
		return lblTitel;
	}

	public JLabel getLblStation() {
		return lblStation;
	}

	public JTextField getTxtTreinNummer() {
		return txtTreinNummer;
	}

	public StationsAutoCompletor getTxtStation() {
		return txtStation;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}



}