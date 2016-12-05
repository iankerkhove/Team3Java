package panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.*;
import javax.swing.JTextField;
import java.util.Properties;
import org.jdatepicker.impl.*;
import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

public class PassesEnKaartenPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblStartDatum;
	private JLabel lblKlasse;
	private JLabel lblPassType;
	private JLabel lblStation1;
	private JLabel lblStation2;
	private JLabel lblPrint;
	
	private JButton btnPrint;
	
	private StationsAutoCompletor txtStation1;
	private StationsAutoCompletor txtStation2;
	
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private ButtonGroup grpKlasses;
	
	private JDatePickerImpl dteStartDatum;
	
	private JComboBox cbxPassType;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PassesEnKaartenPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		GridBagConstraints c3 = new GridBagConstraints();
		GridBagConstraints c4 = new GridBagConstraints();
		GridBagConstraints c5 = new GridBagConstraints();
		GridBagConstraints c6 = new GridBagConstraints();
		GridBagConstraints c7 = new GridBagConstraints();
		GridBagConstraints c8 = new GridBagConstraints();
		GridBagConstraints c9 = new GridBagConstraints();
		GridBagConstraints c10 = new GridBagConstraints();
		GridBagConstraints c11 = new GridBagConstraints();
		GridBagConstraints c12 = new GridBagConstraints();
		GridBagConstraints c13 = new GridBagConstraints();
		
		lblTitle = new JLabel("Passes en Kaarten");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblStartDatum = new JLabel("Startdatum: ");
		lblKlasse = new JLabel("Klasse: ");
		rdbEersteKlasse = new JRadioButton("1e Klas");
		rdbTweedeKlasse = new JRadioButton("2e Klas");
		lblPassType = new JLabel("Type pass: ");
		btnPrint = new JButton("PRINT");
		lblPrint = new JLabel("€0");
		lblStation1 = new JLabel("Station 1: ");
		lblStation2 = new JLabel("Station 2: ");
		txtStation1 = new StationsAutoCompletor();
		txtStation2 = new StationsAutoCompletor();
		
		grpKlasses = new ButtonGroup();
		grpKlasses.add(rdbEersteKlasse);
		grpKlasses.add(rdbTweedeKlasse);
		
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartDatum = new JDatePickerImpl(datePanel2, new GUIDateFormat());
		dteStartDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		
		String[] passType = {"Go Pass 10", "Key Card", "Rail Pass"};
		cbxPassType = new JComboBox(passType);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 2;
		//c.weighty = 1.0;
		//c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblTitle,c);
		
		c1.gridx = 0;
		c1.gridy = 1;
		c1.ipady = 60;
		this.add(lblStartDatum,c1);
		c2.gridx = 1;
		c2.gridy = 1;
		this.add(dteStartDatum,c2);
		c3.gridx = 2;
		c3.gridy = 1;
		this.add(lblPassType,c3);
		c4.gridx = 3;
		c4.gridy = 1;
		this.add(cbxPassType,c4);
		
		c5.gridx = 0;
		c5.gridy = 2;
		this.add(lblKlasse,c5);
		c6.gridx = 1;
		c6.gridy = 2;
		this.add(rdbEersteKlasse,c6);
		c7.gridx = 2;
		c7.gridy = 2;
		this.add(rdbTweedeKlasse,c7);
		
		c8.gridx = 0;
		c8.gridy = 3;
		this.add(btnPrint,c8);
		c9.gridx = 1;
		c9.gridy = 3;
		this.add(lblPrint,c9);
		c10.gridx = 2;
		c10.gridy = 3;
		c10.ipady = 60;
		this.add(lblStation1,c10);
		c11.gridx = 3;
		c11.gridy = 3; 
		this.add(txtStation1,c11);
		
		c12.gridx = 2;
		c12.gridy = 4;
		c12.ipady = 60;
		this.add(lblStation2,c12);
		c13.gridx = 3;
		c13.gridy = 4;
		this.add(txtStation2,c13);
	}

}
