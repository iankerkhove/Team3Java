package panels;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblDescr;

	public StartPanel() {
		init();
	}

	private void init() {

		/* Create all components */
		lblTitle = new JLabel("Start");
		lblTitle.setBounds(10, 0, 440, 48);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		String s = "<html><p>Deze applicatie werd ontworpen door"
				+ "<ul>"
				+ "<li>Jonas De Frère</li>"
				+ "<li>Ian Kerkhove</li>"
				+ "<li>Ludovic Marchand</li>"
				+ "<li>Glenn Bellemans</li>"
				+ "<li>Remco Geldhof</li>"
				+ "<li>Joshua Van der Merwe</li>"
				+ "<li>Youssef Imgharane</li>"
				+ "</ul>"
				+ "<br>"
				+ "Klik op een onderdeel om door te gaan.</p>"
				+ "</html>";
		lblDescr = new JLabel(s);
		lblDescr.setVerticalAlignment(SwingConstants.TOP);
		lblDescr.setBounds(10, 50, 440, 249);
		
		/* Add all components */
		setLayout(null);
		this.add(lblTitle);
		this.add(lblDescr);
	}
}
