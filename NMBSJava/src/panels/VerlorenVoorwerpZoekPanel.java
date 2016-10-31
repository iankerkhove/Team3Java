package panels;

import javax.swing.*;
import java.awt.GridLayout;
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

	private JTextField txtTreinNummer;
	private JTextField txtDatum;
	private JTextField txtSoortVoorwerp;
	private JTextField txtMerk;
	private JTextField txtKleur;

	public VerlorenVoorwerpZoekPanel() {
		setLayout(new GridLayout(7, 2,20,10));

		btnToonAlles = new JButton();
		btnToonAlles.setText("Toon alles");
		btnToonAlles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(btnToonAlles);

		btnNieuw = new JButton();
		btnNieuw.setText("Nieuw");
		btnNieuw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(btnNieuw);

		lblTreinNummer = new JLabel();
		lblTreinNummer.setText("Treinnummer:");
		lblTreinNummer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(lblTreinNummer);

		txtTreinNummer = new JTextField();
		this.add(txtTreinNummer);
		
		lblDatum = new JLabel();
		lblDatum.setText("Datum:");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(lblDatum);

		txtDatum = new JTextField();
		txtDatum.setColumns(1);
		this.add(txtDatum);
		
		lblSoortVoorwerp = new JLabel();
		lblSoortVoorwerp.setText("Soort voorwerp:");
		lblSoortVoorwerp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(lblSoortVoorwerp);
		
		txtSoortVoorwerp = new JTextField();
		txtSoortVoorwerp.setColumns(1);
		this.add(txtSoortVoorwerp);
		
		lblMerk = new JLabel();
		lblMerk.setText("Merk:");
		lblMerk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(lblMerk);
		
		txtMerk = new JTextField();
		txtMerk.setColumns(1);
		this.add(txtMerk);
		
		lblKleur = new JLabel();
		lblKleur.setText("Kleur:");
		lblKleur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(lblKleur);
		
		txtKleur = new JTextField();
		txtKleur.setColumns(1);
		this.add(txtKleur);
			
		btnZoek = new JButton();
		btnZoek.setText("Zoek");
		btnZoek.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(btnZoek);
		
		btnTerug = new JButton();
		btnTerug.setText("Terug");
		btnTerug.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.add(btnTerug);
	}

}