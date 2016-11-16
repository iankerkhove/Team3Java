package panels;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.GUIDateFormat;

import java.awt.GridLayout;
import java.util.Properties;
import java.awt.Font;

@SuppressWarnings("serial")
public class VerlorenVoorwerpZoekPanel extends JPanel {

	private JButton btnToonAlles;
	private JButton btnNieuw;
	private JButton btnZoek;
	private JButton btnTerug;

	private JLabel lblTreinNummer;
	private JLabel lblSoortVoorwerp;
	private JLabel lblMerk;
	private JLabel lblKleur;
	private JLabel lblDatum;
	private JLabel lblResultat;

	private JTextField txtTreinNummer;
	private JTextField txtSoortVoorwerp;
	private JTextField txtMerk;
	private JTextField txtKleur;
	
	private JDatePickerImpl datePicker;
	
	public VerlorenVoorwerpZoekPanel() {
		setLayout(new GridLayout(2, 1, 5, 5));

		JPanel search = new JPanel();
		search.setLayout(new GridLayout(7, 2, 5, 5));

		btnToonAlles = new JButton();
		btnToonAlles.setText("Toon alles");
		btnToonAlles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(btnToonAlles);

		btnNieuw = new JButton();
		btnNieuw.setText("Nieuw");
		btnNieuw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(btnNieuw);

		lblTreinNummer = new JLabel();
		lblTreinNummer.setText("Treinnummer:");
		lblTreinNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(lblTreinNummer);

		txtTreinNummer = new JTextField();
		search.add(txtTreinNummer);

		lblDatum = new JLabel();
		lblDatum.setText("Datum:");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(lblDatum);

		Properties properties = new Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
		datePicker = new JDatePickerImpl(datePanel, new GUIDateFormat());
		datePicker.getJFormattedTextField().setText(GUIDateFormat.getDate());
		search.add(datePicker);

		lblSoortVoorwerp = new JLabel();
		lblSoortVoorwerp.setText("Soort voorwerp:");
		lblSoortVoorwerp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(lblSoortVoorwerp);

		txtSoortVoorwerp = new JTextField();
		txtSoortVoorwerp.setColumns(1);
		search.add(txtSoortVoorwerp);

		lblMerk = new JLabel();
		lblMerk.setText("Merk:");
		lblMerk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(lblMerk);

		txtMerk = new JTextField();
		txtMerk.setColumns(1);
		search.add(txtMerk);

		lblKleur = new JLabel();
		lblKleur.setText("Kleur:");
		lblKleur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(lblKleur);

		txtKleur = new JTextField();
		txtKleur.setColumns(1);
		search.add(txtKleur);

		btnZoek = new JButton();
		btnZoek.setText("Zoek");
		btnZoek.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(btnZoek);

		btnTerug = new JButton();
		btnTerug.setText("Terug");
		btnTerug.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.add(btnTerug);

		this.add(search);

		lblResultat = new JLabel();
		lblResultat.setText("  ");
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResultat.setVerticalAlignment(SwingConstants.TOP);

		JScrollPane scroller = new JScrollPane(lblResultat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroller);
	}

	public JButton getBtnToonAlles() {
		return btnToonAlles;
	}

	public JButton getBtnNieuw() {
		return btnNieuw;
	}

	public JButton getBtnZoek() {
		return btnZoek;
	}

	public JButton getBtnTerug() {
		return btnTerug;
	}

	public JLabel getLblTreinNummer() {
		return lblTreinNummer;
	}

	public JLabel getLblSoortVoorwerp() {
		return lblSoortVoorwerp;
	}

	public JLabel getLblMerk() {
		return lblMerk;
	}

	public JLabel getLblKleur() {
		return lblKleur;
	}

	public JLabel getLblDatum() {
		return lblDatum;
	}

	public JLabel getLblResultat() {
		return lblResultat;
	}

	public JTextField getTxtTreinNummer() {
		return txtTreinNummer;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public JTextField getTxtSoortVoorwerp() {
		return txtSoortVoorwerp;
	}

	public JTextField getTxtMerk() {
		return txtMerk;
	}

	public JTextField getTxtKleur() {
		return txtKleur;
	}

}