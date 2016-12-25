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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PasPrijzenAanPanel() {
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
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
			
			c.insets = new Insets(5, 10, 5, 10);
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1.0;
			c.weighty = 1.0;
			//c.anchor = GridBagConstraints.WEST;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.ipady = 20;
			c.ipadx = 10;
			this.add(lblTitle,c);
			c.gridwidth--;
			
			c.gridy++;
			this.add(lblTypeTicket, c);
			c.gridx++;
			this.add(txtTypeTicket, c);
			c.gridx++;
			this.add(txtNewTypeTicket,c);
			c.gridy++;
			c.gridx = 0;
			this.add(lblKlasse, c);
			c.gridx++;
			this.add(rdbEersteKlasse,c);
			c.gridx++;
			this.add(rdbTweedeKlasse,c);
			
			c.gridy++;
			c.gridx = 0;
			this.add(lblHuidigePrijs, c);
			c.gridx++;
			this.add(txtHuidigePrijs, c);
			c.gridx++;
			this.add(lblNieuwePrijs, c);
			c.gridx++;
			this.add(txtNieuwePrijs, c);
			c.gridy++;
			c.gridx = 0;
			this.add(btnWijzig, c);
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

	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
		testFrame.setSize(600, 400);
		testFrame.add(new PasPrijzenAanPanel());
		testFrame.setVisible(true);
	}

}
