package panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import javax.swing.JTextField;
import java.util.Properties;
import org.jdatepicker.impl.*;
import gui.GUIDateFormat;
import gui.PassTypesAutoCompletor;
import gui.StationsAutoCompletor;

public class PasPrijzenAanPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblTypeTicket;
	private JLabel lblHuidigePrijs;
	private JLabel lblNieuwePrijs;
	private JLabel lblKlasse;
	
	private JTextField txtHuidigePrijs;
	private JTextField txtNieuwePrijs;
	private JTextField txtNewTypeTicket;
	
	private JButton btnWijzig;
	
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private ButtonGroup grpKlasses;
	
	private PassTypesAutoCompletor txtTypeTicket;
	
	public PasPrijzenAanPanel() {
			this.setLayout(new GridLayout(7, 2, 5, 5));
			
			lblTitle = new JLabel("Wijzig prijzen");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			lblTypeTicket = new JLabel("Type ticket");
			txtTypeTicket = new PassTypesAutoCompletor();
			txtNewTypeTicket = new JTextField("Nieuw type ticket");
			
			lblHuidigePrijs = new JLabel("Huidige prijs");
			txtHuidigePrijs = new JTextField("€10.000");
			txtHuidigePrijs.setEditable(false);
			lblNieuwePrijs = new JLabel("Nieuwe prijs");
			txtNieuwePrijs = new JTextField(5);
			
			btnWijzig = new JButton("Prijs aanpassen");
			
			lblKlasse = new JLabel("Klasse");
			rdbEersteKlasse = new JRadioButton("1e klas");
			rdbEersteKlasse.setMnemonic(1);
			rdbTweedeKlasse = new JRadioButton("2e klas");
			rdbTweedeKlasse.setMnemonic(1);
			rdbTweedeKlasse.setSelected(true);
			grpKlasses = new ButtonGroup();
			grpKlasses.add(rdbEersteKlasse);
			grpKlasses.add(rdbTweedeKlasse);
			
			
			this.add(lblTitle);
			this.add(new JLabel());
			
			this.add(lblTypeTicket);
			this.add(txtTypeTicket);
			
			this.add(new JLabel());
			this.add(txtNewTypeTicket);
			
			this.add(lblKlasse);
			JPanel klassepane = new JPanel();
			klassepane.setLayout(new GridLayout(2, 1));
			klassepane.add(rdbEersteKlasse);
			klassepane.add(rdbTweedeKlasse);
			this.add(klassepane);
			
			this.add(lblHuidigePrijs);
			this.add(txtHuidigePrijs);
			
			this.add(lblNieuwePrijs);
			this.add(txtNieuwePrijs);
			
			this.add(btnWijzig);
	}
	
	public JTextField getTxtNewTypeTicket() {
		return txtNewTypeTicket;
	}

	public void setTxtHuidigePrijs(JTextField txtHuidigePrijs) {
		this.txtHuidigePrijs = txtHuidigePrijs;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblTypeTicket() {
		return lblTypeTicket;
	}

	public JLabel getLblHuidigePrijs() {
		return lblHuidigePrijs;
	}

	public JLabel getLblNieuwePrijs() {
		return lblNieuwePrijs;
	}

	public JLabel getLblKlasse() {
		return lblKlasse;
	}

	public JTextField getTxtHuidigePrijs() {
		return txtHuidigePrijs;
	}

	public JTextField getTxtNieuwePrijs() {
		return txtNieuwePrijs;
	}

	public JButton getBtnWijzig() {
		return btnWijzig;
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

	public PassTypesAutoCompletor getTxtTypeTicket() {
		return txtTypeTicket;
	}
}